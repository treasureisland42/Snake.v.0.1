import java.util.ArrayList;
import java.awt.Point;

public class Snake {

    private static String direction;
    private static final int[][] dir = {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0},
    };
    private static int[][] pos = {
            {0, 1},
            {0, 2},
            {1, 2},
            {1, 3}
    };
    static ArrayList<Point> points;


    public Snake(ArrayList<Point> p,  String direction) {
        points = p;
        Snake.direction = direction;
    }

    public static void main(String[] args){

        for (int[] po : pos) {
            for (int j = 0; j < 1; j++) {
                points.add(new Point(po[j], po[j + 1]));
            }
        }
    }

    //method to take a step, updates and returns an arraylist of coords, including the new position
    public static Snake takeStep(Point p, Snake s){

        points.remove(points.size() - 1);
        points.add(0, p);
        return s;
    }

    public static Snake growSnake(Point p, Snake s){
        points.add(0, p);
        return s;
    }

    public static int[][] setDirection(String direction){
        int index = 0;
        Point pointDir = new Point(1,2);
        //change this for enum at some point.
        if(direction.equalsIgnoreCase("D")) index = 0;
        if(direction.equalsIgnoreCase("U")) index = 1;
        if(direction.equalsIgnoreCase("L")) index = 2;
        if(direction.equalsIgnoreCase("R")) index = 3;

        //populates the newDir array with the coordinates
        int[][] newDir = new int[1][2];
        for (int[] ints : newDir) {
            System.arraycopy(dir[index], 0, ints, 0, newDir.length + 1);
        }
        return newDir;
    }

    //returns an array of coordinates of the snake head
    public static Point getHead(){

        return new Point(points.get(0));
    }

    //returns an array of coordinates of the snake body
    public static Point[] getPos(Snake snake) {
        Point[] positions = new Point[points.size()];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = points.get(i);
        }
        return positions;
    }

    //returns a Snake
    public static Snake getSnake(){
        return new Snake(points, "UP");
    }


}

