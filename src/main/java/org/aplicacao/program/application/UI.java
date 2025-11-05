package org.aplicacao.program.application;

import org.aplicacao.program.chess.ChessPiece;
import org.aplicacao.program.chess.ChessPosition;
import org.aplicacao.program.chess.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {


    //codigo especias das cores para imprimir no console (Cores do texto)
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //codigo especias das cores para imprimir no console (Cores do fundo)
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    public static ChessPosition readChessPosition(Scanner sc){
        try{
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1)); //recortando e convertendo o string a partir da posicao 1
            return new ChessPosition(column, row);
        }
        catch(RuntimeException e){
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        }
    }


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
            if(piece.getColor() == Color.white){
                System.out.print(ANSI_BLUE + piece + ANSI_RESET);
            } else{
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }
        }
        System.out.print(" "); // para nao ficar colado
    }
}
