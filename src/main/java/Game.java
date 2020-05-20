import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private final int width;
    private final int height;
    private static int[][] pos = new int[][]{{0, 1},
                                        {0, 2}, {1, 2}, {1, 3}};
    private static Snake snake = new Snake(pos, "UP");
    private static Game game = new Game(10, 10);

    //creates an instance of game
    public Game(int height, int width) {
        this.height = height;
        this.width = width;

    }

    public static void main(String[] args) {
        render(game);
        move();
    }

    //renders the board and plots the starting positions of the snake
    public static void render(Game game) {
        int[][] matrix = new int[game.height][game.width];
        //iterates through the board, one cell at a time
        for(int r = 0; r < game.height; r++){
            for(int t = 0; t < game.width; t++){
                matrix[t][r] = 0;
                //iterates through the list of positions of the snake and checks against the current board position.
                for(int g = 0; g < Snake.getPos(snake).length; g++){
                        if (r == Snake.getPos(snake)[g][0] && t == Snake.getPos(snake)[g][1]) {
                            matrix[t][r] = 1;
                        } if (r == Snake.getHead(snake)[0][0] && t == Snake.getHead(snake)[0][1]) {
                            matrix[t][r] = 2;
                        }
                    }
                }
            }
        //prints out the board based on the matrix values
        //prints top row of * for wall
        for(int k = 0; k < game.width + 2; k++)
            System.out.print("* ");

        System.out.println();
        //prints the matrix along with left and right wall
        for (int i = 0; i < game.height; i++) {
            System.out.print("* ");
            for (int j = 0; j < game.width; j++) {

                if (matrix[i][j] == 0) {
                    System.out.print("  ");
                } else if(matrix[i][j] == 1){
                    System.out.print("0 ");
                } else if(matrix[i][j] == 2){
                    System.out.print("X ");
                }

                if(j == game.width -1 ){
                    System.out.print("*");
                }
            }
            if(i == game.height - 1){
                System.out.println();
                for(int k = 0; k < game.width + 2; k++)
                    System.out.print("* ");
            }
            System.out.println();
        }
    }
    //moves the snake around the board. takes input from the user and moves the snake in the corresponding direction
    public static void move(){

        //gets the user input and sets the direction of the snake
        System.out.println("What direction do you want to move? (U/D/L/R)");
        Scanner input = new Scanner(System.in);
        String dir = input.next();
        int[][]move = new int[1][2];
        switch(dir){
            case "U" :
                move = snake.setDirection("U");
                break;
            case "D" :
                move = snake.setDirection("D");
                break;
            case "L" :
                move = snake.setDirection("L");
                break;
            case "R" :
                move = snake.setDirection("R");
                break;
            default :
                System.out.print("Invalid direction");
        }
        //gets the head of the snake and makes the movement. Adds this movement to top of positions list and removes the
        //last one.
        int[][] headPos = Snake.getHead(snake);
        int[][] newHeadPos = new int[1][2];
        newHeadPos[0][0] = headPos[0][0] + move[0][0];
        newHeadPos[0][1] = headPos[0][1] + move[0][1];
        Snake.takeStep(newHeadPos, snake);
        //above line returns an array of the new positions, but they arent assigned to the snake object in this class, so when
        //render is run again, the board just reprints
        render(game);
        move();


        }

    }

