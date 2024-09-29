package service;

import figure.*;
import dto.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class Board implements IBoard {
    private Figure[][] board;
    private ChessGameStatus status = ChessGameStatus.WHITE_MOVE;

    public Board() {
        board = new Figure[8][8];

        // Заполнение пустыми клетками
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = new Empty(new Place(row, col));
            }
        }

        // Расставляем белые фигуры
        board[0][0] = new Rook("white", new Place(0, 0));
        board[0][1] = new Knight("white", new Place(0, 1));
        board[0][2] = new Bishop("white", new Place(0, 2));
        board[0][3] = new Queen("white", new Place(0, 3));
        board[0][4] = new King("white", new Place(0, 4));
        board[0][5] = new Bishop("white", new Place(0, 5));
        board[0][6] = new Knight("white", new Place(0, 6));
        board[0][7] = new Rook("white", new Place(0, 7));

        // Белые пешки
        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn("white", new Place(1, col));
        }

        // Расставляем черные фигуры
        board[7][0] = new Rook("black", new Place(7, 0));
        board[7][1] = new Knight("black", new Place(7, 1));
        board[7][2] = new Bishop("black", new Place(7, 2));
        board[7][3] = new Queen("black", new Place(7, 3));
        board[7][4] = new King("black", new Place(7, 4));
        board[7][5] = new Bishop("black", new Place(7, 5));
        board[7][6] = new Knight("black", new Place(7, 6));
        board[7][7] = new Rook("black", new Place(7, 7));

        // Черные пешки
        for (int col = 0; col < 8; col++) {
            board[6][col] = new Pawn("black", new Place(6, col));
        }
    }

    public Figure[][] getBoard() {
        return board;
    }

    public void setBoard(Figure[][] board) {
        this.board = board;
    }

    @Override
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

        move(oldPlace.toChessNotation() + newPlace.toChessNotation());
        return ChessGameStatus.WHITE_MOVE;
    }

    private ChessGameStatus whiteMove(ChessGameStatus status) {
        Optional<MoveR> bestMove = findBestMove("white");
        if (bestMove.isEmpty())
            return ChessGameStatus.ERROR;
        Place oldPlace = bestMove.get().figure().getPlace();
        Place newPlace = bestMove.get().newPlace();

        move(oldPlace.toChessNotation() + newPlace.toChessNotation());
        return ChessGameStatus.BLACK_MOVE;
    }

    private Optional<MoveR> findBestMove(String color) {
        return Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(figure -> figure != null && figure.getColor().equalsIgnoreCase(color))
                .map(figure -> figure.move(this))
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
        Figure figure = board[oldRow][oldCol];
        if (figure instanceof Empty) {
            System.out.println("Error: nothing to move");
        }

        // Обновляем координаты фигуры
        figure.setPlace(new Place(newRow, newCol));

        // Перемещаем фигуру на новое место
        board[newRow][newCol] = figure;

        // Освобождаем старое место (ставим пустую клетку)
        board[oldRow][oldCol] = new Empty(new Place(oldRow, oldCol));
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
                Figure figure = board[row][col];

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
}
