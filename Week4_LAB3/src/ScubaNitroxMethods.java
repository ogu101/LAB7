import java.util.Scanner;
//=================================================================================================
public class ScubaNitroxMethods {
    //----------------------------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);
    private static final int FEET_PER_ATM_CONSTANT = 33;
    private static final double MAX_O2_PRESSURE = 1.4;
    private static final double CONTINGENCY_O2_PRESSURE = 1.6;
    //----------------------------------------------------------------------------------------------
    public static void main(String[] args){
        //--- Variables to hold dive depth and percentage O2
        int diveDepth;
        int percentOxygen;
        char oxygenGroup;
        boolean exceedsMaxO2Pressure, exceedsContingencyO2Pressure;



        //--- Retrieve values from user
        System.out.println("Enter depth and percentage O2 : ");
        diveDepth = keyboard.nextInt();
        percentOxygen = keyboard.nextInt();

        //Display data
        double oxygenPressure = computePPO2(diveDepth, percentOxygen);
        displayWarnings(oxygenPressure);
    }
    //----------------------------------------------------------------------------------------------
    private static double computePPO2(int diveDepth, int percentOxygen){
        double oxygenPressure;
        double ambientPressure;


        //--- Compute pressure
        ambientPressure = (diveDepth / FEET_PER_ATM_CONSTANT) + 1;
        oxygenPressure = ((double)percentOxygen / 100) * ambientPressure;

        //--- Display pressure
        System.out.println("Ambient pressure : " + ambientPressure);
        System.out.println("O2 pressure : " + oxygenPressure);

        return oxygenPressure;
    }
    private static void displayWarnings(double oxygenPressure){
        //Variables to hold values
        char oxygenGroup;
        boolean exceedsMaxO2Pressure;
        boolean exceedsContingencyO2Pressure;

        //--- Compute warnings
        oxygenGroup = (char) ((int) (oxygenPressure * 10) + (int)'A');
        exceedsMaxO2Pressure = oxygenPressure > 1.4;
        exceedsContingencyO2Pressure = oxygenPressure > 1.6;

        //--- Display warnings
        System.out.println("O2 group : " + oxygenGroup);
        System.out.println("Exceeds maximal O2 pressure : " + exceedsMaxO2Pressure);
        System.out.println("Exceeds contingency O2 pressure : " + exceedsContingencyO2Pressure);
    }

}

