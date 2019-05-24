package com.progmatic.labyrinthproject.exceptions;

import com.progmatic.labyrinthproject.enums.CellType;

/**
 * An exception thrown when a move-related problem happens.
 * @author pappgergely
 */
public class InvalidMoveException extends Exception {
    /**
     * CellType of the problematic cell.
     */
    private final CellType cellType;
    
    /**
     * A message describing the specific problem with this cell.
     */
    private final String message;

    public InvalidMoveException(CellType cellType, String message ) {
        super(message);
        this.cellType = cellType;
        this.message = message;
    }
    
    

}
