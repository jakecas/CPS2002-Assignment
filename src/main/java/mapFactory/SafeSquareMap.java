package mapFactory;

import exceptions.MapSizeUndefinedException;
import propertyObjects.Position;

public class SafeSquareMap extends SquareMap {

    public char[][] generateSeed() {
        return this.generateSeed(15);
    }

}
