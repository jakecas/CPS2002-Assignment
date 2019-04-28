package exceptions;

public class PositionOutOfBoundsException extends RuntimeException {
    public PositionOutOfBoundsException(String position){
        super("This position is out of bounds of the current map: " + position);
    }
}
