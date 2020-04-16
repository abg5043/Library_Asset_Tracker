import java.util.Scanner;
import static java.lang.Integer.parseInt;
import java.time.LocalDate;
public class Library {

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

    public static void returnABook(String[] loanedBooks, String[] renters, String[] books, String[] dueDates) {
        var in = new Scanner(System.in);
        System.out.println("");
        String rentedBook = enterOnlyStr("What book will be rented: ", loanedBooks, "Rented book not found. Please Try Again");
        String formattedRentedBook = "";
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

    public static void rentBook(String[] loanedBooks, String[] renters, String[] books, String[] dueDates) {
        var in = new Scanner(System.in);
        System.out.println("");
        String rentedBook = enterOnlyStr("What book will be rented: ", books, "Unknown Book, Please Try Again");

        //check if book is already rented
        int i = 0;
        while (i<books.length) {
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

        //if not, rent it out!
        System.out.print("Who is renting the book: ");
        String whoRents = in.nextLine();
        LocalDate date = LocalDate.now();
        String formattedRentedBook = "";

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

    public static String findRenter(String[] loanedBooks, String[] renters, String[] books, String[] dueDates) {

        for (int i = 0; i < loanedBooks.length; i++) {
            if (loanedBooks[i] != null) {
                System.out.println("");
                System.out.println(books[i]);
                System.out.println("Rented by: " + renters[i]);
                System.out.println("Due on: " + dueDates[i]);
            }
        }
        return null;
    }

    public static void inventory(String[] bookList) {
        System.out.println("");
        for (int i = 0; i < bookList.length; i++) {
            System.out.println((i + 1) + ". " + bookList[i]);
        }
        backToMenu();
    }

    public static int enterOnlyNumInRange(String message, int smallest, int largest) {
        var in = new Scanner(System.in);
        System.out.print(message);
        var strNum = in.nextLine();
        if (!isNum(strNum)) {
            while (!isNum(strNum)) {
                System.out.println("Not a number, please try again");
                System.out.print(message);
                strNum = in.nextLine();
            }
            while (parseInt(strNum) < smallest || parseInt(strNum) > largest) {
                System.out.println("Please only enter numbers between " + smallest + " and " + largest);
                System.out.print(message);
                strNum = in.nextLine();
            }
            return parseInt(strNum);
        } else {
            while (parseInt(strNum) < smallest || parseInt(strNum) > largest) {
                System.out.println("Please only enter numbers between " + smallest + " and " + largest);
                System.out.print(message);
                strNum = in.nextLine();
            }
            return parseInt(strNum);
        }
    }

    public static String enterOnlyStr(String message, String[] options, String error) {
        var in = new Scanner(System.in);
        System.out.print(message);
        String str = in.nextLine();
        boolean isString = false;
        for (String option : options) {
            if(str.equalsIgnoreCase(option)) {
                isString = true;
            }
        }
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
