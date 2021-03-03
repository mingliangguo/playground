/**
 * the Symbol class represents a single symbol used in the Whack A Mole Game.
 * @author Mingyu Sun
 */
public class Symbol {

    /** Name of the symbol */
    private String name;

    /** Points of the symbol */
    private int points;

    /** Determine whether has click */
    private boolean hasBeenClickedOn;

    /**
     * Contructor and initializes a Symbol object
     * @param name name of the symbol
     * @param points points of the symbol
     * @throws NullPointerException if name is null
     * @throws IllegalArgumentException if points are invalid
     */
    public Symbol(String name, int points) {
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        if (points < 1) {
            throw new IllegalArgumentException("Invalid points");
        }
        this.name = name;
        this.points = points;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the points
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * This is the getter method for the instance field that knows whether
     * the symbol has been clicked on. It simply returns the instance field.
     * @return hasBeenClickedOn shows whether has been clicked
     */
    public boolean hasBeenClickedOn() {
        return hasBeenClickedOn;
    }

    /**
     * This is the setter method for the instance field that knows whether
     * the symbol has been clicked on. It simply sets the value of the instance field
     * to the value that was passed as a parameter.
     * @param hasBeenClickedOn shows whether the button has been clicked
     */
    public void setHasBeenClickedOn(boolean hasBeenClickedOn) {
        this.hasBeenClickedOn = hasBeenClickedOn;
    }

    /**
     * Determine if the given Symbol object has the same
     * state as this Symbol object
     *
     * @param o Symbol object to compare
     * @return true     if given Symbol object equals this Symbol object
               false    otherwise
     */
    public boolean equals(Object o) {
        if (o instanceof Symbol) {
            Symbol other = (Symbol) o;
            return name.equals(other.name) && points == other.points;
        } else {
            return false;
        }
    }

    /**
     * Return a String representation of the Symbol object
     * @return String containing name, points, whether has been clicked
     */
    public String toString() {
        return String.format("%s %d %b", name, points, hasBeenClickedOn);
    }

    public static void main(String[] args) {
      Symbol symbol = new Symbol("Tom", 25);
      System.out.println(symbol);
    }
}
