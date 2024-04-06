package Models;

import enums.Colours;
import enums.PieceTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Piece {
    protected PieceTypes pieceType;

    protected Colours colour;

    protected Cell cell;

    public void printPiece() {
        switch (this.pieceType) {
            case KING:
                System.out.print("KG");
                break;
            case QUEEN:
                System.out.print("QN");
                break;
            case PAWN:
                System.out.print("PN");
                break;
            case ROOK:
                System.out.print("RK");
                break;
            case BISHOP:
                System.out.print("BP");
                break;
            case KNIGHT:
                System.out.print("KN");
                break;
        }
        if (colour.equals(Colours.BLACK)) {
            System.out.print("_B");
        } else
            System.out.print("_W");
    }

}