import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
//=================================================================================================
/**
 * Manages boat data for a fleet of boats
 * @author olaumeh
 * @version V1
 */
public class FleetManagementSystem {
    //-------------------------------------------------------------------------------------------------
    /**
     * Global Scanner object to use keyboard
     */
    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * Global arraylist
     */
    private static ArrayList<Boat> boatsInFleet = new ArrayList<Boat>();
    /**
     * Maximum boat length is 100 feet
     */
    private static final int MAX_BOAT_LENGTH = 100;
    /**
     * Maximum boat purchase price is $1,000,000
     */
    private static final double MAX_PURCHASE_PRICE = 1000000;
    //-------------------------------------------------------------------------------------------------

    /**
     * The main method
     * @param args Passed in from the command line
     */
    public static void main(String[] args) {

//------Variables to hold information for the file handling
        String fileName;
        String input;
        char option = 0;
        String csvData;
        String[] newBoatInfo;

//------Variables to hold information for each boat attribute
        Boat.BoatType type;
        String name;
        int yearOfManufacture;
        String makeAndModel;
        int length;
        double purchasePrice;

//------Variables to hold the name of the boat to remove/expend money on
        String nameToRemove;
        String boatToSpendOn;


//------Loads boat fleet data into program from CSV if there is a command line argument
        if (args.length > 0) {
            fileName = args[0];
            loadBoatFleetFromCSV(fileName);

//------Loads boat fleet data from DB if there is no command line argument
        } else {
            loadBoatFleetFromDB();
        }

//------Welcome message
        System.out.println("Welcome to the Fleet Management System\n" +
                "--------------------------------------\n");

//------Displays menu options as long as the user doesn't exit the program
        do {

//----------Gets user input
            System.out.print("(P)rint, (A)dd, (R)emove, (E)xpense, e(X)it : ");
            input = keyboard.nextLine();

//----------Validates user input
            if (input.isEmpty()){
                System.out.println("No input is provided. Please enter a valid option.");
                continue;
            }
            option = Character.toUpperCase(input.charAt(0));

//----------Performs user menu selection
            switch (option) {

//--------------P : prints boat fleet data
                case 'P':
                    System.out.println(" ");
                    printBoatInventory();
                    System.out.println(" ");
                    break;

//--------------A : adds new boat to fleet
                case 'A':

//------------------Gets user input
                    System.out.print("Please enter the new boat CSV data          : ");
                    csvData = keyboard.nextLine();
                    newBoatInfo = csvData.split(",");
                    System.out.println(" ");

//------------------Assigns each boat property variable with the user input
                    type = Boat.BoatType.valueOf(newBoatInfo[0]);
                    name = newBoatInfo[1];
                    yearOfManufacture = Integer.parseInt(newBoatInfo[2]);
                    makeAndModel = newBoatInfo[3];
                    length = Integer.parseInt(newBoatInfo[4]);
                    purchasePrice = Double.parseDouble(newBoatInfo[5]);

//------------------Validate boat length input
                    while (length > MAX_BOAT_LENGTH){
                        System.out.println("Invalid input. Please enter a boat length less than 100 feet.");
                        length = keyboard.nextInt();
                    }

//------------------Validates purchase price input
                    while (purchasePrice > MAX_PURCHASE_PRICE){
                        System.out.println("Invalid input. Please enter a purchase less than $1,000,000.");
                        purchasePrice = keyboard.nextDouble();
                    }

//------------------Adds boat to fleet
                    addBoat(type, name, yearOfManufacture, makeAndModel, length, purchasePrice);

                    break;

//--------------R : removes boat from fleet
                case 'R':

//------------------Gets user input
                    System.out.print("Which boat do you want to remove?           : ");
                    nameToRemove = keyboard.nextLine();

//------------------Validates user input and removes boat accordingly
                    if (!removeBoat(nameToRemove)){
                        System.out.println("Cannot find boat " + nameToRemove);

                }
                    System.out.println(" ");
                    break;

//--------------E : calculates boat expenses
                case 'E':

//------------------Gets user input
                    System.out.print("Which boat do you want to spend on?         : ");
                    boatToSpendOn = keyboard.nextLine();

//------------------Validates user input and updates expenses accordingly
                    expenses(boatToSpendOn);

                    break;

//--------------X : exits the program
                case 'X':
                    System.out.println("Exiting the Fleet Management System");
                    break;

//--------------Default : validates menu option input
                default:
                    System.out.println("Invalid menu option, try again");
                    break;
            }
        } while (option != 'X');

//------Writes to DB file with updated fleet data
        writeToDB();

    }// end of main method
//-------------------------------------------------------------------------------------------------

/**
 * Loads fleet data from DB file
 */
public static void loadBoatFleetFromDB(){

//--Creates instance of ObjectInputStream
    ObjectInputStream fromStream = null;

//--Handles exceptions during file handling
    try {

//------Links fromStream to object data in FleetData.db
        fromStream = new ObjectInputStream(new FileInputStream("FleetData.db"));

//------Sets boatsInFleet equal to list of boats from db file
        boatsInFleet = (ArrayList<Boat>) fromStream.readObject();

//--Catches IOExceptions
    } catch (IOException e) {
        System.out.println("ERROR loading " + e.getMessage());

//--Catches ClassNotFoundExceptions
    } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());

    } finally {
        if (fromStream != null) {
            try {
//--------------Closes fromStream
                fromStream.close();

//----------Catches IOExceptions while closing file
            } catch (IOException e) {
                System.out.println("ERROR closing " + e.getMessage());

            }
        }
    }

} // end of loadBoatFleetFromDB method
//-------------------------------------------------------------------------------------------------

