package org.aplicacao.program.chess;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.boardGame.Position;
import org.aplicacao.program.chess.pieces.King;
import org.aplicacao.program.chess.pieces.Rook;

public class ChessMatch {

    private Board board;

    public ChessMatch(){
        board = new Board(8,8);
        initialSetup();
    }

    public ChessPiece[][] getPieces(){ //liberando para o programa uma matriz chessPiece para que o programa conheça apenas a camada de xadrez e nao a camada de tabuleiro
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //quantidade de linhas e colunas do tabuleiro
        for(int i = 0; i < board.getRows(); i++){   //percorrer a matriz de pecas e para cada vamos fazer um downCast para chessPiece
            for(int j = 0; j < board.getColumns(); j++){
                mat[i][j] = (ChessPiece) board.piece(i,j); //para cada posicao i, j do tabuleiro a matriz mat vai receber a peca da posicao
            }
        }
        return mat;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){ //responsavel por iniciar a partida colocando as pecas no tabuleiro
        placeNewPiece('b', 6 ,new Rook(board, Color.white));
        placeNewPiece('e', 8 ,new King(board, Color.Black));
        placeNewPiece('e', 1 ,new King(board, Color.white));

    }
}
