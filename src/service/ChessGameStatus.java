package service;

public enum ChessGameStatus {
    WHITE_MOVE,
    BLACK_MOVE,
    ACTIVE,         // Игра активна, ходят игроки
    CHECK,          // Шах королю
    CHECKMATE,      // Мат, игра окончена
    DRAW,           // Ничья (например, соглашение или троекратное повторение позиции)
    ERROR
}
