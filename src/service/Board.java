package service;

import figure.*;
import dto.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Board implements Cloneable{
    private Figure[][] board;
    private ChessGameStatus status = ChessGameStatus.WHITE_MOVE;
    private MoveR prevMove = null;
    public Board() {
        board = new Figure[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Empty(new Place(row, col));
            }
        }

        board[0][0] = new Rook("white", new Place(0, 0));
        board[1][0] = new Knight("white", new Place(1, 0));
        board[2][0] = new Bishop("white", new Place(2, 0));
        board[3][0] = new Queen("white", new Place(3, 0));
        board[4][0] = new King("white", new Place(4, 0));
        board[5][0] = new Bishop("white", new Place(5, 0));
        board[6][0] = new Knight("white", new Place(6, 0));
        board[7][0] = new Rook("white", new Place(7, 0));

        for (int col = 0; col < 8; col++) {
            board[col][1] = new Pawn("white", new Place(col, 1));
        }

        board[0][7] = new Rook("black", new Place(0, 7));
        board[1][7] = new Knight("black", new Place(1, 7));
        board[2][7] = new Bishop("black", new Place(2, 7));
        board[3][7] = new Queen("black", new Place(3, 7));
        board[4][7] = new King("black", new Place(4, 7));
        board[5][7] = new Bishop("black", new Place(5, 7));
        board[6][7] = new Knight("black", new Place(6, 7));
        board[7][7] = new Rook("black", new Place(7, 7));

        for (int col = 0; col < 8; col++) {
            board[col][6] = new Pawn("black", new Place(col, 6));
        }

        board[0][4] = new Bishop("black", new Place(0, 4));
    }

    public Figure[][] getBoard() {
        return board;
    }

    public void setBoard(Figure[][] board) {
        this.board = board;
    }

    public ChessGameStatus update(String input) {
        ChessGameStatus newStatus = status;

        //Move other side
        if (!input.equalsIgnoreCase("start")) {
            move(input);
        }
        //Move for us
        switch (status) {
            case WHITE_MOVE ->
                    newStatus = whiteMove(status);
            case BLACK_MOVE ->
                    newStatus = blackMove(status);
            case DRAW -> {
                return ChessGameStatus.DRAW;
            }
            case CHECKMATE -> {
                return ChessGameStatus.CHECKMATE;
            }
        }
        return newStatus;
    }

    private ChessGameStatus blackMove(ChessGameStatus status) {
        Optional<MoveR> bestMove = findBestMove("black");
        if (bestMove.isEmpty())
            return ChessGameStatus.ERROR;
        Place oldPlace = bestMove.get().figure().getPlace();
        Place newPlace = bestMove.get().newPlace();
        System.out.println("Found best move for black: " + bestMove.get().figure()
            + " to " + bestMove.get().newPlace() + " with rating: " + bestMove.get().moveRating()
        );
        move(oldPlace.toChessNotation() + newPlace.toChessNotation());
        return ChessGameStatus.WHITE_MOVE;
    }

    private ChessGameStatus whiteMove(ChessGameStatus status) {
        Optional<MoveR> bestMoveOptional = findBestMove("white");

        if (bestMoveOptional.isEmpty())
            return ChessGameStatus.ERROR;

        MoveR bestMove = bestMoveOptional.get();

        ChessAnalyzeService.chessMoveActionListener(this, bestMove);

        this.prevMove = bestMove;

        return ChessGameStatus.BLACK_MOVE;
    }

    private Optional<MoveR> findBestMove(String color) {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(figure -> figure != null && figure.getColor().equalsIgnoreCase(color))
                .map(figure -> figure.bestMove(this))
                .filter(Objects::nonNull) // if method .move for figure not ready
                .max(Comparator.comparingDouble(MoveR::moveRating));
    }

    public ChessGameStatus getStatus() {
        return status;
    }

    public void setStatus(ChessGameStatus status) {
        this.status = status;
    }

    public void move(String input) {
        input = input.toLowerCase();

        // Получаем старые координаты
        int oldCol = input.charAt(0) - 'a';
        int oldRow = Character.getNumericValue(input.charAt(1) - 1);

        // Получаем новые координаты
        int newCol = input.charAt(2) - 'a';
        int newRow = Character.getNumericValue(input.charAt(3) - 1);

        // Проверяем, есть ли фигура на старом месте
        Figure figure = board[oldCol][oldRow];
        if (figure instanceof Empty) {
            System.out.println("Error: nothing to move");
        }

        // Обновляем координаты фигуры
        figure.setPlace(new Place(newCol, newRow));

        // Перемещаем фигуру на новое место
        board[newCol][newRow] = figure;

        // Освобождаем старое место (ставим пустую клетку)
        board[oldCol][oldRow] = new Empty(new Place(oldCol, oldRow));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Заголовок с буквами колонок
        sb.append("   a b c d e f g h\n");
        sb.append(" +----------------+\n");

        // Проходим по каждой строке доски, начиная с 7 до 0 (от конца к началу)
        for (int row = 7; row >= 0; row--) {
            sb.append(row + 1).append("| ");  // Нумерация строк

            for (int col = 0; col < 8; col++) {
                Figure figure = board[col][row];

                if (figure instanceof Empty) {
                    sb.append(". ");  // Пустые клетки
                } else if (figure instanceof Pawn) {
                    sb.append(figure.getColor().equals("black") ? "p " : "P ");
                } else if (figure instanceof Rook) {
                    sb.append(figure.getColor().equals("black") ? "r " : "R ");
                } else if (figure instanceof Knight) {
                    sb.append(figure.getColor().equals("black") ? "n " : "N ");
                } else if (figure instanceof Bishop) {
                    sb.append(figure.getColor().equals("black") ? "b " : "B ");
                } else if (figure instanceof Queen) {
                    sb.append(figure.getColor().equals("black") ? "q " : "Q ");
                } else if (figure instanceof King) {
                    sb.append(figure.getColor().equals("black") ? "k " : "K ");
                }
            }

            sb.append("|").append(row + 1);  // Завершаем строку нумерацией
            sb.append("\n");
        }

        sb.append(" +----------------+\n");
        sb.append("   a b c d e f g h\n");  // Завершаем колонками

        return sb.toString();
    }

    public void pawnToQueen(Place newPlace, String color) {
        board[newPlace.getX()][newPlace.getY()] = new Queen(color, newPlace);
    }

    @Override
    public Board clone() {
        try {
            Board cloned = (Board) super.clone();

            cloned.board = new Figure[board.length][];
            for (int i = 0; i < board.length; i++) {
                cloned.board[i] = new Figure[board[i].length];
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != null) {
                        cloned.board[i][j] = ((Figure) board[i][j]).clone();
                    }
                }
            }

            cloned.status = this.status;
            cloned.prevMove = this.prevMove != null ? this.prevMove : null;

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
