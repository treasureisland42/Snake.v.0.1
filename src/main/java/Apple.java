import java.awt.*;
import java.util.ArrayList;

public class Apple {

    private static Point points;
    private static Apple a;
    public Apple(Point p) {
        points = p;
    }

    public static void main(String[] args){
        a = spawnApple();
    }
    public static Apple spawnApple() {
        int randX = (int) (Math.random() * 11);
        int randY = (int) (Math.random() * 11);
        Point p = new Point(randX, randY);
        a = new Apple(p);
        if(Snake.getHead().getX() == a.getP().getX() && Snake.getHead().getY() == a.getP().getY()){
            spawnApple();
        }
        return a;
    }

    public static Point getP() {

        return new Point((int) (points.getX()), (int) (points.getY()));
    }

    public static double getX() {
        return a.getP().getX();
    }

    public static double getY() {
        return a.getP().getY();
    }

}