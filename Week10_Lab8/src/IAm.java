import java.util.Scanner;
public class IAm {

    private static final Scanner keyboard = new Scanner(System.in);

    public static void main (String[] args){
        String userSentence = "";
        String outputString = "";

        System.out.println("Please enter a sentence, . to end.");

        while (!(userSentence.equals("."))){
            userSentence = keyboard.nextLine();
            if (userSentence.startsWith("I am")){
               outputString += getOutputSentence(userSentence);
            }

        } // end of while loop

        System.out.print("The qualities are " + outputString);

    } //end of main method

    public static String getOutputSentence (String userSentence){
        String outputString = "";
        outputString = outputString + userSentence.substring(5, userSentence.length()) + ", ";

        return outputString;

    } // end of getOutputSentence method
} //end of I Am class