/**
 * Loads boat data from CSV file
 * @param fileName Name of the CSV file to load the data from
 */
public static void loadBoatFleetFromCSV(String fileName){

//--Creates reader object
    BufferedReader fromBufferedReader = null;

//--Holds data from each line of the CSV file
    String aline;

//--Handles exceptions during file handling
    try {

//------Makes fromBufferedReader a reference to the character data in the file provided
        fromBufferedReader = new BufferedReader(new FileReader(fileName));

//------Variable to hold content from each line in the file
        aline = fromBufferedReader.readLine();

//------Loops until the end of the file
        while (aline != null){

//----------Places the data in the line into an array of strings
            String [] aBoatDataInCurrentLine = aline.split(",");

//----------Assigns each member of the array to a boat attribute
            Boat.BoatType type = Boat.BoatType.valueOf(aBoatDataInCurrentLine[0].toUpperCase());
            String name = aBoatDataInCurrentLine[1];
            int yearOfManufacture = Integer.parseInt(aBoatDataInCurrentLine[2]);
            String makeAndModel = aBoatDataInCurrentLine[3];
            int length = Integer.parseInt(aBoatDataInCurrentLine[4]);
            double purchasePrice = Double.parseDouble(aBoatDataInCurrentLine[5]);

//----------Creates a new boat object
            Boat aNewBoat = new Boat(type, name, yearOfManufacture, makeAndModel, length, purchasePrice);

//----------Adds boat object to array list
            boatsInFleet.add(aNewBoat);

//----------Reads the next line
            aline =  fromBufferedReader.readLine();

        }

//--Catches IOExceptions
    } catch (IOException e){

        System.out.println("Error reading " + e.getMessage());

    } finally {
        if (fromBufferedReader != null){

//----------Closes file
            try {
                fromBufferedReader.close();

//----------Catches IOExceptions that occur while trying to close file
            } catch (IOException e){
                System.out.println("Error closing " + e.getMessage());
            }
        }
    }

} // end of loadBoatFleetFromCSV method
//-------------------------------------------------------------------------------------------------

/**
 * Prints boat inventory
 */
public static void printBoatInventory() {

//--Variables to iterate through arrayList and calculate total purchase price/expenses
    int index;
    double totalPurchasePrices = 0;
    double totalExpenses = 0;

    System.out.println("Fleet report:");

//--Iterates through array list and prints each boat object and calculates total purchase price/expenses
    for (index = 0; index < boatsInFleet.size(); index++) {
        if (boatsInFleet.get(index) != null) {
            System.out.print("  ");
            System.out.println(boatsInFleet.get(index));
        }

        totalPurchasePrices += boatsInFleet.get(index).getPurchasePrice();
        totalExpenses += boatsInFleet.get(index).getExpenses();
    }

//--Prints total purchase price/expenses
    System.out.printf("  %-53s : Paid $ %10.2f : Spent $ %10.2f%n",
            "Total", totalPurchasePrices, totalExpenses);
} // end of printBoatInventory method
//-------------------------------------------------------------------------------------------------

