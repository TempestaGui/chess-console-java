package org.aplicacao.program.boardGame;

import org.aplicacao.program.boardGame.exceptions.BoardException;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns){
        if(rows < 1 || columns < 1){
            throw new BoardException("Rows and columns must be greater than 1");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Piece piece(int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException("Position does not exist");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position does not exist");
        }
        return pieces[position.getRow()][position.getColumn()]; //retornando a peca que esta na posicao da linha e da coluna
    }

    public void placePiece(Piece piece, Position position){ //vai na matriz de pecas e atribuir a peca que vir como argumento
        if(thereIsAPiece(position)){
            throw new BoardException("Piece already exist in this position "+position);
        }
        pieces[position.getRow()][position.getColumn()] =  piece;
        piece.position = position;
    }

    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    private boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns; //verificando se a posicao existe
    }

    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Position does not exist");
        }
        return piece(position) != null;
    }

}
