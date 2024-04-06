package Models;

import enums.Colours;
import enums.PieceTypes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KingPiece extends Piece {

    public KingPiece(final Colours colour) {
        this.colour = colour;
        this.pieceType = PieceTypes.KING;
        this.cell = new Cell();
        if (colour.equals(Colours.BLACK)) {
            cell.setXPos(0);
        } else
            cell.setXPos(7);
        cell.setYPos(4);

    }
}