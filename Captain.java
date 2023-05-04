/*    Author:      Giovanni Sanchez (gsanchez@muhlenberg.edu)
 *    Date:        04/14/23
 *    Description: Is a subclass of Creature and has class scoped ArrayList with super encapsulating methods
 */
import java.util.ArrayList;
public class Captain extends Creature{ // a subclass of Creature
    private ArrayList<Veggie> veggieList; // A class scoped ArrayList of type Veggie

    public Captain(int valueX,int valueY) // A constructor that takes in two integer values representing x and y coordinates
    {
        super(valueX,valueY,"V"); // The x and y coordinate values and the String “V” should be provided to the super class’s constructor
        veggieList = new ArrayList<Veggie>(); // The ArrayList is initialized to an empty ArrayList
    }

    public <veggieList> void addVeggie( Veggie veggie) // A method named addVeggie() that takes in a Veggie object as a parameter,returns nothing,and adds the object to the ArrayList
    {
        veggieList.add(veggie);
    }
    //  Encapsulating methods
    public ArrayList<Veggie> getVeggieList() {
        return veggieList;
    }

    public void setVeggieList(ArrayList<Veggie> veggieList) {
        this.veggieList = veggieList;
    }
    public int getX(){
        return super.getX_Coordinates();
    }
    public int getY(){
        return super.getY_Coordinates();
    }
    public void setX(int x){
        super.setX_Coordinates(x);
    }
    public void setY(int Y){
        super.setY_Coordinates(Y);

    }
}
