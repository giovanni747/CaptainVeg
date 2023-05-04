/*    Author:      Giovanni Sanchez (gsanchez@muhlenberg.edu)
 *    Date:        04/14/23
 *    Description: Has a class scoped String variable to store a symbol with Encapsulating methods
 */
public class FieldInhabitant {
    private String Symbol; // String variable that stores symbol

    public FieldInhabitant(String Symbol)
    {
        this.Symbol = Symbol;
    } // constructors
    public String getSymbol() {
        return Symbol;
    } // getter method
    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    } // setter method
}