package objects.maps;

public class HazardousSquareMap extends SquareMap{

    public char[][] generateSeed() {
        return this.generateSeed(0.3);
    }
}
