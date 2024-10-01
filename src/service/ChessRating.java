package service;

public interface ChessRating {
    // Moves
    Double MOVE_PAWN_FORWARD = 0.5;
    Double MOVE_PAWN_FORWARD_DUO = 0.6;
    Double MOVE_PAWN_TO_QUEEN = 12.;

    // Figures
    Double FIGURE_PAWN = 1.;
    Double FIGURE_BISHOP = 3.;
    Double FIGURE_KNIGHT = 3.;
    Double FIGURE_ROOK = 4.;
    Double FIGURE_QUEEN = 8.;
    Double FIGURE_KING = 10.;
    Double FIGURE_EMPTY = 0.;

    // Some additional coefficients
    Double CENTER_COEFFICIENT1 = 0.001;
    Double CENTER_COEFFICIENT2 = 0.002;
    Double CENTER_COEFFICIENT3 = 0.003;
    Double CENTER_COEFFICIENT4 = 0.004;

    Double ERROR = -1000.;
}
