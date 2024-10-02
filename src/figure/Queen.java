package figure;

import dto.MoveR;
import service.Board;
import service.ChessRating;
import service.Place;

import java.util.List;

public class Queen implements Figure {
    private final String colorForCheck;
    private String color;
    private Place place;

    public Queen(String color, Place place) {
        this.color = color;
        this.place = place;
        this.colorForCheck = this.color.equalsIgnoreCase("white") ? "black" : "white";
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
        return ChessRating.FIGURE_QUEEN;
    }

    @Override
    public MoveR bestMove(Board board) {
        return null;
    }

    @Override
    public boolean isFigureCheckingKing(Board board) {
        Integer x = place.getX();
        Integer y = place.getY();

        //l
        while (x - 1 >= 0 && y + 1 <= 7) {
            x -= 1;
            y += 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }
        x = place.getX();
        y = place.getY();


        //r
        while (x + 1 <= 7 && y + 1 <= 7) {
            x += 1;
            y += 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }
        x = place.getX();
        y = place.getY();

        //ld
        while (x + 1 <= 7 && y - 1 >= 0) {
            x += 1;
            y -= 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }
        x = place.getX();
        y = place.getY();

        //rd
        while (x - 1 >= 0 && y - 1 >= 0) {
            x -= 1;
            y -= 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }
        x = place.getX();
        y = place.getY();

        //up
        while (y + 1 <= 7) {
            y += 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }
        y = place.getY();


        //down
        while (y - 1 >= 0) {
            y -= 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }
        y = place.getY();

        //left
        while (x - 1 >= 0) {
            x -= 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }
        x = place.getX();

        //right
        while (x + 1 <= 7) {
            x += 1;
            Figure figure = board.getBoard()[x][y];
            if (figure instanceof King && figure.getColor().equalsIgnoreCase(colorForCheck))
                return true;
            else if (!(figure instanceof Empty)) {
                break;
            }
        }

        return false;
    }

    @Override
    public Queen clone() {
        try {
            Queen cloned = (Queen) super.clone();
            cloned.place = this.place != null ? this.place.clone() : null;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }
}
