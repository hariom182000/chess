package services;

import Models.Cell;
import Models.Piece;
import POJOs.ValidMoveResponse;

import java.util.Map;

public class KingMoveValidator implements MoveValidatorService {
    @Override
    public ValidMoveResponse validMove(Map<Cell, Piece> cellPieceMap, Piece piece, Cell cell) {
        return null;
    }
}