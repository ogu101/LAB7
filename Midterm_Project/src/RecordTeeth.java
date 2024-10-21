import java.util.Scanner;
public class RecordTeeth {
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        // Get user input
        int numMembers;

        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("---------------------------------------");

        System.out.println("Please enter number of people in the family : ");
        numMembers = keyboard.nextInt();

        // Validate user input
        do {
            System.out.println("Invalid number of people, try again : ");
            numMembers = keyboard.nextInt();
        } while ((numMembers <= 0) || (numMembers > 6));

        // Get family dental records

        // Display menu

    } // end of the main method

    public static String [][][] getDentalRecords(int numMembers){

        // Variables to hold getDentalRecords
        String [][][] dentalRecords = new String[numMembers][2][];
        int arrayIndex;
        int rowIndex;
        int colIndex;
        String [] colNames = {"uppers", "lowers"};

        // Fill getDentalRecords with user input
        // Outermost loop iterates through the number of arrays (an array for each family member)
        for (arrayIndex = 0; arrayIndex < numMembers; ++arrayIndex){
            String memberName;
            System.out.print("Please enter the name for family member " + (arrayIndex + 1) + "   : ");
            memberName = keyboard.next();

            // Middle loop iterates through each row in each array (row 1 = uppers, row 2 = lowers)
            for (rowIndex = 0; rowIndex < 3; ++rowIndex){
                System.out.println("Please enter the " + colNames[rowIndex]  + " for " + memberName + "       : ");
                String teethPerRow = keyboard.next();
                // Validate user input

               for (colIndex = 0; colIndex < teethPerRow.length(); ++colIndex ){
                   //Why am i getting an error here?
                   dentalRecords[arrayIndex][rowIndex][colIndex] = teethPerRow.charAt(colIndex);



               } // end of column loop

           } // end of row loop

        } // end of array loop

        return dentalRecords;

    } // end of getDentalRecords

} // end of class
