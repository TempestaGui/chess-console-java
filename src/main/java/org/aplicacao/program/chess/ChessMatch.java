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

    private void initialSetup(){ //responsavel por iniciar a partida colocando as pecas no tabuleiro
        board.placePiece(new Rook(board, Color.white), new Position(2,1));
        board.placePiece(new King(board, Color.Black), new Position(0, 4));
        board.placePiece(new King(board, Color.white), new Position(7, 4));

    }
}
