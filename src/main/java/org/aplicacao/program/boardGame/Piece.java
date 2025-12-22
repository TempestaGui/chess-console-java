package org.aplicacao.program.boardGame;

public abstract class Piece {

    protected Position position;

    private Board board;

    public Piece(Board board){
        this.board = board;
        position = null; //posicao de uma peca recem criada
    }

    protected Board getBoard() {
        return board;
    }

    //rookMethods metodo que faz um gancho com a subclasse
    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position){
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove(){
        boolean[][] mat = possibleMoves();
        for(int i =0; i<mat.length; i++){
            for(int j = 0; j<mat.length; j++){
                if(mat[i][j]){
                    return true;
                }
            }
        }
        return false;
    }


}
