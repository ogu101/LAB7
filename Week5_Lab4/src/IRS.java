import java.util.Scanner;
//=================================================================================================
public class IRS {
    //----------------------------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);
    private static final double STINKING_RICH = 500000;
    private static final double QUITE_RICH = 200000;
    private static final double MIAMI_POOR = 100000;
    private static final double AVERAGE = 50000;
    private static final double REALISTIC = 20000;

    private static final double HIGH_RATE = 0.25;
    private static final double MED_RATE = 0.10;
    private static final double LOW_RATE = 0.03;
    //----------------------------------------------------------------------------------------------
    public static void main(String[] args){
        // Variables to hold input, income, deductions
        double input;
        double income = 0;
        double deductions = 0;

        // Get input
        System.out.print("Enter next amount: ");
        input = keyboard.nextInt();

        // Compute total income and deductions
        while(input != 0){
            if (input > 0){
                income = income + input;
            } else {
                deductions = deductions + (input *  -1);
            }
            System.out.print("Enter next amount: ");
            input = keyboard.nextInt();
        }

        // Compute taxable income
        double taxable;
        taxable = computeTaxableIncome(income, deductions);

        // Choose tax group
        char taxGroup;
        taxGroup = chooseTaxGroup(taxable);

        // Compute tax owed
        double taxOwed;
        taxOwed = computeTaxOwed(taxable, taxGroup);

        // Display tax information
        displayTaxInformation(income, deductions, taxable, taxGroup, taxOwed);

    } // end of the main method
    //----------------------------------------------------------------------------------------------
    private static double computeTaxableIncome(double income, double deductions){
        // Variable to hold taxable income
        double taxable;

        // Compute taxable income
        if (income >= deductions){
            taxable = income - deductions;
        }
        else{
            taxable = 0.0;
        }

        // Return taxable income
        return taxable;

    } // end of the computeTaxableIncome method
    //----------------------------------------------------------------------------------------------
    private static char chooseTaxGroup(double taxable){
        // Variable to hold tax group
        char taxGroup;

        // Compute tax group
        if(taxable >= STINKING_RICH){
            taxGroup = 'S';
        } else if (taxable >= QUITE_RICH){
            taxGroup = 'Q';
        } else if (taxable >= MIAMI_POOR){
            taxGroup = 'M';
        } else if (taxable >= AVERAGE){
            taxGroup = 'A';
        } else if (taxable >= REALISTIC){
            taxGroup = 'R';
        } else {
            taxGroup = 'P';
        }

        // Return tax group
        return taxGroup;

    } //  end of the chooseTaxGroup method
    //----------------------------------------------------------------------------------------------
    private static double computeTaxOwed(double taxable, char taxGroup){
        // Variable to hold tax owed
        double taxOwed = 0.0;

        //Compute tax owed
        if ((taxGroup == 'S') || (taxGroup == 'Q')){
            taxOwed = taxable * HIGH_RATE;
        } else if (taxGroup == 'M'){
            taxOwed = taxable * MED_RATE;
        } else if ((taxGroup == 'A') || (taxGroup == 'R')){
            taxOwed = taxable * LOW_RATE;
        } else if (taxGroup == 'P'){
            taxOwed = 0.0;
        } else{
            System.out.print("Error!");
        }

        // Return taxOwed
        return taxOwed;

    } // end of the computeTax method
    //----------------------------------------------------------------------------------------------
    private static void displayTaxInformation(double income, double deduction, double taxable, char taxGroup, double taxOwed){
        System.out.println("Income: " + "$" + income);
        System.out.println("Deductions: " + "$" + deduction);
        System.out.println("Taxable income: " + "$" + taxable);
        System.out.println("Tax group: " + "$" + taxGroup);
        System.out.println("Tax owed: " + "$" + taxOwed);

    } // end of the displayTaxInformation method
    //----------------------------------------------------------------------------------------------
} // end of IRS class
//=================================================================================================