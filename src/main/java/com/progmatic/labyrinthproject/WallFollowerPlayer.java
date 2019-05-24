package com.progmatic.labyrinthproject;


import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.enums.Hand;
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
public class WallFollowerPlayer implements Player {
    
//    Hand hand;
    
//    public WallFollowerPlayer( Hand hand ) {
//        this.hand = hand;
//    }
    
    public WallFollowerPlayer() {
//        int r = (int) (Math.random() * 2);
//        if (r == 0 ) {
//            this.hand = Hand.LEFT;
//        } else {
//            this.hand = Hand.RIGHT;
//        }
        
    }

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> possibleDir = l.possibleMoves();
        Direction nextMove = possibleDir.get(0);
        return nextMove;
    }
    
}
