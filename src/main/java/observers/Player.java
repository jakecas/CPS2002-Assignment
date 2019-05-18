package observers;

import enums.Direction;
import exceptions.PositionOutOfBoundsException;
import objects.Position;
import objects.maps.Map;
import observables.Team;

public class Player implements Observer{
    private final Position initialPosition;
    private Position position;
    private Map map;
    private Team team;
    private String html;

    public Player(Position position, Map map, Team team){
        this.initialPosition = new Position(position.getX(), position.getY());
        this.position = position;
        this.map = map;
        this.team = team;

        team.register(this);
    }

    public Map getMap(){
        return map;
    }

    public Position move(Direction direction){
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
        return position;
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
       return html;
    }

    public void resetToInitialPosition() {
        position = initialPosition;
    }

    @Override
    public void update(){
        html = map.generateHTML(team, position);
    }
}
