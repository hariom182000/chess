package services;

import Models.Cell;
import Models.Piece;
import POJOs.ValidMoveResponse;
import enums.Colours;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PawnMoveValidator implements MoveValidatorService {

    @Override
    public ValidMoveResponse validMove(final Map<Cell, Piece> cellPieceMap, final Piece piece, final Cell cell) {
        final ValidMoveResponse moveResponse = new ValidMoveResponse();
        if (!piece.getCell().getYPos().equals(cell.getYPos())) {
            moveResponse.setIsValidMove(Boolean.FALSE);
            return moveResponse;
        }
        final Scanner scanner = new Scanner(System.in);
        if (piece.getColour() == Colours.BLACK) {
            if (cell.getXPos() - piece.getCell().getXPos() > 2 || cell.getXPos() <= piece.getCell().getXPos()) {
                moveResponse.setIsValidMove(Boolean.FALSE);
                return moveResponse;
            }
            if (cellPieceMap.containsKey(new Cell(piece.getCell().getXPos() + 1, piece.getCell().getYPos()))) {
                moveResponse.setIsValidMove(Boolean.FALSE);
                return moveResponse;
            }
            if (cell.getXPos() - piece.getCell().getXPos() == 2 &&
                cellPieceMap.containsKey(new Cell(piece.getCell().getXPos() + 2, piece.getCell().getYPos()))) {
                moveResponse.setIsValidMove(Boolean.FALSE);
                return moveResponse;
            }
        } else {
            if (piece.getCell().getXPos() - cell.getXPos() > 2 || cell.getXPos() >= piece.getCell().getXPos()) {
                moveResponse.setIsValidMove(Boolean.FALSE);
                return moveResponse;
            }
            if (cellPieceMap.containsKey(new Cell(piece.getCell().getXPos() - 1, piece.getCell().getYPos()))) {
                moveResponse.setIsValidMove(Boolean.FALSE);
                return moveResponse;
            }
            if (piece.getCell().getXPos() - cell.getXPos() == 2 &&
                cellPieceMap.containsKey(new Cell(piece.getCell().getXPos() - 2, piece.getCell().getYPos()))) {
                moveResponse.setIsValidMove(Boolean.FALSE);
                return moveResponse;
            }
        }
        moveResponse.setIsValidMove(Boolean.TRUE);
        moveResponse.setCellReached(cell);
        final List<Cell> possibleKills = new ArrayList<>();
        if (piece.getColour() == Colours.BLACK) {
            if (cellPieceMap.containsKey(new Cell(cell.getXPos() + 1, cell.getYPos() + 1)) &&
                Colours.WHITE.equals(cellPieceMap.get(new Cell(cell.getXPos() + 1, cell.getYPos() + 1)).getColour())) {
                possibleKills.add(new Cell(cell.getXPos() + 1, cell.getYPos() + 1));
            }
            if (cellPieceMap.containsKey(new Cell(cell.getXPos() + 1, cell.getYPos() - 1))
                &&
                Colours.WHITE.equals(cellPieceMap.get(new Cell(cell.getXPos() + 1, cell.getYPos() - 1)).getColour())) {
                possibleKills.add(new Cell(cell.getXPos() + 1, cell.getYPos() - 1));
            }
        } else {
            if (cellPieceMap.containsKey(new Cell(cell.getXPos() - 1, cell.getYPos() + 1))

                &&
                Colours.BLACK.equals(cellPieceMap.get(new Cell(cell.getXPos() - 1, cell.getYPos() + 1)).getColour())) {
                possibleKills.add(new Cell(cell.getXPos() - 1, cell.getYPos() + 1));
            }
            if (cellPieceMap.containsKey(new Cell(cell.getXPos() - 1, cell.getYPos() - 1))
                &&
                Colours.BLACK.equals(cellPieceMap.get(new Cell(cell.getXPos() - 1, cell.getYPos() - 1)).getColour())) {
                possibleKills.add(new Cell(cell.getXPos() - 1, cell.getYPos() - 1));
            }
        }

        Integer killIndex = -1;
        if (possibleKills.size() == 2) {
            System.out.println("press 1 to kill piece at right, 2 to kill piece at left");
            int m = scanner.nextInt();
            if (m == 1)
                killIndex = 0;
            if (m == 2)
                killIndex = 1;
        }
        if (possibleKills.size() == 1) {
            System.out.println("press 1 to kill piece");
            int m = scanner.nextInt();
            if (m == 1)
                killIndex = 0;
        }
        if (killIndex >= 0) {
            moveResponse.setIsPieceKilled(Boolean.TRUE);
            moveResponse.setKilledPieceCell(possibleKills.get(killIndex));
            moveResponse.setPieceKilled(cellPieceMap.get(possibleKills.get(killIndex)));
        }
        return moveResponse;
    }
}