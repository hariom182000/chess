package Models;

import enums.Colours;
import enums.PieceTypes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BishopPiece extends Piece {

    public BishopPiece(final Colours colour, final Integer yPos) throws Exception {
        this.colour = colour;
        this.pieceType = PieceTypes.BISHOP;
        this.cell = new Cell();
        if (colour.equals(Colours.BLACK)) {
            cell.setXPos(0);
        } else
            cell.setXPos(7);
        if (yPos == 2 || yPos == 5)
            cell.setYPos(yPos);
        else
            throw new Exception();
    }
}