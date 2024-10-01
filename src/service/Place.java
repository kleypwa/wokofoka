package service;

public class Place implements Cloneable{
    Integer x;
    Integer y;

    public Place(Integer letter, Integer number) {
        this.x = letter;
        this.y = number;
    }

    public Place() {
    }

    public String toChessNotation() {
        char column = (char) ('a' + this.x);
        int row = this.y + 1;
        return "" + column + row;
    }

    @Override
    public Place clone() {
        try {
            return (Place) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning not supported", e);
        }
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
