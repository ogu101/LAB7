import java.util.Scanner ;
//=================================================================================================
public class KillingKangaroos {
    //----------------------------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);
    //--- The roadkill constant is 1.47
    private static final double ROADKILL_CONSTANT = 1.47;

    private static final double ROAD_WIDTH = 0.01;

    public static void main(String[] args){
        //--- Variables to hold square side length, road length, and # of kangaroos
        double squareSide;
        double roadLength;
        int kangaroosCount;

        //--- Retrieve values from user
        System.out.print("Enter side of square in km : ");
        squareSide = keyboard.nextDouble();

        System.out.print("Enter roads length in km : ");
        roadLength = keyboard.nextDouble();

        System.out.print("Enter number of 'roos : ");
        kangaroosCount = keyboard.nextInt();

        //--- Perform calculation
        double kangarooDensity = kangaroosCount / (squareSide * squareSide);
        double roadSurfaceArea = roadLength * ROAD_WIDTH;
        double numberKangarooInjuriesAndKills = kangarooDensity * roadSurfaceArea * ROADKILL_CONSTANT;

        int kangarooKills = (int) numberKangarooInjuriesAndKills;
        double kangarooInjuries = (numberKangarooInjuriesAndKills % kangarooKills) * 10;

        //--- Display results
        System.out.println("Expected number of kills is : " + kangarooKills );
        System.out.println("Expected number of injuries is : " + (int) kangarooInjuries);

    } // end of the main method
//----------------------------------------------------------------------------------------------
}
// end of the class
//=================================================================================================