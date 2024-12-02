import java.io.*;
//=================================================================================================

/**
 * Maintains data for Boat objects
 * author Ola Umeh
 * version V1
 */
public class Boat implements Serializable {
    /**
     * Global enum data for boat type
     */
    public enum BoatType {SAILING, POWER}
    //-------------------------------------------------------------------------------------------------
    /**
     * Variables to hold data about Boat objects
     */
    private BoatType type;
    private String name;
    private int yearOfManufacture;
    private String makeAndModel;
    private int length;
    private double purchasePrice;
    private double expenses;
    //-------------------------------------------------------------------------------------------------

    /**
     * No parameter constructor
     */
    public Boat() {
        type = null;
        name = null;
        yearOfManufacture = 0;
        makeAndModel = null;
        length = 0;
        purchasePrice = 0.0;
        expenses = 0.00;
    }
    //-------------------------------------------------------------------------------------------------
    /**
     * Constructor with every boat attribute except expenses
     * @param type Boatype enum for type of boat
     * @param name String for boat name
     * @param yearOfManufacture Integer for boat's year of manufacture
     * @param makeAndModel String for boat model
     * @param length Integer for boat length
     * @param purchasePrice Double for boat's purchase price
     */
    public Boat(BoatType type, String name, int yearOfManufacture, String makeAndModel, int length, double purchasePrice) {

        this();

        this.type = type;
        this.name = name;
        this.yearOfManufacture = yearOfManufacture;
        this.makeAndModel = makeAndModel;
        this.length = length;
        this.purchasePrice = purchasePrice;
        expenses = 0.00;
    }
    //-------------------------------------------------------------------------------------------------
    /**
     * Formats how each boat object should be printed
     * @return A formatted boat object
     */
    public String toString() {

        return String.format("%-8s %-20s %4d %-12s %4d' : Paid $ %10.2f : Spent $ %10.2f",
                type, name, yearOfManufacture, makeAndModel, length, purchasePrice, expenses);
    }
    //-------------------------------------------------------------------------------------------------

    /**
     * Accessor method for purchase price
     * @return The boat's purchase price
     */
    public double getPurchasePrice() {

        return(purchasePrice);
    }

    /**
     * Accessor method for boat name
     * @return The boat's name
     */
    public String getBoatName() {

        return(name);
    }

    /**
     * Accessor method for boat expenses
     * @return The boat's expenses
     */
    public double getExpenses() {

        return(expenses);
    }

    /**
     * Setter method for expenses
     * @param expenditure Double for amount spent on boat
     */
    public void setExpenses(double expenditure){

        expenses = expenditure;

    }

//-------------------------------------------------------------------------------------------------
}
//=================================================================================================