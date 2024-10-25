import java.util.Scanner;

/**
 * Calculates the total ages per house on a street
 * @author olaumeh
 */
public class StreetPeople {

    private static Scanner keyboard = new Scanner(System.in);

    /**
     * Main method where the input is collected and the calucluations are performed
     * @param args
     */
    public static void main(String[] args) {

        // Get number of houses in the street
        int numHouses;
        System.out.print("How many houses in the street?");
        numHouses = keyboard.nextInt();

        //  Get house numbers for each house
        int [] houseNumbers = new int [numHouses];
        int houseNumbersIndex;

        for (houseNumbersIndex = 0; houseNumbersIndex < numHouses; houseNumbersIndex++){
            System.out.print("What is the next house number?");
            houseNumbers[houseNumbersIndex] = keyboard.nextInt();
        }// end of the for loop

        // Get ages for each house
        int [][] houseAges = new int [numHouses][];
        int rowIndex;
        int colIndex;

        for(rowIndex = 0; rowIndex < houseAges.length; rowIndex++){
            System.out.println("How many people live in number " + houseNumbers[rowIndex]);
            int numPeoplePerHouse = keyboard.nextInt();

            houseAges[rowIndex] = new int[numPeoplePerHouse];

            for(colIndex = 0; colIndex < houseAges[rowIndex].length; colIndex++){
                System.out.println("What is that age of person  " + (colIndex + 1));
                houseAges[rowIndex][colIndex] = keyboard.nextInt();
            }// end of the inner loop

        }// end of the outer loop

        // Perform calculations

        // Total age per house


        for(rowIndex = 0; rowIndex < houseAges.length; rowIndex++){

            int totalAge = 0;
            int streetTotalAge;

            for(colIndex = 0; colIndex < houseAges[rowIndex].length; colIndex++){
                totalAge = totalAge + houseAges[rowIndex][colIndex];

            }// end of the inner loop

            System.out.println("House " + houseNumbers[rowIndex] + " has a total age of " + totalAge);

        }// end of the outer loop


    } //end of the main method


} //  end of the StreetPeople class
