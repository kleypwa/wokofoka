package figure;

import dto.MoveR;
import service.Board;
import service.Place;

public class Empty implements Figure {
    private String color = "empty";
    private Place place;

    public Empty() {
    }

    public Empty(Place place) {
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
        return 0.;
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
