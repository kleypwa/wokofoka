package service;

import figure.Figure;

class FigureCheckResult {
    private final Figure figure;
    private final boolean isCheckingKing;

    public FigureCheckResult(Figure figure, boolean isCheckingKing) {
        this.figure = figure;
        this.isCheckingKing = isCheckingKing;
    }

    public Figure getFigure() {
        return figure;
    }

    public boolean isCheckingKing() {
        return isCheckingKing;
    }
}

