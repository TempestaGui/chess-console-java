package org.aplicacao.program.boardGame;

public class Position {
    private int row;
    private int column;

    public Position(){}

    public Position(int row, int Column){
        this.row = row;
        this.column = Column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString(){
        return "Psosition [row:  " + row + ", column: " + column + "]";
    }


    public void setValues(int row, int column){

    }
}
