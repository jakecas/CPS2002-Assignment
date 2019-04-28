package exceptions;

public class MapSizeUndefinedException extends RuntimeException {
    public MapSizeUndefinedException(String operation){
        super("The size of the map needs to be set before this operation: " + operation);
    }
}
