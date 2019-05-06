package mapFactory;

import exceptions.MapSizeUndefinedException;
import propertyObjects.Position;

public class HazardousSquareMap extends SquareMap{

    public char[][] generateSeed() {
        return this.generateSeed(30);
    }
}
