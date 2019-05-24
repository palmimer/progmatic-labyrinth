package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {
    
    private final Map<Coordinate, CellType> labyrinth;
    private int width;
    private int height;
    private Coordinate playerPosition;
    
    public LabyrinthImpl() {
        this.labyrinth = new HashMap<>();
        this.width = 0;
        this.height = 0;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            width = Integer.parseInt(sc.nextLine());
            height = Integer.parseInt(sc.nextLine());
            CellType type;
            Coordinate c;

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                        case 'W':
                            type = CellType.WALL;
                            break;
                        case 'E':
                            type = CellType.END;
                            break;
                        case 'S':
                            type = CellType.START;
                            break;
                        default:
                            type = CellType.EMPTY;
                            break;
                    }
                    c = new Coordinate(ww, hh);
                    labyrinth.put(c, type);
                    if (type.equals(CellType.START)) {
                        playerPosition = c;
                    }
                }
                
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public int getWidth() {
        if (width != 0) {
            return width;
        } else {
            return -1;
        }
    }

    @Override
    public int getHeight() {
        if (height != 0) {
            return height;
        } else {
            return -1;
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        boolean isThereSuchCoordinate = false;
    
        for (Map.Entry<Coordinate, CellType> entry : labyrinth.entrySet()) {
            if ( entry.getKey().equals(c) ) {
                isThereSuchCoordinate = true;
                return entry.getValue();
            } 
        }
        if (isThereSuchCoordinate = false) {
            throw new CellException(c, "Ez a hely nem létezik a labirintusban.");
        }
        return null;
    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        for (CellType value : labyrinth.values()) {
            value = CellType.EMPTY;
        }
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        boolean isThereSuchCoordinate = false;
        for (Coordinate coordinate : labyrinth.keySet()) {
            if (coordinate.equals(c)) {
                labyrinth.put(c, type);
            }
        }
        if (isThereSuchCoordinate = false) {
            throw new CellException(c, "Ez a hely nem létezik a labirintusban.");
        }
        
        // a player helyzetét a startra állítja
        if (type.equals(CellType.START)) {
            playerPosition = c;
        }
    }

    @Override
    public Coordinate getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public boolean hasPlayerFinished() {
        for (Map.Entry<Coordinate, CellType> entry : labyrinth.entrySet()) {
            if (playerPosition.equals(entry.getKey())) {
                if (entry.getValue().equals(CellType.END)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> possibleMoves = new ArrayList<>();
        Coordinate east = new Coordinate(playerPosition.getCol() + 1, playerPosition.getRow());
        Coordinate south = new Coordinate(playerPosition.getCol(), playerPosition.getRow() + 1);
        Coordinate west = new Coordinate(playerPosition.getCol() - 1, playerPosition.getRow());
        Coordinate north = new Coordinate(playerPosition.getCol(), playerPosition.getRow() - 1);
        for (Map.Entry<Coordinate, CellType> entry : labyrinth.entrySet()) {
            Coordinate c = entry.getKey();
            if (c.equals(east)) {
                if (isPossibleMove(entry.getValue())) {
                    possibleMoves.add(Direction.EAST);
                }
            }
            if (c.equals(south)) {
                if (isPossibleMove(entry.getValue())) {
                    possibleMoves.add(Direction.SOUTH);
                }
            }
            if (c.equals(west)) {
                if (isPossibleMove(entry.getValue())) {
                    possibleMoves.add(Direction.WEST);
                }
            }
            if (c.equals(north)) {
                if (isPossibleMove(entry.getValue())) {
                    possibleMoves.add(Direction.NORTH);
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        int newCol = playerPosition.getCol();
        int newRow = playerPosition.getRow();
        boolean hasPlayerMoved = false;
        switch (direction){
            case EAST: newCol++; break;
            case SOUTH: newRow++; break;
            case WEST: newCol--; break;
            case NORTH: newRow--; break;
        }
        Coordinate newC = new Coordinate(newCol, newRow);
        for (Map.Entry<Coordinate, CellType> entry : labyrinth.entrySet()) {
            Coordinate  c = entry.getKey();
            if ( newC.equals(c) ){
                if (!entry.getValue().equals(CellType.WALL)) {
                    playerPosition = newC;
                    hasPlayerMoved = true;
                } else {
                    throw new InvalidMoveException( entry.getValue(), "Ide nem léphet a játékos!" );
                }
            }
        }
        if ( !hasPlayerMoved ) {
            throw new InvalidMoveException( null, "Ide nem léphet a játékos!" );
        }
    }

    private boolean isPossibleMove( CellType c ){
        if (c.equals(CellType.EMPTY) || c.equals(CellType.END)) {
            return true;
        } else {
            return false;
        }
    }

}
