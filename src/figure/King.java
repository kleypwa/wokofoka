package figure;

import dto.MoveR;
import service.Board;
import service.Place;

public class King implements Figure {
    private String color;
    private Place place;

    public King(String color, Place place) {
        this.color = color;
        this.place = place;
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
        return 10.;
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
}
