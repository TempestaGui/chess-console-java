package org.aplicacao.program.chess.pieces;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.chess.ChessPiece;
import org.aplicacao.program.chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "♜";
    }
}
