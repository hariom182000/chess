package Models;

import enums.Colours;
import enums.PieceTypes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KnightPiece extends Piece {

    public KnightPiece(final Colours colour, final Integer yPos) throws Exception {
        this.pieceType = PieceTypes.KNIGHT;
        this.colour = colour;
        this.cell = new Cell();
        if (colour.equals(Colours.BLACK)) {
            cell.setXPos(0);

        } else
            cell.setXPos(7);
        if (yPos == 1 || yPos == 6)
            cell.setYPos(yPos);
        else
            throw new Exception();
    }
}