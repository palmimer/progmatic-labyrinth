/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.interfaces.Player;

/**
 *
 * @author progmatic
 */
public class PlayGame {
    
    
    public static void main(String[] args) throws Exception {
        LabyrinthImpl l = new LabyrinthImpl();
        Player p1 = new RandomPlayer();
        l.loadLabyrinthFile("labyrinth1.txt");
        
        
    }
    
    
}
