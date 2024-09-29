package figure;

import dto.MoveR;
import service.Board;
import service.Place;

public class Pawn implements Figure {
    private String color;
    private Place place;
    private boolean isMoved = false;
    private Double figureRating = 1.;

    public Pawn(String color, Place place) {
        this.color = color;
        this.place = place;
    }

    @Override
    public MoveR move(Board board) {
        Double rating = -1.;
        int x = place.getLetter();
        int y = place.getNumber();
        int newX = x, newY = y;


        if (color.equalsIgnoreCase("white")) {
            if (isMoved) {
                Figure onMove;
                // left eating move
                if (x != 0) {
                    onMove = board.getBoard()[x - 1][y + 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x - 1;
                        newY = y + 1;
                    }
                }

                // right eating move
                if (x != 7) {
                    onMove = board.getBoard()[x + 1][y + 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x + 1;
                        newY = y + 1;
                    }
                }

                // move forward
                if (y != 7) {
                    onMove = board.getBoard()[x][y + 1];
                    if (onMove.getFigureRating() == 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x;
                        newY = y + 1;
                    }
                }

            } else {
                Figure onMove;
                // left eating move
                if (y != 0) {
                    onMove = board.getBoard()[x + 1][y - 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x + 1;
                        newY = y - 1;
                    }
                }

                // right eating move
                if (y != 7) {
                    onMove = board.getBoard()[x + 1][y + 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x + 1;
                        newY = y + 1;
                    }
                }

                // move forward
                if (x != 7) {
                    onMove = board.getBoard()[x + 1][y];
                    if (onMove instanceof Empty) {
                        rating = onMove.getFigureRating();
                        newX = x + 1;
                        newY = y;
                    }
                }

                // move forward on two cells
                if (board.getBoard()[x + 1][y] instanceof Empty) {
                    onMove = board.getBoard()[x + 2][y];
                    if (onMove instanceof Empty) {
                        rating = 1.;
                        newX = x + 2;
                        newY = y;
                    }
                }
            }
        } else {
            if (isMoved) {
                Figure onMove;
                // left eating move
                if (y != 0) {
                    onMove = board.getBoard()[x - 1][y - 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x - 1;
                        newY = y - 1;
                    }
                }

                // right eating move
                if (y != 7) {
                    onMove = board.getBoard()[x - 1][y + 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x - 1;
                        newY = y + 1;
                    }
                }

                // move forward
                if (x != 0) {
                    onMove = board.getBoard()[x - 1][y];
                    if (onMove.getFigureRating() == 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x;
                        newY = y - 1;
                    }
                }

            } else {
                Figure onMove;
                // left eating move
                if (x != 0) {
                    onMove = board.getBoard()[x - 1][y - 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x - 1;
                        newY = y - 1;
                    }
                }

                // right eating move
                if (x != 7) {
                    onMove = board.getBoard()[x + 1][y - 1];
                    if (onMove.getFigureRating() > 0 && onMove.getFigureRating() > rating) {
                        rating = onMove.getFigureRating();
                        newX = x + 1;
                        newY = y - 1;
                    }
                }

                // move forward
                if (y != 0) {
                    onMove = board.getBoard()[x][y - 1];
                    if (onMove instanceof Empty) {
                        rating = onMove.getFigureRating();
                        newX = x;
                        newY = y - 1;
                    }
                }

                // move forward on two cells
                if (board.getBoard()[x][y - 1] instanceof Empty) {
                    onMove = board.getBoard()[x][y - 2];
                    if (onMove instanceof Empty) {
                        rating = 1.;
                        newX = x;
                        newY = y - 2;
                    }
                }
            }
        }
        return new MoveR(board.getBoard()[x][y], new Place(newX, newY), rating);
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
        return figureRating;
    }

    @Override
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
