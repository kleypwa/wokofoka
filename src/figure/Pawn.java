package figure;

import dto.MoveR;
import service.*;

import java.util.*;

public class Pawn implements Figure {
    private String color;
    private Place place;
    private boolean isMoved = false;
    private String colorForCheck;

    public Pawn(String color, Place place) {
        this.color = color;
        this.place = place;
        this.colorForCheck = this.color.equalsIgnoreCase("white") ? "black" : "white";
    }

    @Override
    public List<MoveR> move(Board board) {
        List<MoveR> moves = new ArrayList<>();

        int x = place.getX();
        int y = place.getY();

        if (color.equalsIgnoreCase("white")) {
            Figure onMove;
            MoveR move = null;

            // left eating move
            if (x != 0) {
                onMove = board.getBoard()[x - 1][y + 1];
                if (onMove.getFigureRating() > 0 &&
                        onMove.getColor().equalsIgnoreCase("black")) {
                    Place newPlace = new Place(x - 1,y + 1);
                    if (y + 1 == 7) {
                        move = new MoveR(
                                this,
                                newPlace,
                                ChessRating.MOVE_PAWN_TO_QUEEN
                                        + ChessAnalyzeService.additionalRate(this, newPlace),
                                ChessAction.PAWN_TO_QUEEN
                        );

                        if (ChessAnalyzeService.isThisMoveLegal(board, move, colorForCheck))
                            moves.add(move);
                    } else {
                        move = new MoveR(
                                this,
                                newPlace,
                                onMove.getFigureRating()
                                        + ChessAnalyzeService.additionalRate(this, newPlace),
                                ChessAction.PAWN_MOVE
                        );

                        if (ChessAnalyzeService.isThisMoveLegal(board, move, colorForCheck))
                            moves.add(move);
                    }
                }
            }

            // right eating move
            if (x != 7) {
                onMove = board.getBoard()[x + 1][y + 1];
                if (onMove.getFigureRating() > 0 &&
                        onMove.getColor().equalsIgnoreCase("black")) {
                    Place newPlace = new Place(x + 1,y + 1);
                    if (y + 1 == 7) {
                        move = new MoveR(
                                this,
                                newPlace,
                                ChessRating.MOVE_PAWN_TO_QUEEN
                                        + ChessAnalyzeService.additionalRate(this, newPlace),
                                ChessAction.PAWN_TO_QUEEN
                        );

                        if (ChessAnalyzeService.isThisMoveLegal(board, move, colorForCheck))
                            moves.add(move);
                    } else {
                        move = new MoveR(
                                this,
                                newPlace,
                                onMove.getFigureRating()
                                        + ChessAnalyzeService.additionalRate(this, newPlace),
                                ChessAction.PAWN_MOVE
                        );

                        if (ChessAnalyzeService.isThisMoveLegal(board, move, colorForCheck))
                            moves.add(move);
                    }
                }
            }

            // move forward
            if (y != 7) {
                onMove = board.getBoard()[x][y + 1];
                if (onMove.getFigureRating() == 0) {
                    Place newPlace = new Place(x,y + 1);
                    if (y + 1 == 7) {
                        move = new MoveR(
                                this,
                                newPlace,
                                ChessRating.MOVE_PAWN_TO_QUEEN
                                        + ChessAnalyzeService.additionalRate(this, newPlace),
                                ChessAction.PAWN_TO_QUEEN
                        );

                        if (ChessAnalyzeService.isThisMoveLegal(board, move, colorForCheck))
                            moves.add(move);
                    } else {
                        move = new MoveR(
                                this,
                                newPlace,
                                ChessRating.MOVE_PAWN_FORWARD
                                        + ChessAnalyzeService.additionalRate(this, newPlace),
                                ChessAction.PAWN_MOVE
                        );

                        if (ChessAnalyzeService.isThisMoveLegal(board, move, colorForCheck))
                            moves.add(move);
                    }
                }
            }

            // move forward on two cells
            if (!isMoved) {
                if (board.getBoard()[x][y + 1] instanceof Empty) {
                    onMove = board.getBoard()[x][y + 2];
                    if (onMove instanceof Empty) {
                        Place newPlace = new Place(x, y + 2);
                        move = new MoveR(
                                this,
                                newPlace,
                                ChessRating.MOVE_PAWN_FORWARD_DUO
                                        + ChessAnalyzeService.additionalRate(this, newPlace),
                                ChessAction.PAWN_MOVE_DUO
                        );
                    }
                }
            }
            if (ChessAnalyzeService.isThisMoveLegal(board, move, colorForCheck))
                moves.add(move);


        } else {

            // Here will be functional for black, but now it doesn't need.
        }
        return moves;
    }

    @Override
    public Place getPlace() {
        return place;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public void setAdditionalParameter(Boolean parameter) {
        this.isMoved = true;
    }

    @Override
    public Double getFigureRating() {
        return ChessRating.FIGURE_PAWN;
    }

    @Override
    public MoveR bestMove(Board board) {
        return this.move(board).stream()
                .max(Comparator.comparingDouble(MoveR::moveRating))
                .orElse(null);
    }

    @Override
    public boolean isFigureCheckingKing(Board board) {
        Integer x = place.getX();
        Integer y = place.getY();


        if (x != 0 && x != 7) {
            if (ChessAnalyzeService.isKingHere(board, x + 1, y + 1, colorForCheck))
                return true;
            if (ChessAnalyzeService.isKingHere(board, x - 1, y + 1, colorForCheck))
                return true;
        }
        return false;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Pawn(" + this.place.toChessNotation() + ")";
    }

    @Override
    public Pawn clone() {
        try {
            Pawn cloned = (Pawn) super.clone();
            cloned.place = this.place != null ? this.place.clone() : null;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
