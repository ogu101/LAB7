import java.util.Scanner;
//=============================================================================
public class BuggyProgram {
    //-----------------------------------------------------------------------------
    private static final Scanner keyboard = new Scanner(System.in);
    //----The gas constant iS 8.3143
    private static final double GAS_CONSTANT = 8.3143;

    //-----------------------------------------------------------------------------
    public static void main(String[] args) {

//----Variables to hold volume, moles, temperature, and pressure
        double volume;
        double moles;
        double temp;
        double pressure;

//----Get data from user
        System.out.print("Enter volume, moles, temperature : ");
        volume = keyboard.nextDouble();
        moles = keyboard.nextDouble();
        temp = keyboard.nextDouble();

//----Calculate pressure
        pressure = moles * GAS_CONSTANT * temp / volume;

//----Print results
        System.out.println("Pressure is " + pressure);
    }
//-----------------------------------------------------------------------------
}
//=============================================================================


