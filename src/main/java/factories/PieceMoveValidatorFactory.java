package factories;

import Models.Piece;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import services.BishopMoveValidator;
import services.KingMoveValidator;
import services.KnightMoveValidator;
import services.MoveValidatorService;
import services.PawnMoveValidator;
import services.QueenMoveValidator;
import services.RookMoveValidator;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PieceMoveValidatorFactory {

    private static final PieceMoveValidatorFactory instance = new PieceMoveValidatorFactory();

    private final MoveValidatorService rookMoveValidator = new RookMoveValidator();

    private final MoveValidatorService kingMoveValidator = new KingMoveValidator();

    private final MoveValidatorService knightMoveValidator = new KnightMoveValidator();

    private final MoveValidatorService pawnMoveValidator = new PawnMoveValidator();

    private final MoveValidatorService queenMoveValidator = new QueenMoveValidator();

    private final MoveValidatorService bishopMoveValidator = new BishopMoveValidator();

    public static PieceMoveValidatorFactory getInstance() {
        return instance;
    }

    public MoveValidatorService getMoveValidatorService(final Piece piece) {
        switch (piece.getPieceType()) {
            case KING:
                return kingMoveValidator;
            case QUEEN:
                return queenMoveValidator;
            case PAWN:
                return pawnMoveValidator;
            case ROOK:
                return rookMoveValidator;
            case KNIGHT:
                return knightMoveValidator;
            case BISHOP:
                return bishopMoveValidator;
        }
        return null;
    }
}