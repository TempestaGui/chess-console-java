package org.aplicacao.program.chess;

import org.aplicacao.program.boardGame.Board;
import org.aplicacao.program.boardGame.Piece;
import org.aplicacao.program.boardGame.Position;
import org.aplicacao.program.chess.Exceptions.ChessException;
import org.aplicacao.program.chess.pieces.King;
import org.aplicacao.program.chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private final Board board;
    private int turn;
    private Color currentPlayer;
    private boolean check;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch(){
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.Blue;
        initialSetup();
    }

    public int getTurn(){
        return turn;
    }
    public Color getCurrentPlayer(){
        return currentPlayer;
    }
    public boolean getCheck(){
        return check;
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
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){ //responsavel por iniciar a partida colocando as pecas no tabuleiro
        placeNewPiece('c', 1 ,new Rook(board, Color.Blue));
        placeNewPiece('c', 2 ,new Rook(board, Color.Blue));
        placeNewPiece('d', 2 ,new Rook(board, Color.Blue));
        placeNewPiece('e', 2 ,new Rook(board, Color.Blue));
        placeNewPiece('e', 1 ,new Rook(board, Color.Blue));
        placeNewPiece('d', 1 ,new King(board, Color.Blue));

        placeNewPiece('c', 7 ,new Rook(board, Color.Red));
        placeNewPiece('c', 8 ,new Rook(board, Color.Red));
        placeNewPiece('d', 7 ,new Rook(board, Color.Red));
        placeNewPiece('e', 7 ,new Rook(board, Color.Red));
        placeNewPiece('e', 8 ,new Rook(board, Color.Red));
        placeNewPiece('d', 8 ,new King(board, Color.Red));

    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition destinationPosition){
        Position source = sourcePosition.toPosition();
        Position destination = destinationPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, destination);

        Piece capturedPiece = makeMove(source, destination);

        if(testCheck(currentPlayer)){
            undoMove(source, destination, capturedPiece);
            throw new ChessException("You can`t put yourself in check!!");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position destination){
        Piece p  = board.removePiece(source);
        Piece capturedPiece = board.removePiece(destination);
        board.placePiece(p, destination);

        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturetPiece){
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if(capturetPiece != null){
            board.placePiece(capturetPiece, target);
            capturedPieces.remove(capturetPiece);
            piecesOnTheBoard.add(capturetPiece);
        }
    }

    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("Piece dont exist in this position "+position);
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("the chosen piece is not yours, "+getCurrentPlayer() +" is yours piece!!");
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

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.Blue) ? Color.Red : Color.Blue;
    }

    private Color opponent(Color color){
        return (color == Color.Blue) ? Color.Red : Color.Blue;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream()
                .filter(x -> ((ChessPiece)x).getColor() == color).toList();

        for(Piece p: list){
            if(p instanceof King){
                return (ChessPiece)p;
            }
        }
        throw new IllegalStateException("There is no "+color+" King on the board!");
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces =  piecesOnTheBoard.stream()
                .filter(x -> ((ChessPiece)x).getColor() == opponent(color)).toList();

        for(Piece p: opponentPieces){
            boolean[][] mat = p.possibleMoves();

            if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }
}
