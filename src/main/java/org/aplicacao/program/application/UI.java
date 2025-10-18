package org.aplicacao.program.application;

import org.aplicacao.program.chess.ChessPiece;

public class UI {
    public static void printBoard(ChessPiece[][] pieces) {
        for(int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " "); //imprimir o rotulo lateral de 1 a 8
            for(int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j]); //imprimir a peca na posicao i, j
            }
            System.out.println(); //quebra de linha
        }
        System.out.println("  a b c d e f g h"); //rotulo inferior
    }

    private static void printPiece(ChessPiece piece){ // metodo auxiliar que vai ser responsavel por imprimir uma peca ou "-" caso nao tenha
        if(piece == null){
            System.out.print("-");
        }
        else {
            System.out.print(piece);
        }
        System.out.print(" "); // para nao ficar colado
    }
}
