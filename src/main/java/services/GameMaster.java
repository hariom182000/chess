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
import POJOs.ValidMoveResponse;
import enums.Colours;
import enums.PieceTypes;
import factories.PieceMoveValidatorFactory;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Data
public class GameMaster {

    final private PieceMoveValidatorFactory pieceMoveValidatorFactory = PieceMoveValidatorFactory.getInstance();

    final private Player playerA;

    final private Player playerB;

    final private ChessBoard chessBoard;

    private Integer move;

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

    private Boolean canMovePiece(final Cell cell, final Colours colour) {
        if (!cell.isValidCell(ChessBoard.boardSize))
            return Boolean.FALSE;
        if (chessBoard.getCellPieceMap().containsKey(cell)) {
            return chessBoard.getCellPieceMap().get(cell).getColour().equals(colour);
        }
        return Boolean.FALSE;
    }

    private Colours getCurrentPlayersColor() {
        if (move % 2 == 0)
            return Colours.WHITE;
        return Colours.BLACK;
    }

    private Boolean isValidCellToMove(final Cell cell, final Piece piece) {
        if (!cell.isValidCell(ChessBoard.boardSize))
            return Boolean.FALSE;
        if (cell.getXPos().equals(piece.getCell().getXPos()) && cell.getYPos().equals(piece.getCell().getXPos()))
            return Boolean.FALSE;
        return Boolean.TRUE;
    }

    public void play() {
        final Scanner scanner = new Scanner(System.in);
        while (true) {
            chessBoard.printBoard();
            final Colours currentPlayersColor = getCurrentPlayersColor();
            System.out.println(
                    currentPlayersColor.toString() + " turn, give co-ordinates of the piece you want to move");
            Integer xPos = scanner.nextInt();
            Integer yPos = scanner.nextInt();
            Cell cell = new Cell(xPos, yPos);
            if (!canMovePiece(cell, currentPlayersColor)) {
                System.out.println("invalid co-ordinates");
                continue;
            }
            final Piece selectedPiece = chessBoard.getCellPieceMap().get(cell);
            System.out.println("give co-ordinates where you want to move");
            xPos = scanner.nextInt();
            yPos = scanner.nextInt();
            cell = new Cell(xPos, yPos);
            if (!isValidCellToMove(cell, selectedPiece)) {
                System.out.println("invalid co-ordinates");
                continue;
            }
            MoveValidatorService moveValidatorService =
                    pieceMoveValidatorFactory.getMoveValidatorService(selectedPiece);
            ValidMoveResponse
                    moveResponse = moveValidatorService.validMove(chessBoard.getCellPieceMap(), selectedPiece, cell);
            if (!moveResponse.getIsValidMove()) {
                System.out.println("invalid move");
                continue;
            }
            if (moveResponse.getIsPieceKilled()) {
                if (moveResponse.getPieceKilled().getPieceType().equals(PieceTypes.KING)) {
                    System.out.println("game over " + currentPlayersColor + " won the game");
                    break;
                }
                if (currentPlayersColor.equals(Colours.BLACK)) {
                    playerA.getPieces().remove(moveResponse.getPieceKilled());
                } else {
                    playerB.getPieces().remove(moveResponse.getPieceKilled());
                }
                chessBoard.getCellPieceMap().remove(moveResponse.getCellReached(), moveResponse.getPieceKilled());
            }
            chessBoard.getCellPieceMap().remove(selectedPiece.getCell());
            selectedPiece.setCell(moveResponse.getCellReached());
            chessBoard.getCellPieceMap().put(moveResponse.getCellReached(), selectedPiece);
            move++;
        }
    }

}
