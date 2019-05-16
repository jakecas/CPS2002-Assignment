package objects.maps;

public class SafeSquareMap extends SquareMap {
    private static final SafeSquareMap SINGLETON = new SafeSquareMap();

    private SafeSquareMap(){
        super();
    }

    public static SafeSquareMap getInstance(){
        return SINGLETON;
    }

    public char[][] generateSeed() {
        return this.generateSeed(0.1);
    }

}
