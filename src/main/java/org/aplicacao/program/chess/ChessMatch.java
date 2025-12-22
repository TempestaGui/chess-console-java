package org.aplicacao.program.chess;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.boardGame.Piece;
import org.aplicacao.program.boardGame.Position;
import org.aplicacao.program.chess.Exceptions.ChessException;
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
        placeNewPiece('c', 1 ,new Rook(board, Color.white));
        placeNewPiece('c', 2 ,new Rook(board, Color.white));
        placeNewPiece('d', 2 ,new Rook(board, Color.white));
        placeNewPiece('e', 2 ,new Rook(board, Color.white));
        placeNewPiece('e', 1 ,new Rook(board, Color.white));
        placeNewPiece('d', 1 ,new King(board, Color.white));

        placeNewPiece('c', 7 ,new Rook(board, Color.Black));
        placeNewPiece('c', 8 ,new Rook(board, Color.Black));
        placeNewPiece('d', 7 ,new Rook(board, Color.Black));
        placeNewPiece('e', 7 ,new Rook(board, Color.Black));
        placeNewPiece('e', 8 ,new Rook(board, Color.Black));
        placeNewPiece('d', 8 ,new King(board, Color.Black));

    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition destinationPosition){
        Position source = sourcePosition.toPosition();
        Position destination = destinationPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, destination);

        Piece capturedPiece = makeMove(source, destination);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position destination){
        Piece p  = board.removePiece(source);
        Piece capturedPiece = board.removePiece(destination);
        board.placePiece(p, destination);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Piece dont exist in this position "+position);
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece "+position);
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("the chosen piece can`t move to target position "+target);
        }
    }
}
