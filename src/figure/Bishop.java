package figure;

import dto.MoveR;
import service.Board;
import service.ChessRating;
import service.Place;

import java.util.List;

public class Bishop implements Figure {
    private String color;
    private Place place;

    public Bishop(String color, Place place) {
        this.color = color;
        this.place = place;
    }

    @Override
    public List<MoveR> move(Board board) {
        return List.of();
    }

    @Override
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
        return ChessRating.FIGURE_BISHOP;
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
    public Bishop clone() {
        try {
            Bishop cloned = (Bishop) super.clone();
            cloned.place = this.place != null ? this.place.clone() : null;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
