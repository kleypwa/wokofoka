package figure;

import dto.MoveR;
import service.Board;
import service.ChessRating;
import service.Place;

import java.util.List;

public class Empty implements Figure {
    private String color = "empty";
    private Place place;

    public Empty(Place place) {
        this.place = place;
    }

    @Override
    public Place getPlace() {
        return place;
    }

    @Override
    public Double getFigureRating() {
        return ChessRating.FIGURE_EMPTY;
    }

    @Override
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public void setAdditionalParameter(Boolean parameter) {

    }

    @Override
    public MoveR bestMove(Board board) {
        return null;
    }

    @Override
    public boolean isFigureCheckingKing(Board board) {
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
    public Empty clone() {
        try {
            Empty cloned = (Empty) super.clone();
            cloned.place = this.place != null ? this.place.clone() : null;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
