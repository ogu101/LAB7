import java.util.Scanner;

public class Practice {

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Practice arrays");

        //Declaring an array
        int[] myArray = new int[3];

        //Declaring 2 Dimension array
        int[][] yourArray = new int[3][2];

        //Update array
        int rowIndex;
        int colIndex;

        for (rowIndex = 0; rowIndex < yourArray.length; rowIndex++) {

            for (colIndex = 0; colIndex < yourArray[rowIndex].length; colIndex++) {

                System.out.println("At index [" + rowIndex + "][" + colIndex + "] : Enter a number: ");
                yourArray[rowIndex][colIndex] = keyboard.nextInt();

            }// end of the inner for loop --> the col

        }//end of the outer for loop --> the row

        //Display the 2D array
        for (rowIndex = 0; rowIndex < yourArray.length; rowIndex++) {

            for (colIndex = 0; colIndex < yourArray[rowIndex].length; colIndex++) {

                System.out.println("At index [" + rowIndex + "][" + colIndex + "] : The number is : " + yourArray[rowIndex][colIndex]);
            }// end of the inner for loop --> the col

        }//end of the outer for loop --> the row


    } // end of the main method

    private static void updateMyArray(int[] myArray) {
        //Update aray
        int index;
        for (index = 0; index < myArray.length; index++) {

            System.out.println(" At index  " + index + ": Enter a number:");
            myArray[index] = keyboard.nextInt();
        }// end of the for loop

        //Display array
        for (index = 0; index < myArray.length; index++) {

            System.out.println("At index  " + index + " The number is " + myArray[index]);
        }
    }// end of the updateMyArray method

}// end of the practice class
