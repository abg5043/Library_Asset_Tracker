import java.util.Scanner;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;

/** This class keeps track of books {@code books} on loan in a given library.
 */

public class Library {

    /** The following method contains the main menu features of the class. It also contains the current library books.
     * Later features of this method could include additional menu options to add and subtract books from the library
     * if a user had a given password.
     *
     * @input the user interacts with the menu through standard input. Users need only enter integers.
     *
     */

    public static void main(String[] args) {
        //Create arrays to track books in library, if they are loaned, who rented, and dueDates
        final String[] books = new String[] {
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
        String[] loanedBooks = new String[books.length];
        String[] renters = new String[books.length];
        String[] dueDates = new String[books.length];

	    System.out.println("Welcome to Dr. Grant's Library App!\n");

	    boolean notDone = true; //Tracks if the user is done with the program or not.

	    while (notDone) {
	        System.out.println("Menu:");
            System.out.println("  1) List Inventory");
            System.out.println("  2) List Books on Loan");
            System.out.println("  3) Rent a Book");
            System.out.println("  4) Return a Book");
            System.out.println("  5) Quit\n");

            var menuChoice = enterOnlyNumInRange("Please make a selection: ", 1, 5);

            switch(menuChoice) {
                case 1:
                    inventory(books);
                    break;
                case 2:
                    bookOnLoan(loanedBooks, renters, books, dueDates);
                    break;
                case 3:
                    rentBook(loanedBooks, renters, books, dueDates);
                    break;
                case 4:
                    returnABook(loanedBooks, renters, books, dueDates);
                    break;
                case 5:
                    System.out.println("\nThanks for using the library!");
                    System.exit(0);
            }
        }
    }

    /** The following method returns a book to the library and removes the loaned out book information from the
     * loaned book array {@code loanedBooks}, the renters array {@code renters}, and the due date array {@code dueDates}.
     *
     * @param loanedBooks = an array of all books checked out from the library
     * @param renters = an array of all people renting books
     * @param books = an array of all books the library owns
     * @param dueDates = an array of all due dates for all rented books
     * @input the user enters the book through standard input. Books not in rented array will not be accepted.
     */

    public static void returnABook(String[] loanedBooks, String[] renters, String[] books, String[] dueDates) {
        var in = new Scanner(System.in);
        System.out.println("");

        //User inputs book; program checks if it is a loaned out book or not
        String rentedBook = enterOnlyStr("What book will be rented: ", loanedBooks, "Rented book not found. Please Try Again");
        String formattedRentedBook = "";

        //remove loaned book info from associated arrays including due dates, renter, and loaned book
        for (int i = 0; i<books.length; i++) {
            if (books[i].equalsIgnoreCase(rentedBook)) {
                formattedRentedBook = books[i];
                loanedBooks[i] = null;
                renters[i] = null;
                dueDates[i] = null;
            }
        }
        System.out.println("\nGreat! \"" + formattedRentedBook + "\" has been successfully returned.");
        backToMenu();
    }

    /** The following method rents a book from the library and adds the loaned out book information to the
     * loaned book array {@code loanedBooks}, the renters array {@code renters}, and the due date array {@code dueDates}.
     *
     * @param loanedBooks = an array of all books checked out from the library
     * @param renters = an array of all people renting books
     * @param books = an array of all books the library owns
     * @param dueDates = an array of all due dates for all rented books
     * @input the user enters the book through standard input. Books not in library will not be accepted. Neither will
     * books that are already rented.
     */

    public static void rentBook(String[] loanedBooks, String[] renters, String[] books, String[] dueDates) {
        var in = new Scanner(System.in);
        System.out.println("");

        //User inputs book; program checks if it is in the library or not
        String rentedBook = enterOnlyStr("What book will be rented: ", books, "Unknown Book, Please Try Again");
        int i = 0;
        while (i<books.length) {

        //check if book is already rented
            if (rentedBook.equalsIgnoreCase(loanedBooks[i])) {
                System.out.println("");
                var finished = enterOnlyStr("Sorry, that book is rented until " + dueDates[i] + ".\nWould you like to try again? (y/n) ",
                        new String[] {"y", "n"},"Please only enter y or n");
                if (finished.equalsIgnoreCase("n")) {
                    System.out.println("");
                    return;
                } else {
                    System.out.println("");
                    rentedBook = enterOnlyStr("What book will be rented: ", books, "Unknown Book, Please Try Again");
                    i = 0;
                }
            }
            i++;
        }

        //if not already rented, then rent it out!
        System.out.print("Who is renting the book: ");
        String whoRents = in.nextLine();
        LocalDate date = LocalDate.now();
        String formattedRentedBook = "";

        //adds rented info to the associated arrays
        for (i = 0; i<books.length; i++) {
            if (books[i].equalsIgnoreCase(rentedBook)) {
                formattedRentedBook = books[i];
                loanedBooks[i] = rentedBook;
                renters[i] = whoRents;
                dueDates[i] = String.valueOf(date.plusDays(7));
            }
        }
        System.out.println("\nGreat! " + whoRents + " has rented \"" + formattedRentedBook + "\"\nuntil " + String.valueOf(date.plusDays(7)) + ".");
        backToMenu();
    }

    /** The following method checks if an array has any values or is just filled will null.
     *
     * @param array = any string array
     * @return = a boolean true or false
     */
    public static boolean isEmpty(String[] array) {
        boolean empty = true;
        for (int i=0; i<array.length; i++) {
            if (array[i] != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }


    /** The following method checks to see if any books are on loan and prints out any books that currently are along with
     * the information about who is renting the book and when the book is due.
     *
     * @param loanedBooks = an array of all books checked out from the library
     * @param renters = an array of all people renting books
     * @param books = an array of all books the library owns
     * @param dueDates = an array of all due dates for all rented books
     */

    public static void bookOnLoan(String[] loanedBooks, String[] renters, String[] books, String[] dueDates) {

        //check if array has loaned books
        if (isEmpty(loanedBooks)) {
            System.out.println("\nNo books are on loan.");
            backToMenu();
        //if not empty, print who has the books
        } else {
            findRenter(loanedBooks, renters, books, dueDates);
            backToMenu();
        }
    }

    /** The following method finds and prints out any books that are currently being rented along with the info about who
     * is renting the book and when it is due.
     *
     * @param loanedBooks = an array of all books checked out from the library
     * @param renters = an array of all people renting books
     * @param books = an array of all books the library owns
     * @param dueDates = an array of all due dates for all rented books
     */

    public static void findRenter(String[] loanedBooks, String[] renters, String[] books, String[] dueDates) {

        for (int i = 0; i < loanedBooks.length; i++) {
            if (loanedBooks[i] != null) {
                System.out.println("");
                System.out.println(books[i]);
                System.out.println("Rented by: " + renters[i]);
                System.out.println("Due on: " + dueDates[i]);
            }
        }
    }

    /** The following method finds and prints what books are currently owned by the library.
     *
     * @param bookList = an array of all books the library owns.
     */

    public static void inventory(String[] bookList) {
        System.out.println("");
        for (int i = 0; i < bookList.length; i++) {
            System.out.println((i + 1) + ". " + bookList[i]);
        }
        backToMenu();
    }

    /** The following method checks if a number is a number and if it falls between two numbers of a given range.
     *
     * @param message = a message printed to the user requesting a specific type of number
     * @param smallest = the smallest number possible to be inputted by the user
     * @param largest = the largest number possible to be inputted by the user
     *
     * @input input an int from standard input. Only integers that fall between the range will be accepted
     * @return a valid integer
     */

    public static int enterOnlyNumInRange(String message, int smallest, int largest) {
        var in = new Scanner(System.in);

        //ask the user for a type of number
        System.out.print(message);
        var strNum = in.nextLine();

        //check if that input really is a number or doesn't fall between the range
        while (!isNum(strNum) || parseInt(strNum) < smallest || parseInt(strNum) > largest) {

            //first check: if it's not a number, keep asking until the user inputs one
            while (!isNum(strNum)) {
                System.out.println("Not a number, please try again");
                System.out.print(message);
                strNum = in.nextLine();
            }

            //second check: is the number within the range?
            if (parseInt(strNum) < smallest || parseInt(strNum) > largest) {
                System.out.println("Please only enter numbers between " + smallest + " and " + largest);
                System.out.print(message);
                strNum = in.nextLine();
            } else {
                return parseInt(strNum);
            }
        }

        //return straight out if it passes both tests
        return parseInt(strNum);
    }


    /** The following method checks if a user-inputted string matches strings from a given string array {@code options}.
     *
     * @param message = a message printed to the user requesting a specific type of string
     * @param options = an array of strings that the user-inputted string must match (regardless of case)
     * @param error = an error message telling the user what to input if they were wrong
     *
     * @input input a string from standard input. Only integers that match {@code options} will be accepted
     * @return a valid string
     */

    public static String enterOnlyStr(String message, String[] options, String error) {
        var in = new Scanner(System.in);

        //ask user for a specific type of string
        System.out.print(message);
        String str = in.nextLine();
        boolean isString = false;

        //check if the string matches any strings in the options array
        for (String option : options) {
            if(str.equalsIgnoreCase(option)) {
                isString = true;
            }
        }

        //if not in options, keep asking user for a string that is included in options
        while (!isString) {
            System.out.println(error);
            System.out.print(message);
            str = in.nextLine();
            for (String option : options) {
                if(str.equalsIgnoreCase(option)) {
                    isString = true;
                }
            }
        }

        //return valid string
        return str;
    }


    /** The following method checks if an integer is truly an integer.
     *
     * @param strNum = a test string
     *
     * @return a boolean value
     */

    public static boolean isNum(String strNum) {
        //if it's a null string, it obviously isn't a number
        if (strNum == null) {
            return false;
        }

        //try to call Integer.parseInt
        try {
            int d = Integer.parseInt(strNum);
        //if a number format exception error happens, return false; it's not an integer
        } catch (NumberFormatException nfe) {
            return false;
        }
        //if that error does not happen, return true; it is an integer.
        return true;
    }

    /** The following method checks if the user wants to go back to the main menu.
     *
     * @input = the program checks with standard input to see if the user wants to quit the program or go back to the main menu.
     */

    public static void backToMenu() {
        System.out.println("");
        var finished = enterOnlyStr("Would you like to go back to the menu? (y/n) ", new String[] {"y", "n"},
                "Please only enter y or n");
        if (finished.equalsIgnoreCase("n")) {
            System.out.println("\nThanks for using the library!");
            System.exit(0);
        } else {
            System.out.println("");
        }
    }

}
