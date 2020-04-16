import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Library {

    public static void main(String[] args) {
        //Establish Scanner
        var in = new Scanner(System.in);

        //Inventory of books
        final String[] books = new String [] {
                "The Musician's Guide to Theory and Analysis",
                "Concise Introduction to Tonal Harmony",
                "The Musician's Guide to Fundamentals",
                "Manual for Ear Training and Sight Singing",
                "A Geometry of Music",
                "Audacious Euphony",
                "In the Process of Becoming",
                "Mahler's Symphonic Sonatas",
                "Contemporary Musicianship: Analysis and the Artist",
                "Pieces of Tradition",
                "Tonality and Transformation",
                "The Cambridge History of Western Music Theory",
                "The Romantic Overture and Musical Form from Rossini to Wagner",
                "Mozart's Music of Friends"
        };
	    System.out.println("Welcome to Dr. Grant's Library App!");
	    System.out.println("");

	    boolean notDone = true; //Tracks if the user is done with the program or not.

	    while (notDone) {
	        System.out.println("Menu:");
            System.out.println("  1) List Inventory");
            System.out.println("  2) List Books on Loan");
            System.out.println("  3) Rent a Book");
            System.out.println("  4) Return a Book");
            System.out.println("  5) Quit");
            System.out.println("");
            var menuChoice = enterOnlyNum("Please make a selection: ");
        }









    }

    public static int enterOnlyNum(String message) {
        var in = new Scanner(System.in);
        System.out.print(message);
        var strNum = in.next();
        if (!isNum(strNum)) {
            while (!isNum(strNum)) {
                System.out.println("Incorrect Input, try again");
                System.out.print(message);
                strNum = in.next();
            }
            return parseInt(strNum);
        } else {
            return parseInt(strNum);
        }
    }

    public static String enterOnlyStr(String message, String option1, String option2) {
        var in = new Scanner(System.in);
        var str = in.nextLine();
        if (!str.equals(option1) && !str.equals(option2)) {
            while (!str.equals(option1) && !str.equals(option2)) {
                System.out.println("Incorrect Input, try again");
                System.out.println(message);
                str = in.nextLine();
            }
        }
        return str;
    }

    public static boolean isNum(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
