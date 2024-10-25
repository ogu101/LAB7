import java.util.Scanner;
//=====================================================================================================================

/**
 * Record the teeth of members in one family
 * @author Ola Umeh
 * @version V2
 */

public class RecordTeeth_V2 {
//---------------------------------------------------------------------------------------------------------------------
    /**
     * Global Scanner object to use keyboard
     */
    private static Scanner keyboard = new Scanner(System.in);
    /**
     * Maximum number of family members is 6
     */
    private static final int MAX_FAMILY_MEMBERS = 6;

    /**
     * Maximum number of teeth is 8
     */
    private static final int MAX_TEETH = 8;
//---------------------------------------------------------------------------------------------------------------------

    /**
     * The main method
     * @param args Passed in from the command line
     */
    public static void main(String[] args) {

//-------Variables to hold the number of family members, member names, and family dental records
        int numMembers;
        String [] memberNamesList;
        char[][][] dentalRecords;

//-------Display welcome message
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("---------------------------------------");

//-------Get number of people in family
        System.out.print("Please enter number of people in the family : ");
        numMembers = keyboard.nextInt();

//-------Validate user input for numMembers
        while((numMembers <=0)|| (numMembers > MAX_FAMILY_MEMBERS)){
            System.out.print("Invalid number of people, try again         : ");
            numMembers = keyboard.nextInt();
        }

//-------Get family member names and dental records
        memberNamesList = new String[numMembers];
        dentalRecords = new char[numMembers][2][];
        getDentalRecords(memberNamesList, dentalRecords);

//-------Display menu
        String userMenuChoice = diplayMenuAndGetUserMenuChoice().toUpperCase();

        while (!userMenuChoice.equals("X")){
            if (userMenuChoice.equals("P")){
                printDentalRecords(dentalRecords,memberNamesList);
            } else if (userMenuChoice.equals("E")) {
                extractTooth(dentalRecords, memberNamesList );
            } else if (userMenuChoice.equals("R")) {
                findRoots(dentalRecords);
            }

            userMenuChoice = diplayMenuAndGetUserMenuChoice();

        } // end of while loop

//-------Display exit message
        System.out.println(" ");
        System.out.println("Exiting the Floridian Tooth Records :-)");

    } // end of the main method
//---------------------------------------------------------------------------------------------------------------------
    /**
     * Get the dental records for each member in a family
     * @param memberNamesList List  of family member names
     * @param dentalRecords 3D array for the teeth of each member in the family
     */
    private static void getDentalRecords(String[]memberNamesList, char [][][] dentalRecords){

//-------Variables to hold array index, row index, column index
        int arrayIndex;
        int rowIndex;
        int colIndex;

//-------Variable to hold teeth string input
        String teethPerRow;

//-------Variable to display name of column in user prompt
        String [] colNames = {"uppers", "lowers"};

//-------Fill getDentalRecords with user input
        for (arrayIndex = 0; arrayIndex < dentalRecords.length; ++arrayIndex){

//-----------Get family member names
            System.out.print("Please enter the name for family member " + (arrayIndex + 1) + "   : ");
            memberNamesList[arrayIndex] = keyboard.next();

            for (rowIndex = 0; rowIndex < dentalRecords[arrayIndex].length; ++rowIndex){

//---------------Get teeth characters for each row
                System.out.print("Please enter the " + colNames[rowIndex]  + " for " + memberNamesList[arrayIndex]
                                 + "       : ");
                teethPerRow = keyboard.next().toUpperCase();

//---------------Validate teethPerRow input
                while (!(isTeethValid(teethPerRow))){
                    System.out.print("Invalid teeth types, try again              : ");
                    teethPerRow = keyboard.next().toUpperCase();
                }

                while (teethPerRow.length() > MAX_TEETH){
                    System.out.print("Too many teeth, try again                   : ");
                    teethPerRow = keyboard.next();
                }

//---------------Initialize row index
                dentalRecords[arrayIndex][rowIndex] = new char [teethPerRow.length()];

               for (colIndex = 0; colIndex < teethPerRow.length(); ++colIndex ){

//------------------Fill each column with tooth character
                   dentalRecords[arrayIndex][rowIndex][colIndex] = teethPerRow.charAt(colIndex);

               } // end of column loop

           } // end of row loop

        } // end of array loop


    } // end of getDentalRecords  method
//---------------------------------------------------------------------------------------------------------------------
    /**
     * Validates the tooth input
     * @param teethPerRow The string of teeth in a row
     * @return A boolean value indicating if the teethPerRow input is valid or not
     */
    public static boolean isTeethValid (String teethPerRow){

//-------Variable to hold teethIndex
        int teethIndex;

//-------Check if tooth is missing
        for (teethIndex  = 0; teethIndex < teethPerRow.length();teethIndex++){

            if (teethPerRow.charAt(teethIndex) != 'I' && teethPerRow.charAt(teethIndex) != 'B'
                && teethPerRow.charAt(teethIndex) != 'M'  ) {
                return false;
            }
        }
        return true;
    }  // end of the isTeethValid method
//---------------------------------------------------------------------------------------------------------------------
    /**
     * Displays the program menu and gets user menu selection
     * @return User menu selection
     */
    public static String diplayMenuAndGetUserMenuChoice(){

//-------Variable to hold userMenuChoice
        String userMenuChoice;

//-------Display menu
        System.out.println(" ");
        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it          : ");

 //------Get user choice
        userMenuChoice = keyboard.next().toUpperCase();

//-------Validate userMenuChoice
        while (!userMenuChoice.equals("P")  && !userMenuChoice.equals("E") && !userMenuChoice.equals("R")
                && !userMenuChoice.equals("X") ){
            System.out.print("Invalid menu option, try again              : ");
            userMenuChoice = keyboard.next().toUpperCase();
        }
        return userMenuChoice;
    } // end of displayMenu method
//---------------------------------------------------------------------------------------------------------------------
    /**
     * Displays dental records for the whole family
     * @param dentalRecords 3D array for the teeth of each member in the family
     * @param memberNamesList List  of family member names
     */
    public static void printDentalRecords(char [][][] dentalRecords, String [] memberNamesList) {

//-------Variables to hold arrayIndex, rowIndex, colIndex
        int arrayIndex;
        int rowIndex;
        int colIndex;

//-------Variable to display row names in user prompt
        String [] colNames = {"Uppers", "Lowers"};

//-------Display family dental records
        for (arrayIndex = 0; arrayIndex < dentalRecords.length; ++arrayIndex){

            if (arrayIndex == 0){
                System.out.println(" ");
                System.out.println(memberNamesList[arrayIndex]);
            }
            else {
                System.out.println(memberNamesList[arrayIndex]);
            }

            for (rowIndex = 0; rowIndex < dentalRecords[arrayIndex].length; ++rowIndex){
                System.out.print("  "+ colNames[rowIndex]  + ":  ");

                for (colIndex = 0; colIndex < dentalRecords[arrayIndex][rowIndex].length; ++colIndex ){

                    if (colIndex == dentalRecords[arrayIndex][rowIndex].length - 1){
                        System.out.println((colIndex + 1) + ":" + dentalRecords[arrayIndex][rowIndex][colIndex] + "  ");
                    }
                    else {
                        System.out.print((colIndex + 1) + ":" + dentalRecords[arrayIndex][rowIndex][colIndex] + "  ");
                    }
                } // end of column loop

            } // end of row loop

        } // end of array loop


    } // end of printDentalRecords method
// ---------------------------------------------------------------------------------------------------------------------
    /**
     * Removes a tooth from the dental record and marks it as missing
     * @param dentalRecords 3D array for the teeth of each member in the family
     * @param memberNamesList List of family member names
     */
    public static void extractTooth(char[][][] dentalRecords, String [] memberNamesList ){

//-------Variable to hold family member name
        String memberName;

//--------Variables to hold user input: indexExtraction, toothLayer, toothNumber, toothToExtract
        int indexExtraction;
        String toothLayer;
        int toothNumber;
        char toothToExtract;

//-------Variables to hold arrayIndex, rowIndex, colIndex
        int arrayIndex;
        int rowIndex;
        int colIndex;

//-------Get family member name
        System.out.print("Which family member                         : ");
        memberName = keyboard.next().toUpperCase();

//-------Validate memberName input
        while (!isMemberNameValid(memberName, memberNamesList)){
            System.out.print("Invalid family member, try again            : ");
            memberName = keyboard.next().toUpperCase();
        }

//-------Get tooth layer of extraction
        System.out.print("Which tooth layer (U)pper or (L)ower        : ");
        toothLayer = keyboard.next().toUpperCase();

//-------Validate toothLayer input
        while (!(toothLayer.equals("U")) && !(toothLayer.equals("L"))){
            System.out.print("Invalid layer, try again                    : ");
            toothLayer = keyboard.next().toUpperCase();
        }

        if (toothLayer.equals("U")){
            rowIndex = 0;
        }
        else {
            rowIndex = 1;
        }

 //------Get tooth number of extraction
        System.out.print("Which tooth number                          :");
        toothNumber = keyboard.nextInt();

 //------Compute array index of tooth number
        arrayIndex = getArrayIndex(memberName, memberNamesList);

//-------Validate toothNumber input
        while (!(toothNumber <= dentalRecords[arrayIndex][rowIndex].length)){
            System.out.print("Invalid tooth number, try again             :");
            toothNumber = keyboard.nextInt();
        }

        while (dentalRecords[arrayIndex][rowIndex][toothNumber -1] == 'M'){
            System.out.print("Missing tooth, try again                    :");
            toothNumber = keyboard.nextInt();
        }

//-------Compute column index of tooth number
        colIndex = toothNumber - 1;

//-------Extract tooth in dental records
        dentalRecords[arrayIndex][rowIndex][colIndex] = 'M';

    } // end of extractTooth method
// ---------------------------------------------------------------------------------------------------------------------

