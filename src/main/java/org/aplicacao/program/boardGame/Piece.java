package org.aplicacao.program.boardGame;

public class Piece {

    protected Position position;

    private Board board;

    public Piece(Board board){
        this.board = board;
        position = null; //posicao de uma peca recem criada
    }

    protected Board getBoard() {
        return board;
    }


}
