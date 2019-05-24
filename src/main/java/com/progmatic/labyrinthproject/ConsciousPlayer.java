package com.progmatic.labyrinthproject;


import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author progmatic
 */
public class ConsciousPlayer implements Player {

    public ConsciousPlayer() {
    }

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> possibleDir = l.possibleMoves();
        
        Direction nextMove = possibleDir.get(0);
        return nextMove;
    }
    
}
