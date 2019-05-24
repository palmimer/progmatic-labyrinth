/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.util.List;
import java.util.Random;

/**
 *
 * @author progmatic
 */
public class RandomPlayer implements Player {

    public RandomPlayer() {
    }
    
    

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> possibleDir = l.possibleMoves();
        Random r = new Random();
        int i = r.nextInt(possibleDir.size());
        return possibleDir.get(i);
    }
    
}
