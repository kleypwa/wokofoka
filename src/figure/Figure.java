package figure;

import dto.MoveR;
import service.Board;
import service.Place;

import java.util.List;

public interface Figure extends Cloneable {
    List<MoveR> move(Board board);
    MoveR bestMove(Board board);
    boolean isFigureCheckingKing(Board board);

    String getColor();
    void setPlace(Place newPlace);
    void setAdditionalParameter(Boolean parameter);
    Place getPlace();
    Double getFigureRating();

    Figure clone();
}