    /**
     * Validates memberName input
     * @param memberName Name of family member
     * @param memberNamesList List of famiy member names
     * @return A Boolean value indicating if memberName is valid
     */
    private static boolean isMemberNameValid(String memberName, String [] memberNamesList ){

//-------Variable to hold index of memberNamesList
        int index;

        for (index = 0; index < memberNamesList.length; index++){
            if (memberName.equals(memberNamesList[index].toUpperCase())){

                return true;
            }

        }

        return false;

    } // end of isMemberNameValid method

    /**
     * Gets array index based off of member name
     * @param memberName Name of family member
     * @param memberNamesList List of family member names
     * @return Index value
     */
    private static int getArrayIndex(String memberName, String [] memberNamesList ){
        for (int index = 0; index < memberNamesList.length; index++) {

            if (memberName.equals(memberNamesList[index].toUpperCase())) {
                return index;
            }
        }

        return -1;

    } // end of getArrayIndex method
//---------------------------------------------------------------------------------------------------------------------

    /**
     * Identifies root canals in family dental records
     * @param dentalRecords 3D array for the teeth of each member in the family
     */
    private static void findRoots(char[][][] dentalRecords){
        int arrayIndex;
        int rowIndex;
        int colIndex;

        int incisorTotal = 0;
        int bicuspidTotal = 0;
        int missingTotal = 0;

        double discriminant;
        double root1;
        double root2;
        double [] result;

        // Outermost loop iterates through the number of arrays (an array for each family member)
        for (arrayIndex = 0; arrayIndex < dentalRecords.length; ++arrayIndex){

            // Middle loop iterates through each row in each array (row 0 = uppers, row 1 = lowers)
            for (rowIndex = 0; rowIndex < dentalRecords[arrayIndex].length; ++rowIndex){

                // inner loop iterates through each column in array (each column represents a tooth)
                for (colIndex = 0; colIndex < dentalRecords[arrayIndex][rowIndex].length; ++colIndex ){

                    if (dentalRecords[arrayIndex][rowIndex][colIndex] == 'I') {
                        incisorTotal += 1;
                    } else if (dentalRecords[arrayIndex][rowIndex][colIndex] == 'B') {
                        bicuspidTotal += 1;
                    } else if (dentalRecords[arrayIndex][rowIndex][colIndex] == 'M') {
                        missingTotal += 1;
                    }

                } // end of column loop

            } // end of row loop

        } // end of array loop

        discriminant = bicuspidTotal * bicuspidTotal - 4 * incisorTotal * -missingTotal;

        result = new  double[3];

        if (discriminant >= 0) {
            root1 = (-bicuspidTotal + Math.sqrt(discriminant)) / (2 * incisorTotal);
            root2 = (-bicuspidTotal - Math.sqrt(discriminant)) / (2 * incisorTotal);

            result[0] = root1;
            result[1] = root2;
            result[2] = 1;

            if (result[0] != result[1]){
                System.out.printf("One root canal at  %.2f   \n",  root1);
                System.out.printf("Another root canal at %.2f\n ", root2);
            }
            else{
                System.out.println("One root at " + root1);
            }

        }

    } // end of findRoots method
//---------------------------------------------------------------------------------------------------------------------

} // end of RecordTeeth class
//=====================================================================================================================
