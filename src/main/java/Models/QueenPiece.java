package Models;

import enums.Colours;
import enums.PieceTypes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueenPiece extends Piece {

    public QueenPiece(final Colours colour) {
        this.colour = colour;
        this.pieceType = PieceTypes.QUEEN;
        this.cell = new Cell();
        if (colour.equals(Colours.BLACK)) {
            this.cell.setXPos(0);
        } else
            this.cell.setXPos(7);

        this.cell.setYPos(3);
    }
}