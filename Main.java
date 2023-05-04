/*    Author:      Giovanni Sanchez (gsanchez@muhlenberg.edu)
 *    Date:        04/14/23
 *    Description: Main mostly calls all methods and prints the content with the use of a while loop
 */
import java.io.IOException;
public class Main
{
    public static void main(String[] args) throws IOException {
        int remainingVeg; // a variable that stores the remaining Veggies
        GameEngine game = new GameEngine(); // I instantiated and stored a GameEngine object
        game.initializeGame(); // Initialized the game using the GameEngine method
        game.intro(); // Display the game’s introduction using the GameEngine method
        System.out.println(); // space between the intro and field
        remainingVeg = game.remainingVeggies(); // stores the number of remaining vegetables in the game initialized using the GameEngine method
        while(remainingVeg != 0){ // While there are still vegetables left in the game
            System.out.println(remainingVeg + " veggies remaining"); // Output the number of remaining vegetables
            System.out.println("Current score: " + game.getScore()); // Output the player’s score
            game.printField(); // Print out the field using the GameEngine method
            game.moveRabbit(); // Move the rabbits using the GameEngine method
            game.moveCaptain(); // Move the captain using the GameEngine method
            remainingVeg = game.remainingVeggies(); // the new number of remaining vegetables after all methods
        }
        game.gameOver(); // Display the Game Over information using the GameEngine method
        game.saveHighScore(); // writes the High Score using the GameEngine method





    }


}
