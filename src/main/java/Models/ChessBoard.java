package Models;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ChessBoard {

    final public static Integer boardSize = 8;

    public Map<Cell, Piece> cellPieceMap = new HashMap<>();

    public List<Cell> cells;

    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            System.out.print("|| ");
            for (int j = 0; j < boardSize; j++) {
                final Cell cell = new Cell(i, j);
                if (cellPieceMap.containsKey(cell)) {
                    cellPieceMap.get(cell).printPiece();
                } else
                    System.out.print("    ");
                System.out.print(" || ");
            }
            System.out.println("\n");
        }
    }
}