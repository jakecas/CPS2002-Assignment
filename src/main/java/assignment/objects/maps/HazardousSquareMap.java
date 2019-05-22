package assignment.objects.maps;

public class HazardousSquareMap extends SquareMap{
    private static final HazardousSquareMap SINGLETON = new HazardousSquareMap();

    private HazardousSquareMap(){
        super();
    }

    public static HazardousSquareMap getInstance(){
        return SINGLETON;
    }

    public char[][] generateSeed() {
        return this.generateSeed(0.3);
    }
}
