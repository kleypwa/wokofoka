package figure;

import dto.MoveR;
import service.Board;
import service.Place;

public class Rook implements Figure {
    private String color;
    private Place place;

    public Rook(String color, Place place) {
        this.color = color;
        this.place = place;
    }

    @Override
    public MoveR move(Board board) {
        return null;
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
    public Double getFigureRating() {
        return 4.;
    }
}
