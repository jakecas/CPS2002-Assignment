package objects.maps;

public class SafeSquareMap extends SquareMap {

    public char[][] generateSeed() {
        return this.generateSeed(0.1);
    }

}
