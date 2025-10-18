package org.aplicacao.program.chess.pieces;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.chess.ChessPiece;
import org.aplicacao.program.chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "♔";
    }
}
