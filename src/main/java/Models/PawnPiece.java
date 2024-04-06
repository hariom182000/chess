package Models;

import enums.Colours;
import enums.PieceTypes;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PawnPiece extends Piece {

    public PawnPiece(final Colours colour, final Integer yPos) {
        this.colour = colour;
        this.pieceType = PieceTypes.PAWN;
        if (colour.equals(Colours.BLACK)) {
            this.cell = new Cell(1, yPos);
        } else
            this.cell = new Cell(6, yPos);
    }
}