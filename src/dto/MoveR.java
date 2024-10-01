package dto;

import figure.Figure;
import service.ChessAction;
import service.Place;

public record MoveR(
        Figure figure,
        Place newPlace,
        Double moveRating,
        ChessAction action
) {
}
