package org.aplicacao.program.application;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.boardGame.Position;
import org.aplicacao.program.chess.ChessMatch;
import org.aplicacao.program.chess.ChessPiece;
import org.aplicacao.program.chess.ChessPosition;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();

        while(true){
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(sc);

            System.out.print("Destination: ");
            ChessPosition destination = UI.readChessPosition(sc);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, destination);
        }
        }
}