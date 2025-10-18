package org.aplicacao.program.boardGame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns){
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
        return pieces[row][column];
    }

    public Piece piece(Position position){
        return pieces[position.getRow()][position.getColumn()]; //retornando a peca que esta na posicao da linha e da coluna
    }

    public void placePiece(Piece piece, Position position){ //vai na matriz de pecas e atribuir a peca que vir como argumento
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
        return piece(position) != null;
    }

}
