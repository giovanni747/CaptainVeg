/*    Author:      Giovanni Sanchez (gsanchez@muhlenberg.edu)
 *    Date:        04/14/23
 *    Description: Is a subclass of FieldInhabitants and has class scoped variables with Encapsulating methods
 */
public class Creature extends FieldInhabitant{ // a subclass of FieldInhabitant
    private int x_Coordinates; //A class scoped integer variables to represent the x coordinate
    private int y_Coordinates; //A class scoped integer variables to represent the y coordinate

    public Creature(int x_Coordinates,int y_Coordinates, String symbolCreature) // A constructor that takes in two integer values x and y coordinates and the String Symbol

    {
        super(symbolCreature); // The symbol value is provided to the super class’s constructor
        this.x_Coordinates = x_Coordinates; // he x and y coordinate values are assigned to the class scoped variables
        this.y_Coordinates = y_Coordinates;

    }

    public int getX_Coordinates() {
        return x_Coordinates;
    } // getter method

    public void setX_Coordinates(int x_Coordinates) {
        this.x_Coordinates = x_Coordinates;
    } // setter method

    public int getY_Coordinates() {
        return y_Coordinates;
    } // getter method

    public void setY_Coordinates(int y_Coordinates) {
        this.y_Coordinates = y_Coordinates;
    } // setter method
}
