import enums.Direction;

public class Player {
    private final Position initialPosition;
    private Position position;
    private Map map;

    public Player(Position position, Map map){
        this.initialPosition = position;
        this.position = position;
        this.map = map;
    }

    public void move(Direction direction){
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