/**
 * Adds new boat object to arrayList of boatsInFleet
 * @param type Boatype enum for type of boat
 * @param name String for boat name
 * @param yearOfManufacture Integer for boat's year of manufacture
 * @param makeAndModel String for boat model
 * @param length Integer for boat length
 * @param purchasePrice Double for boat's purchase price
 */
public static void addBoat(Boat.BoatType type, String name, int yearOfManufacture, String makeAndModel, int length
        , double purchasePrice) {

    Boat newBoat;
    newBoat = new Boat(type, name, yearOfManufacture, makeAndModel, length, purchasePrice);

    boatsInFleet.add(newBoat);

} // end of addBoat method
//-------------------------------------------------------------------------------------------------

/**
 * Removes boat object from array list
 * @param nameToRemove Name of the boat the user wants to remove
 * @return Boolean
 */
public static boolean removeBoat(String nameToRemove) {

//--Variables to iterate through boatsInFleet array list
    int index;
    Boat currentBoat;

//--Iterates through boatsInfleet
    for (index = 0; index < boatsInFleet.size(); index++){

        currentBoat = boatsInFleet.get(index);

//------Checks if nameToRemove is present in arrayList + removes that boat
        if ((currentBoat != null) && nameToRemove.equalsIgnoreCase(currentBoat.getBoatName())){

            boatsInFleet.remove(index);

            return true;

        }
    }

    return false;

} // end of removeBoat method
//-------------------------------------------------------------------------------------------------

/**
 * Calculates the expenditure for a boat and determines if more money may be spent
 * @param boatName Name of the boat of which the user wants to spend money on
 */
public static void expenses(String boatName){

//--Variables to iterate through boatsInFleet array list
    int index;
    Boat currBoat;

//--Variable to calculate boat expenses
    double expenditure;
    double potentialExpenses;
    double moneyLeft;
    boolean boatExists = false;
    Boat boat = null;

//--Iterates through boatsInFleet
    for (index = 0; index < boatsInFleet.size() ; index++) {

        currBoat = boatsInFleet.get(index);

//------Checks if the boat exists in the fleet
        if ((currBoat != null && boatName.equalsIgnoreCase(currBoat.getBoatName()))){

            boatExists = true;
            boat = currBoat;
            break;

        }

    }

//--Validates boatName input
    if (boatExists){

//------Gets user input
        System.out.print("How much do you want to spend?              : ");
        expenditure = keyboard.nextDouble();
        keyboard.nextLine();

//------Calculates potential expenses
        potentialExpenses = boat.getExpenses() + expenditure;

//------Checks if potential expenses are less than the purchase price of the boat
        if (potentialExpenses <= boat.getPurchasePrice()){

//----------Authorizes expenditure + updates expenses
            boat.setExpenses(potentialExpenses);
            System.out.println("Expense authorized, $" + String.format("%.2f",potentialExpenses ) + " spent.");
            System.out.println("   ");

        }

        else {

//----------Does not authorize expenditure
            moneyLeft = boat.getPurchasePrice() - boat.getExpenses();
            System.out.println("Expense not permitted, only $" + String.format("%.2f",moneyLeft ) + " left to spend." );
            System.out.println("   ");

        }


    }

    else {
        System.out.println("Cannot find boat " + boatName);
        System.out.println("   ");
    }

    }// end of expenses method

//-------------------------------------------------------------------------------------------------

/**
 * Writes to DB file
 */
public static void writeToDB(){

//--Creates instance of ObjectOutputStream
    ObjectOutputStream toStream = null;

    try {
//------Links toStream to object data within the FleetData.db file
        toStream = new ObjectOutputStream(new FileOutputStream("FleetData.db"));

//------Updates file with boatsInFleet data
        toStream.writeObject(boatsInFleet);

//--Catches IOExceptions
    } catch (IOException e){

        System.out.println("Error writing " + e.getMessage());

    }

} // end of writeToDB method
//-------------------------------------------------------------------------------------------------
} // end of FleetManagementSystem class
//=================================================================================================
