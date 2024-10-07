import java.util.Scanner;
//=================================================================================================
public class ScubaNitrox {
    //----------------------------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);

    private static final int FEET_PER_ATM_CONSTANT = 33;

    private static final double MAX_O2_PRESSURE = 1.4;

    private static final double CONTINGENCY_O2_PRESSURE = 1.6;

    public static void main(String[] args){
        //--- Variables to hold dive depth and percentage O2
        int diveDepth;
        int percentOxygen;
        double ambientPressure;
        double oxygenPressure;
        char oxygenGroup;
        boolean exceedsMaxO2Pressure, exceedsContingencyO2Pressure;


        //--- Retrieve values from user
        System.out.println("Enter depth and percentage O2 : ");
        diveDepth = keyboard.nextInt();
        percentOxygen = keyboard.nextInt();

        //--- Perform calculations
        ambientPressure = (diveDepth / FEET_PER_ATM_CONSTANT) + 1;
        oxygenPressure = ((double)percentOxygen / 100) * ambientPressure;
        oxygenGroup = (char) ((int) (oxygenPressure * 10) + (int)'A');

        exceedsMaxO2Pressure = oxygenPressure > MAX_O2_PRESSURE;
        exceedsContingencyO2Pressure = oxygenPressure > CONTINGENCY_O2_PRESSURE;

        //--- Display results
        System.out.println("Ambient pressure : " + ambientPressure);
        System.out.println("O2 pressure : " + oxygenPressure);
        System.out.println("O2 group : " + oxygenGroup);
        System.out.println("Exceeds maximal O2 pressure : " + exceedsMaxO2Pressure);
        System.out.println("Exceeds contingency O2 pressure : " + exceedsContingencyO2Pressure);




    }

}
