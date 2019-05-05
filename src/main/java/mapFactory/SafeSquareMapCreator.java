package mapFactory;

public class SafeSquareMapCreator extends SquareMapCreator {

    public Map createSafeSquareMap(){
        return new SafeSquareMap();
    }
}
