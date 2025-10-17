package org.aplicacao.program.application;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.boardGame.Position;
import org.aplicacao.program.chess.ChessMatch;

public class Main {
    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        UI.printBoard(chessMatch.getPieces());
        }
}