import java.util.Scanner;
public class Kindle {
    // Variable to hold number of pages and current page number
    private int numberOfPages;
    private int currPageNumber;


    public Kindle (){
        numberOfPages = 0;
        currPageNumber = 1;

    }// end of default constructor

    public Kindle (int theNumberOfPages){
        numberOfPages = theNumberOfPages;
        currPageNumber = 1;

    } // end of 1P constructor

    public String toString (){

        return ("Page " + currPageNumber + " of " + numberOfPages);

    } // end of toString method

    public void turnPages (int pagesRead){
        if (currPageNumber + pagesRead <= numberOfPages){
            currPageNumber += pagesRead;
        }
        else {
            System.out.println("Turning  " + pagesRead + " would take you past the last page.");
            currPageNumber = numberOfPages;
            System.out.println("You are now on       :" + toString());
        }

    } // end of turnPages 1P  method

    public void turnPages (){
        if (currPageNumber + 1 <= numberOfPages){
            currPageNumber += 1;
        }
        else {
            System.out.println("Turning 1 more page would take you past the last page.");
            currPageNumber = numberOfPages;
            System.out.println("You are now on       :" + toString());
        }

    } // end of turnPages 0P  method



}
