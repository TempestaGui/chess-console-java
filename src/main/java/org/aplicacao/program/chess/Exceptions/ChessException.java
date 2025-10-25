package org.aplicacao.program.chess.Exceptions;

import org.aplicacao.program.boardGame.exceptions.BoardException;

public class ChessException extends BoardException {
    private static final long serialVersionUID = 1L;

    public ChessException(String message) {
        super(message);
    }
}
