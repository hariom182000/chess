package POJOs;

import Models.Cell;
import Models.Piece;
import lombok.Data;

@Data
public class ValidMoveResponse {

    private Boolean isValidMove;

    private Boolean isPieceKilled;

    private Piece pieceKilled;

    private Cell killedPieceCell;

    private Cell cellReached;

}