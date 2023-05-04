/*    Author:      Giovanni Sanchez (gsanchez@muhlenberg.edu)
 *    Date:        04/14/23
 *    Description: Is a subclass of FieldInhabitants and has class scoped variables with Encapsulating methods
 */
public class Veggie extends FieldInhabitant{ // Veggie is a subclass of fieldInhabitants

    private String veggieName; // A class scoped String variable representing the name of the vegetable
    private int veggiePoints = 0; // A class scoped integer variable representing the number of points of that vegetable is worth

    public Veggie(String veggieName,String veggieSymbol,int veggiePoints) //  A constructor that takes in two String values and an int
    {
        super(veggieSymbol); // The symbol is provided to the super class’s constructor
        this.veggieName = veggieName; // The name and points values are assigned to the class scoped variables
        this.veggiePoints = veggiePoints;

    }


    public String getVeggieName() {
        return veggieName;
    } // getter method

    public void setVeggieName(String veggieName) {
        this.veggieName = veggieName;
    } // setter method

    public int getVeggiePoints() {
        return veggiePoints;
    } // getter method

    public void setVeggiePoints(int veggiePoints) {
        this.veggiePoints = veggiePoints;
    } // setter method
}
