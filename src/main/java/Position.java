import java.util.Random;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position randomPosition(int max){
        Random rand = new Random();
        return new Position(rand.nextInt(max), rand.nextInt(max));
    }

    public static double euclideanDistance(Position position1, Position position2){
        double square1 = Math.pow(position1.getX() - position2.getX(), 2);
        double square2 = Math.pow(position1.getY() - position2.getY(), 2);
        return Math.sqrt(square1+square2);
    }

    public boolean isWithinLimit(int min, int max){
        if(min < x && x < max){
            return min < y && y < max;
        }
        return false;
    }
}
