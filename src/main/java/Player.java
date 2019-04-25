import enums.Direction;
import exceptions.PositionOutOfBoundsException;

public class Player {
    private final Position initialPosition;
    private Position position;
    private Map map;

    public Player(Position position, Map map){
        this.initialPosition = position;
        this.position = position;
        this.map = map;
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
        if(!position.isWithinLimit(0, map.getMapSize())){
            position = temp;
            throw new PositionOutOfBoundsException();
        }
        map.getTile(position).revealTile();
    }

    public void setPosition(Position position) {
        if(!position.isWithinLimit(0, map.getMapSize())){
            throw new PositionOutOfBoundsException();
        }
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public String printMap(){
       return map.generateHTML(position);
    }
}
