package service;

import dto.MoveR;
import figure.Figure;
import figure.Pawn;

import java.util.Arrays;
import java.util.Objects;

public class ChessAnalyzeService {
    public static Double additionalRate(Figure figure, Place place) {

        if (figure instanceof Pawn) {
            int x = place.getX();
            int distanceTo3 = Math.abs(x - 3);
            int distanceTo4 = Math.abs(x - 4);

            int minDistance = Math.min(distanceTo3, distanceTo4);

            switch (minDistance) {
                case 0: return ChessRating.CENTER_COEFFICIENT4;
                case 1: return ChessRating.CENTER_COEFFICIENT3;
                case 2: return ChessRating.CENTER_COEFFICIENT2;
                case 3: return ChessRating.CENTER_COEFFICIENT1;
                default: return 0.0;
            }
        }
        return 0.0;
    }

    public static void chessMoveActionListener(Board board, MoveR move) {
        Place oldPlace = move.figure().getPlace();
        Place newPlace = move.newPlace();

        System.out.println("=====Found best move for white: " + move.figure().toString()
                + "\n=====To: " + newPlace.toChessNotation()
                + "\n=====With rating: " + move.moveRating()
                + "\n"
        );

        switch (move.action()) {
            case PAWN_TO_QUEEN -> {
                board.move(oldPlace.toChessNotation() + newPlace.toChessNotation());
                board.pawnToQueen(newPlace, move.figure().getColor());
            }
            case PAWN_MOVE_DUO -> {
                board.move(oldPlace.toChessNotation() + newPlace.toChessNotation());
                board.getBoard()[newPlace.getX()][newPlace.getY()].setAdditionalParameter(true);
            }
            case PAWN_MOVE -> {
                board.move(oldPlace.toChessNotation() + newPlace.toChessNotation());
            }
            default -> throw new IllegalStateException("Unexpected value: " + move.action());
        }
    }

    public static boolean isThisMoveLegal(Board board, MoveR move, String color) {
        // if next move I can do check
        Board futureBoard = board.clone();
        futureBoard.move(move.figure().getPlace().toChessNotation()
                + move.newPlace().toChessNotation());

        boolean isChecked = Arrays.stream(futureBoard.getBoard())
                .flatMap(Arrays::stream)
                .filter(figure -> !Objects.equals(figure.getColor(), color))
                .anyMatch(figure -> figure.isFigureCheckingKing(futureBoard));
        return isChecked;
    }
}
