package figure;

import dto.MoveR;
import service.Board;
import service.Place;

public interface Figure {
    MoveR move(Board board);
    String getColor();
    void setPlace(Place newPlace);
    Place getPlace();
    Double getFigureRating();
}
