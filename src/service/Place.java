package service;

public class Place {
    Integer letter;
    Integer number;

    public Place(Integer letter, Integer number) {
        this.letter = letter;
        this.number = number;
    }

    public Place() {
    }

    public String toChessNotation() {
        char column = (char) ('a' + this.letter);
        int row = this.number + 1;
        return "" + column + row;
    }

    public Integer getLetter() {
        return letter;
    }

    public void setLetter(Integer letter) {
        this.letter = letter;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
