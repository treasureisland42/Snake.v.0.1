import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {

    private static String direction;
    //private static int[][] pos;
    public static List<int[][]> posit;
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


    public Snake(int[][] pos, String direction) {
        this.pos = pos;
        this.direction = direction;
    }

    public static void main(String[] args){

        int[][]newPos = {{0,4}};
        Snake snake = new Snake(pos, "UP");

        takeStep(newPos, snake);
        setDirection("UP");
        getHead(snake);
        getPos(snake);
        getSnake();
    }
    //method to take a step, updates and returns an arraylist of coords, including the new position
    public static List<int[][]> takeStep(int[][] p, Snake s){

        posit = new ArrayList(Arrays.asList(pos));
        posit.remove(0);
        posit.add(posit.size(), p);
        //System.out.println(Arrays.deepToString(posit.toArray()));
        //loop to iterate through the position arraylist, and copy the values to the 2d array to update the value of snake
        //does not work, conversion is a bitch, check bookmarks
        for(int i = 0; i < s.pos.length; i++){
            for(int j = 0; j < 2; j++){
                //s.pos[i][j] = posit.get(i)[j];
                System.out.println(posit.get(i));
                int[] ne = posit.get(i).get(i);
                s.pos[i][j] = ne[j];
            }
        }


        return posit;

    }

    public static int[][] setDirection(String direction){
        int index = 0;
        //change this for enum at some point.
        if(direction.equalsIgnoreCase("D")) index = 0;
        if(direction.equalsIgnoreCase("U")) index = 1;
        if(direction.equalsIgnoreCase("R")) index = 2;
        if(direction.equalsIgnoreCase("L")) index = 3;

        //populates the newDir array with the coordinates
        int[][] newDir = new int[1][2];
        for (int[] ints : newDir) {
            System.arraycopy(dir[index], 0, ints, 0, newDir.length + 1);
        }
        return newDir;
    }

    //returns an array of coordinates of the snake head
    public static int[][] getHead(Snake snake){
        //creates a 2d array @headPos and adds the most recent position, or pos of the head of the snake
        int[][] headPos = new int[1][2];
        //simplified for loop with arraycopy instead of nested for to copy the array valeu
        for (int[] headPo : headPos) {
            System.arraycopy(pos[pos.length - 1], 0, headPo, 0, headPos.length + 1);
        }
        return headPos;
    }

    //returns an array of coordinates of the snake body
    public static int[][] getPos(Snake snake) {
        int[][] positions = new int[snake.pos.length][2];
        for (int i = 0; i < positions.length; i++) {
            System.arraycopy(snake.pos[i], 0, positions[i], 0, 2);
        }
        return positions;
    }

    //returns a Snake
    public static Snake getSnake(){
        Snake snake = new Snake(pos, "UP");
        return snake;
    }


}

