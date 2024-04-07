package services;

import Models.Cell;
import Models.Piece;
import POJOs.ValidMoveResponse;

import java.util.Map;

public interface MoveValidatorService {

    ValidMoveResponse validMove(Map<Cell, Piece> cellPieceMap, Piece piece, Cell cell);
}