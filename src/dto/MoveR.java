package dto;

import figure.Figure;
import service.Place;

public record MoveR(
        Figure figure,
        Place newPlace,
        Double moveRating
) {
}
