/*    Author:      Giovanni Sanchez (gsanchez@muhlenberg.edu)
 *    Date:        04/14/23
 *    Description: GameEngine is most of the functionality and has methods that are necessary for the game to be playable
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;


public class GameEngine {
    private FieldInhabitant[][] field; // A class scoped 2D array of type FieldInhabitant named field
    private Captain cptVeggie; // A class scoped variable of type Captain named cptVeggie
    private ArrayList<Rabbit> rabbits; // A class scoped ArrayList of type Rabbit named rabbits

    private ArrayList<Veggie> possibleVeggies; // A class scoped ArrayList of type Veggie named possibleVeggies

    private int playerScore; // A class scoped integer to store the player’s score

    private final int NUMBEROFVEGGIES = 30; // A class scoped constant integer to store the initial number of vegetables
    private final int NUMBEROFRABBITS = 5; // A class scoped constant integer to store the number of rabbits
    Random rand  = new Random();
    private void initVeggies() throws FileNotFoundException { // A private method
        System.out.println("Please enter the name of the vegetable point file to analyze: "); // prompt
        Scanner input = new Scanner(System.in); // Scanner
        String filename = input.nextLine(); // String variable that stores the next line
        File infile = new File(filename); // Creates a new file object infile

        while(!infile.exists()){ // checks if the file exists, and prompts user to put the correct name
            System.out.println(infile.getName() + " does not exist! Please enter the name of the vegetable point file to analyze: ");
            filename = input.nextLine();
            infile = new File(filename); // Creates a new file object infile
        }
        Scanner file = new Scanner(infile); // reads the data
        possibleVeggies = new ArrayList<Veggie>(); // possibleVeggies is initialized


        int rows; // two int variables that store that the size of the field from the files
        int cols;
        String veggieRC = file.nextLine(); //String variable that stores the info of the first line
        String[] veggieList = veggieRC.split(","); // String array that stores the data that has been split by spaces
        rows = Integer.parseInt(veggieList[1]); // store the number in index 1
        cols = Integer.parseInt(veggieList[2]); // store the number in index 2


        field = new FieldInhabitant[rows][cols]; // initialized field to a 2D array of the size specified in the file
        while (file.hasNextLine()) { // a while to run for the lines that are left in the file
            String vegName; // A String variable that stores vegetable names
            String vegSymbol; // A String variable that stores the symbol
            int vegPoints; // A int variable that stores the points
            String veggieInfo = file.nextLine(); // String veggieInfo with the value of each line
            String[] vegList = veggieInfo.split(","); // String array that splits the data from the spaces
            vegName = vegList[0]; // vegName gets the value in index 0 of vegList
            vegSymbol = vegList[1]; // vegName gets the value in index 1 of vegList
            vegPoints = Integer.parseInt(vegList[2]); // vegPoints stores the parsed value at index 2



            Veggie veg = new Veggie(vegName, vegSymbol, vegPoints);  // create new Veggie objects
            possibleVeggies.add(veg); // veg is added to possibleVeggies

        }

        for(int i = 0; i < NUMBEROFVEGGIES; i++) // the for loop iterates through NUMBEROFVEGGIES
        {
            int randRows = rand.nextInt(rows); // generates a random integer between 0 and rows
            int randCols = rand.nextInt(cols); // generates a random integer between 0 and cols
            int vegIdx = rand.nextInt(possibleVeggies.size()); // generates a random integer between 0 and possibleVeggies sizes
            Veggie toAdd = possibleVeggies.get(vegIdx); //randomly picks a object from the ArrayList
            while(field [randRows][randCols] != null) // while a random point is not null
            {
                randRows = rand.nextInt(rows); // generates random new random location
                randCols = rand.nextInt(cols);

            }
            if (field [randRows][randCols] == null){ // if the random point is null
                field [randRows][randCols] = toAdd; // add the randomly selected object
            }
            file.close(); // closed the file

        }

    }
    private void initCaptain() // A private method
    {
        int rows = field.length; // the number of rows in field
        int cols =  field[0].length; // the number of cols in the first row in field
        // Random rand  = new Random(); // random function
        int randRows = rand.nextInt(rows); // generates a random integer between 0 and rows
        int randCols = rand.nextInt(cols); // generates a random integer between 0 and cols
        while(field [randRows][randCols] != null) // while a random point is not null
        {
            randRows = rand.nextInt(rows); // generates random new random location
            randCols = rand.nextInt(cols);

        }
        if (field [randRows][randCols] == null){ // if the random point is null
            cptVeggie = new Captain(randRows,randCols); //  new Captain object is created using the random location
            field [randRows][randCols] = cptVeggie; // the object is assigned a random location in the field
        }


    }
    private void initRabbits() { // A private method

        rabbits = new ArrayList<Rabbit>(); // rabbits is initialized to an empty ArrayList
        for(int i = 0; i < NUMBEROFRABBITS; i++) // for NUMBEROFRABBITS, a random location is chosen for a Rabbit object
        {
            int rows = field.length; // the number of rows in field
            int cols =  field[0].length; // the number of cols in the first row in field
            int randRows = rand.nextInt(rows); // generates a random integer between 0 and rows
            int randCols = rand.nextInt(cols); // generates a random integer between 0 and cols
            while(field [randRows][randCols] != null) // while a random point is not null
            {
                randRows = rand.nextInt(rows); // generates random new random location
                randCols = rand.nextInt(cols);

            }
            if (field [randRows][randCols] == null){ // if the random point is null
                Rabbit randRabbits = new Rabbit(randRows,randCols); //  new Rabbit object is created using the random location
                rabbits.add(randRabbits); // the object is added to rabbits
                field [randRows][randCols] = randRabbits; //  object assigned to the random location in field
            }

        }


    }
    public void initializeGame() throws FileNotFoundException { // private method
        initVeggies(); //calls this methods
        initCaptain();
        initRabbits();
        playerScore = 0; // sets playerScore to 0

    }
    public int remainingVeggies() // a public method
    {

        int countVeg = 0; // counter
        for (int i = 0; i < field.length ; i++) { //  iterating rows
            for (int k = 0; k < field[i].length; k++) { // iterating columns
                if (field[i][k] instanceof Veggie) { // if i iterate through the field and find a instance of Veggie
                    countVeg++; // add one to the variable countVeg
                }
            }
        }
        return countVeg; // returns an integer representing the number of vegetables still in the game
    }
    public void intro()
    {
        System.out.println("Welcome to Captain Veggie!"); // prompt introduction
        System.out.println("The rabbits have invaded your garden and you must harvest\n" +
                "as many vegetables as possible before the rabbits eat them\n" +
                "all! Each vegetable is worth a different number of points\n" +
                "so go for the high score!");
        System.out.println("The vegetables are: ");
        for (int i = 0; i < possibleVeggies.size();i++){
            Veggie vg1 = possibleVeggies.get(i); // gets the object at i from the possibleVeggies using get() and assigns it to vg1
            String vegeName = vg1.getVeggieName(); // stores the name from vg1
            String veSymbol = vg1.getSymbol(); // stores the symbol from vg1
            int pointVeg = vg1.getVeggiePoints(); // stores the points from vg1
            System.out.println(veSymbol + " : " + vegeName + ", " + pointVeg + " points"); // prints the output

        }
        System.out.println("Captain Veggie is V, and the rabbits are R's."); // the symbols meaning


    }
    public void printField()
    {
        for (int i = 0; i < field[0].length+1;i++){ //iterating through first row
            System.out.print("__"); // printed two __ due to spacing each symbol

        }
        System.out.println(""); // moves to next line
        for (int i = 0; i < field.length;i++){ // iterating through rows
            System.out.print("|"); // prints '|' in the beginning of each row
            for (int k = 0; k < field[i].length;k++){ // iterating through cols
                if (field[i][k] == null) { // if the position in field is null
                    System.out.print("  "); // space in place of null
                }
                else System.out.print(field[i][k].getSymbol() + " "); // if not null, print the symbol of the objects there with space
            }
            System.out .println("|"); // at the end print '|'

        }

        for (int i = 0; i < field[0].length+1;i++){ // iterating
            System.out.print("__"); // at the end print


        }
        System.out .println(); // to put space between the field and the movement
    }
    public int getScore(){
        return playerScore;
    } // returns the value of score
    public void moveRabbit() throws FileNotFoundException {
        for(int i = 0; i < rabbits.size();i++) { // iterates through the size of rabbits ArrayList
            int mX = rand.nextInt(-1, 2); // to move the x axis
            int mY = rand.nextInt(-1, 2); // to  move the y axis
            if (mX == 0 && mY == 0) { // if the x & y dont move
                return; // stay in place
            } else {
                int x = rabbits.get(i).getX() + mX; // the x axis is added to the x axis of rabbits
                int y = rabbits.get(i).getY() + mY; // the y axis is added to the y axis of rabbits

                if (x > field.length - 1 || x < 0 || y > field.length - 1 || y < 0) {
                    continue; // skips the current iteration of the loop and move to the next iteration
                }
                if (field[x][y] == null) { // if the position is null
                    field[rabbits.get(i).getX()][rabbits.get(i).getY()] = null; // return the previous position to null
                    field[x][y] = rabbits.get(i); // the rabbit will be in the current position
                    rabbits.get(i).setX(x); // sets the x axis to the rabbit class
                    rabbits.get(i).setY(y); // sets the y axis to the rabbit class
                }
                else if (field[x][y] instanceof Rabbit || field[x][y] instanceof Captain) { // if that position has a object of Rabbit or Captain
                    continue; // skips the current iteration of the loop and move to the next iteration
                } else if (field[x][y] instanceof Veggie) { // if the position has a object of Veggie
                    field[rabbits.get(i).getX()][rabbits.get(i).getY()] = null; // return the previous position to null
                    Veggie veg = (Veggie) field[x][y]; // we get a new object of Veggies that stores the position
                    rabbits.remove(veg); // removes the value of veg from the list rabbits.
                    field[x][y] = rabbits.get(i); // the rabbit will be in the current position
                    rabbits.get(i).setX(x); // sets the x axis to the rabbit class
                    rabbits.get(i).setY(y); // sets the y axis to the rabbit class


                }
            }

        }

    }
    private void moveCptVertical(int vmove){ //A private method that has a int passed to it
        if (field[cptVeggie.getX()+ vmove][cptVeggie.getY()] == null ){ // if vmove is added to the x axis and moves the cptVeggie to a position thats null
            field[cptVeggie.getX()][cptVeggie.getY()] = null; // return the previous position to null
            int newX = cptVeggie.getX() + vmove; // have a variable that stores the new x axis
            cptVeggie.setX(newX); // set the x axis in the class to the new x axis value
            field[newX][cptVeggie.getY()] = cptVeggie; // the current position is populated with the cptVeggie object
        }
        else if (field[cptVeggie.getX()+ vmove][cptVeggie.getY()] instanceof Veggie){ // if vmove is added to the x axis and moves the cptVeggie to a position that has the instance of Veggie
            field[cptVeggie.getX()][cptVeggie.getY()] = null; //  return the previous position to null
            int newX = cptVeggie.getX() + vmove; // have a variable that stores the new x axis
            cptVeggie.setX(newX); // set the x axis in the class to the new x axis value
            Veggie veggie; // create a new Veggie object
            veggie = (Veggie)field[newX][cptVeggie.getY()]; // veggie at the current position is stored in the veggie object
            System.out.println("Yummy! A delicious "+ veggie.getVeggieName());  // prints the VeggieName in veggie
            cptVeggie.addVeggie(veggie); // adds the veggie to the ArrayList
            playerScore += veggie.getVeggiePoints(); // adds up all the points
            field[newX][cptVeggie.getY()] = cptVeggie; // the current position is populated with the cptVeggie object

        }
        else if(field[cptVeggie.getX()+ vmove][cptVeggie.getY()] instanceof Rabbit) // if vmove is added to the x axis and moves the cptVeggie to a position that has the instance of Rabbit
        {
            System.out.println("DO NOT STEP ON RABBIT"); // print statement
            field[cptVeggie.getX()][cptVeggie.getY()] = cptVeggie; // cptVeggie will stay in the previous position

        }
    }
    private void moveCptHorizontal(int vmove){ //A private method that has a int passed to it
        if (field[cptVeggie.getX()][cptVeggie.getY() + vmove] == null ){ // if vmove is added to the y axis and moves the cptVeggie to a position thats null
            field[cptVeggie.getX()][cptVeggie.getY()] = null; //  return the previous position to null
            int newY = cptVeggie.getY() + vmove; // have a variable that stores the new y axis
            cptVeggie.setY(newY); // set the y axis in the class to the new y axis value
            field[cptVeggie.getX()][newY] = cptVeggie; // the current position is populated with the cptVeggie object
        }
        else if (field[cptVeggie.getX()][cptVeggie.getY()+ vmove] instanceof Veggie){ // if vmove is added to the x axis and moves the cptVeggie to a position that has the instance of Veggie
            field[cptVeggie.getX()][cptVeggie.getY()] = null; //  return the previous position to null
            int newY = cptVeggie.getY() + vmove; // have a variable that stores the new y axis
            cptVeggie.setY(newY); // set the y axis in the class to the new y axis value
            Veggie veggie; // create a new Veggie object
            veggie = (Veggie)field[cptVeggie.getX()][newY]; // veggie at the current position is stored in the veggie object
            System.out.println("Yummy! A delicious "+ veggie.getVeggieName()); // prints the VeggieName in veggie
            cptVeggie.addVeggie(veggie); // adds the veggie to the ArrayList
            playerScore += veggie.getVeggiePoints(); // adds up all the points
            field[cptVeggie.getX()][newY] = cptVeggie; // the current position is populated with the cptVeggie object

        }
        else if(field[cptVeggie.getX()][cptVeggie.getY()+ vmove] instanceof Rabbit) // if vmove is added to the x axis and moves the cptVeggie to a position that has the instance of Rabbit
        {
            System.out.println("DO NOT STEP ON RABBIT"); // print statement
            field[cptVeggie.getX()][cptVeggie.getY()] = cptVeggie; // cptVeggie will stay in the previous position

        }

    }

    public void moveCaptain()
    {
        Scanner scan = new Scanner(System.in);
        char choice; // a char variable
        System.out.println("Would you like to move up(W), down(S), left(A), or right(D): "); // movement print
        choice = scan.nextLine().charAt(0); // scans in user input

        switch(choice) { // switch checks the choice
            case ('W'): // if input was 'W'
            case ('w'):
                if(cptVeggie.getX_Coordinates()-1 >= 0) // if the x axis is moved up and is greater than or equal to 0
                {

                    moveCptVertical(-1); // move up one
                }
                else
                {
                    System.out.println("Cannot move there"); // print statement for out of bounds
                }
                break;
            case ('S'): // if input was 'S'
            case ('s'):
                if(cptVeggie.getX_Coordinates()+1 < field.length) // if the x axis is moved down and is less than the length of the rows
                {

                    moveCptVertical(1); // move down one
                }
                else
                {
                    System.out.println("Cannot move there");  // print statement for out of bounds
                }
                break;
            case ('A'): // if input was 'A'
            case ('a'):
                if(cptVeggie.getY_Coordinates()-1 >= 0) // if the y axis moves to the left and is greater than or equal to 0
                {

                    moveCptHorizontal(-1); // move up one

                }
                else
                {
                    System.out.println("Cannot move there");  // print statement for out of bounds
                }
                break;
            case ('D'): // if input was 'D'
            case ('d'):
                if(cptVeggie.getY_Coordinates()+1 < field[0].length) // if the y axis moves to the righ and is less than rows
                {

                    moveCptHorizontal(1); // move up one
                }
                else
                {
                    System.out.println("Cannot move there");  // print statement for out of bounds
                }
                break;
            default:
                System.out.println("Wrong character!!"); // Wrong input

        }

    }
    public void gameOver()
    {
        System.out.println("GAME OVER!!! "); // prints the end of game
        System.out.println("You managed to harvest the following vegetables: "  );

        for(int i = 0;i < cptVeggie.getVeggieList().size();i++){ // iterates through the VeggieList size
            //index practice
            System.out.println(cptVeggie.getVeggieList().get(i).getVeggieName()); // print the names of all indexes in that list

        }

        System.out.println("Total score was: "+ playerScore); // output the players total score

    }

    public void saveHighScore() throws IOException {
        Scanner scan = new Scanner(System.in);
        File infile = new File("HighScore.txt"); // file "HighScore,txt"
        String nickname; // stores the name of the user
        System.out.println("Enter initials: "); // print
        nickname = scan.nextLine(); // read in input
        if(!infile.exists()){// checks if the file exists, and prompts user to put the correct name
            PrintWriter writer = new PrintWriter("HighScore.txt"); // PrintWriter object named writer will write to the file.
            writer.print( "1" + " " +  nickname + " " + playerScore  + "\n"); // write this to file
            writer.close(); // closes the PrintWriter
        }
        else {
            int score; // stores the scores
            File highScorefile = new File("HighScore.txt");
            Scanner reader = new Scanner(highScorefile);
            ArrayList <String> highList; // arrayList to store the high scores
            highList = new ArrayList<>(); // initializes
            String [] file; // A string array
            while(reader.hasNextLine()){
                String high = reader.nextLine(); // reads in the first line in the file
                file = high.split(" "); // split by spaces
                highList.add(file[1] + " " + file[2]); // have the file[1] and file[2] in the same index
            }
            for (int i = 0; i < highList.size();i++){ // iterating through the Arraylist
                String str = highList.get(i).split(" ")[1]; // Split by space and get the value in [1]

                score = Integer.parseInt(str); // parse it to a int
                if (playerScore > score){ // if playerScore is greater than score
                    highList.add(i,nickname+ " " +playerScore); // adds all indexes nickname and playerScore to the ArrayList
                    break; // once adding the data to ArrayList, stop
                }
            }
            PrintWriter writer = new PrintWriter("HighScore.txt");
            for (int i = 0; i < highList.size();i++){ // iterating through Arraylist size
                writer.print( i+1 + " " +  highList.get(i) + "\n"); // write this to file


            }
            writer.close(); // closes the PrintWriter

        }
    }
}
