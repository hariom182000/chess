package services;

import Models.BishopPiece;
import Models.Cell;
import Models.ChessBoard;
import Models.KingPiece;
import Models.KnightPiece;
import Models.PawnPiece;
import Models.Piece;
import Models.Player;
import Models.QueenPiece;
import Models.RookPiece;
import enums.Colours;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class GameMaster {

    final private Player playerA;

    final private Player playerB;

    final private ChessBoard chessBoard;

    final private Integer move;

    public GameMaster(final String playerAName, final String playerBName) throws Exception {
        playerA = new Player();
        playerB = new Player();
        chessBoard = new ChessBoard();
        move = 0;
        initilaizeBoard();
        initilaizePlayerA(playerAName);
        initilaizePlayerB(playerBName);
    }

    private void initilaizeBoard() {
        final List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells.add(new Cell(i, j));
            }
        }
        chessBoard.setCells(cells);
    }

    private void initilaizePlayerA(final String playerName) throws Exception {
        playerA.setPlayerName(playerName);
        playerA.setColour(Colours.WHITE);
        playerA.setPieces(assignPieces(Colours.WHITE));
    }

    private void initilaizePlayerB(final String playerName) throws Exception {
        playerB.setPlayerName(playerName);
        playerB.setColour(Colours.BLACK);
        playerB.setPieces(assignPieces(Colours.BLACK));
    }

    private Set<Piece> assignPieces(final Colours colours) throws Exception {
        final Set<Piece> allPieces = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            allPieces.add(new PawnPiece(colours, i));
            if (i == 1 || i == 6) {
                allPieces.add(new KnightPiece(colours, i));
            }
            if (i == 0) {
                allPieces.add(new KingPiece(colours));
                allPieces.add(new QueenPiece(colours));
            }
            if (i == 2 || i == 5) {
                allPieces.add(new BishopPiece(colours, i));
            }
            if (i == 0 || i == 7) {
                allPieces.add(new RookPiece(colours, i));
            }

        }
        allPieces.forEach(piece -> chessBoard.getCellPieceMap().put(piece.getCell(), piece));
        return allPieces;
    }

    public void play() {
        chessBoard.printBoard();
    }

}
