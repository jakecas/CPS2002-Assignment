package objects;

import enums.Direction;
import exceptions.PositionOutOfBoundsException;
import objects.maps.Map;

import java.util.Collection;
import java.util.LinkedList;

public class Player {
    private final Position initialPosition;
    private Position position;
    private Map map;
    private Collection<Tile> revealedTiles;

    public Player(Position position, Map map){
        this.initialPosition = new Position(position.getX(), position.getY());
        this.position = position;
        this.map = map;
        this.revealedTiles = new LinkedList<>();

        revealTile(initialPosition);
    }

    public Map getMap(){
        return map;
    }

    public void move(Direction direction){
        Position temp = new Position(position.getX(), position.getY());
        switch (direction){
            case NORTH:
                position.add(0,-1);
                break;
            case SOUTH:
                position.add(0,1);
                break;
            case EAST:
                position.add(1,0);
                break;
            case WEST:
                position.add(-1,0);
                break;
        }
        if(!position.isWithinLimit(0, map.getMapSize()-1)){
            // Undoing the movement and throwing an exception.
            position = temp;
            throw new PositionOutOfBoundsException(position.toString());
        }
        revealTile(position);
    }

    // TO REMOVE
    public void revealTile(Position position){
        Tile tile = map.getTile(position);
        if(!revealedTiles.contains(tile)) {
            this.revealedTiles.add(tile);
        }
    }

    public boolean isRevealed(Position position){
        return isRevealed(map.getTile(position));
    }

    public boolean isRevealed(Tile tile){
        return revealedTiles.contains(tile);
    }

    public void setPosition(Position position) {
        if(!position.isWithinLimit(0, map.getMapSize())){
            throw new PositionOutOfBoundsException(position.toString());
        }
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public String printMap(){
       return map.generateHTML(this);
    }

    public void resetToInitialPosition() {
        position = initialPosition;
    }
}
