import java.util.Scanner;
//=================================================================================================

/**
 * Finds prime and fibonacci number
 * @author  Ola Umeh
 */
public class FascinatingNumbers {
    //----------------------------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);
    private static final double ARRAY_SIGN = 10;
    //----------------------------------------------------------------------------------------------

    public static void main (String[] args){
        // Variables to hold input
        int [] numbers = new int[10];
        int numberOfUserNumbers = getUserNumbers(numbers);



        // Receive user input and test if each number in array is Fibonacci or prime

        for (int index = 0; index < numberOfUserNumbers; index++){
            int num = numbers[index];

            boolean isFibonacci = isFibonacci(num);
            boolean isPrime = isPrime(num);

            System.out.print(num + "  is ");
            if(isFibonacci) {
                System.out.print(" Fibonacci ");
            } else {
                System.out.print(" not Fibonacci ");
            }

            if (isPrime) {
                System.out.print(" prime ");
            }
            else {
                System.out.print(" not prime ");
            }


        }


    }  // end of the main method

    /**
     * Gets user numbers
     * @param numberArray takes user input
     * @return returns length of list
     */
    public static int getUserNumbers(int [] numberArray){

        System.out.println("Please enter numbers (0 to stop) :");

        int numberCount = 0;
        int numbers;

        do {
            numbers = keyboard.nextInt();
            if (numbers > 0){
                numberArray[numberCount] = numbers;
                numberCount++;
            }

        } while(numbers != 0 && (numberCount < 10));
        return numberCount;

    } // end of createArray method

    /**
     * Gets prime numbers and returns boolean
     * @param number takes user input
     * @return returns boolean value
     */
    public static boolean isPrime(int number){
        long divisor = 2;

        while (divisor <= Math.sqrt(number)){
            if ((number % divisor) == 0){
                return(false);
            }
            divisor ++;
        }
        return(true);
    } // end of isPrime method

    /**
     * Gets isFibonacci
     * @param number takes user input
     * @return returns boolean
     */
    public static boolean isFibonacci(int number){
        int next = 0;
        int curr = 0;
        int previous = 1;

        while (next <= number){
            if (next == number){
                return(true);
            }
            else{
                next = curr + previous;
                previous = curr;
                curr =  next;

            }
        }
        return(false);

    } // end of isFibonacci method




} // end of public class
