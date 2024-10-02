package figure;

import dto.MoveR;
import service.Board;
import service.ChessAnalyzeService;
import service.ChessRating;
import service.Place;

import java.util.List;

public class King implements Figure {
    private final String colorForCheck;
    private String color;
    private Place place;

    public King(String color, Place place) {
        this.color = color;
        this.place = place;
        this.colorForCheck = this.color.equalsIgnoreCase("white") ? "black" : "white";
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

    }

    @Override
    public Double getFigureRating() {
        return ChessRating.FIGURE_KING;
    }

    @Override
    public MoveR bestMove(Board board) {
        return null;
    }

    @Override
    public boolean isFigureCheckingKing(Board board) {
        Integer x = place.getX();
        Integer y = place.getY();

        if (ChessAnalyzeService.isKingHere(board, x + 1, y + 1, colorForCheck))
            return true;
        if (ChessAnalyzeService.isKingHere(board, x + 1, y, colorForCheck))
            return true;
        if (ChessAnalyzeService.isKingHere(board, x + 1, y - 1, colorForCheck))
            return true;
        if (ChessAnalyzeService.isKingHere(board, x, y + 1, colorForCheck))
            return true;
        if (ChessAnalyzeService.isKingHere(board, x, y - 1, colorForCheck))
            return true;
        if (ChessAnalyzeService.isKingHere(board, x - 1, y + 1, colorForCheck))
            return true;
        if (ChessAnalyzeService.isKingHere(board, x - 1, y, colorForCheck))
            return true;
        if (ChessAnalyzeService.isKingHere(board, x - 1, y - 1, colorForCheck))
            return true;

        return false;
    }

    @Override
    public List<MoveR> move(Board board) {
        return List.of();
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public King clone() {
        try {
            King cloned = (King) super.clone();
            cloned.place = this.place != null ? this.place.clone() : null;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
