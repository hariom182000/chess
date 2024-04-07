package services;

import Models.Cell;
import Models.Piece;
import POJOs.ValidMoveResponse;

import java.util.Map;

public class RookMoveValidator implements MoveValidatorService {
    @Override
    public ValidMoveResponse validMove(final Map<Cell, Piece> cellPieceMap, final Piece piece, final Cell cell) {
        return null;
    }
}