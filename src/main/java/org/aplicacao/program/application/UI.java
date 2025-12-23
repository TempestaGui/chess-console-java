package org.aplicacao.program.application;

import org.aplicacao.program.chess.ChessMatch;
import org.aplicacao.program.chess.ChessPiece;
import org.aplicacao.program.chess.ChessPosition;
import org.aplicacao.program.chess.Color;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    public static void printMatch(ChessMatch chesMatch, List<ChessPiece> captured){
        printBoard(chesMatch.getPieces());
        System.out.println();
        printCapturedPieces(captured);
        System.out.println();
        System.out.println("Turn: "+chesMatch.getTurn());
        System.out.println("Waiting player: "+chesMatch.getCurrentPlayer());
    }


    public static void printBoard(ChessPiece[][] pieces) {
        for(int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " "); //imprimir o rotulo lateral de 1 a 8
            for(int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false); //imprimir a peca na posicao i, j
            }
            System.out.println(); //quebra de linha
        }
        System.out.println("  a b c d e f g h"); //rotulo inferior
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for(int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " "); //imprimir o rotulo lateral de 1 a 8
            for(int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]); //imprimir a peca na posicao i, j com o fundo colorido
            }
            System.out.println(); //quebra de linha
        }
        System.out.println("  a b c d e f g h"); //rotulo inferior
    }

    private static void printPiece(ChessPiece piece, boolean background){ // metodo auxiliar que vai ser responsavel por imprimir uma peca ou "-" caso nao tenha
        if(background){
            System.out.print(ANSI_PURPLE_BACKGROUND);
        }
        if(piece == null){
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if(piece.getColor() == Color.Blue){
                System.out.print(ANSI_BLUE + piece + ANSI_RESET);
            } else{
                System.out.print(ANSI_RED + piece + ANSI_RESET);
            }
        }
        System.out.print(" "); // para nao ficar colado
    }

    private static void printCapturedPieces(List<ChessPiece> captured){
        List<ChessPiece> blue = captured.stream()
                .filter(x -> x.getColor() == Color.Blue).collect(Collectors.toList());
        List<ChessPiece> red = captured.stream()
                .filter(x -> x.getColor() == Color.Red).collect(Collectors.toList());

        System.out.println("Captured pieces: ");
        System.out.print("Blue: ");
        System.out.print(ANSI_BLUE);
        System.out.println(Arrays.toString(blue.toArray()));
        System.out.print(ANSI_RESET);

        System.out.print("Red: ");
        System.out.print(ANSI_RED);
        System.out.println(Arrays.toString(red.toArray()));
        System.out.print(ANSI_RESET);
    }
}
