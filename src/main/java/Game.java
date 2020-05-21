import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private final int width;
    private final int height;
    private static int[][] pos = new int[][]{{0, 1},
                                        {0, 2}, {1, 2}, {1, 3}};
    private static ArrayList<Point> points = new ArrayList<>();
    private static Snake snake;
    private static Game game;

    //creates an instance of game
    public Game(int height, int width) {
        this.height = height;
        this.width = width;

    }

    public static void main(String[] args) {

        for (int[] po : pos) {
            for (int j = 0; j < 1; j++) {
                points.add(new Point(po[j], po[j + 1]));
            }
        }
        snake = new Snake(points, pos, "UP");
        game = new Game(10, 10);
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
                    int x = Snake.getPos(snake)[g].x;
                    int y = Snake.getPos(snake)[g].y;
                        if (r == x && t == y) {
                            matrix[t][r] = 1;
                        } if (r == Snake.getHead(snake).x && t == Snake.getHead(snake).y) {
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
                //should replace with switch
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
                move = Snake.setDirection("U");
                break;
            case "D" :
                move = Snake.setDirection("D");
                break;
            case "L" :
                move = Snake.setDirection("L");
                break;
            case "R" :
                move = Snake.setDirection("R");
                break;
            default :
                System.out.print("Invalid direction");
        }

        Point headPos = Snake.getHead(snake);
        Point newHeadPos = new Point(headPos.x + move[0][0], headPos.y + move[0][1]);
        Snake.takeStep(newHeadPos, snake);
        render(game);
        //move not functioning correctly. have to take into account edges, and wrapping on self
        move();


        }

    }

