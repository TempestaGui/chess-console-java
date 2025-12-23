package org.aplicacao.program.chess;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.boardGame.Piece;
import org.aplicacao.program.boardGame.Position;

public abstract class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public ChessPosition getChessPosition() { return ChessPosition.fromPosition(position); }

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece cp = (ChessPiece) getBoard().piece(position);
        return cp != null && cp.getColor() != color;
    }
}
