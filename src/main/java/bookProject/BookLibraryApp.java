import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A class which allows user interaction with the registry.
 * @author Mikael Falkenberg Krog
 */
public class BookLibraryApp {

    private BookRegister bookRegister;
    private Scanner scanner;

    /**
     * Method which initialises the application
     */
    private void init() {
        bookRegister = new BookRegister();
        bookRegister.addTestBooks();
        System.out.println("Welcome to this book register.");
        System.out.println("You can search for books, add books and remove books");
        System.out.println("Type 'help' for list of commands");
        boolean finished = false;
        while (!finished) {
            System.out.print("> ");
            scanner = new Scanner(System.in);
            //removes all spaces in the command
            String command = scanner.nextLine().toLowerCase();
            command = command.replaceAll("\\s", "");

            switch (command) {
                case "help":
                    printHelp();
                    break;
                case "searchtitle":
                    searchTitle();
                    break;
                case "searchauthor":
                    searchAuthor();
                    break;
                case "searchean":
                    searchEAN();
                    break;
                case "add":
                    addBook();
                    break;
                case "remove":
                    removeBook();
                    break;
                case "listdetailed":
                    listAllBooks();
                    break;
                case "list":
                    listAllBooksSimple();
                    break;
                case "quit":
                    System.out.println("Exiting application");
                    finished = true;
                    break;
                default:
                    System.out.println("This command does not exist");
            }
        }
    }

    /**
     * Method which prints out available commands.
     */

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("Help, Search Title, Search Author, Search EAN, Add, Remove, List, List Detailed, Quit");
    }

    /**
     * Method which searches by title.
     */

    private void searchTitle() {
        System.out.println("Type the title of the book you are searching for");
        String book = scanner.nextLine();
        if (bookRegister.searchBookTitle(book).size() == 0) {
            System.out.println("There is no book in the registry with this title");
        } else {
            System.out.println("----------------------------------");
            for (int i = 0; i < bookRegister.searchBookTitle(book).size(); i++) {
                bookRegister.searchBookTitle(book).get(i).printBook();
            }
        }
    }

    /**
     * Method which searches by author.
     */

    private void searchAuthor() {
        System.out.println("Type the author of the book or books you are searching for");
        String author = scanner.nextLine().toLowerCase();
        if (bookRegister.searchBookAuthor(author).size() == 0) {
            System.out.println("There is no book in the registry written by this author");
        } else {
            System.out.println("----------------------------------");
            for (int i = 0; i < bookRegister.searchBookAuthor(author).size(); i++) {
                bookRegister.searchBookAuthor(author).get(i).printBook();
            }
        }
    }

    /**
     * Method which searches by EAN number.
     */

    private void searchEAN() {
        System.out.println("Type the EAN number of the book you are searching for");
        String EAN = scanner.nextLine().toLowerCase();
        if (bookRegister.searchBookEAN(EAN) == null) {
            System.out.println("There is no book in the registry with this EAN number");
        } else {
            System.out.println("----------------------------------");
            bookRegister.searchBookEAN(EAN).printBook();
        }
    }

    /**
     * Method which adds a book.
     */

    private void addBook() {
        System.out.println("Type the title of the book you wish to add");
        String title = scanner.nextLine();

        System.out.println("Type the author of the book you wish to add");
        String author = scanner.nextLine();

        System.out.println("Type in the publisher of the book you wish to add");
        String publisher = scanner.nextLine();

        System.out.println("Type in the year the book was Published");
        int yearReleased = 0;
        boolean done = false;
        while(!done) {
            yearReleased = checkInt();
            if (yearReleased > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println("The book cannot be published in the future");
                System.out.println("Please type in a valid year");
            }
            else {
                done = true;
            }
        }

        System.out.println("Type in the amount of pages in the book");
        int pages = 0;
        done = false;
        while(!done) {
            pages = checkInt();
            if (pages < 3) {
                System.out.println("The book must contain more than 2 pages");
                System.out.println("Please type in amount of pages again");
            }
            else {
                done = true;
            }
        }

        System.out.println("Type the EAN number of the book you wish to add, it must be 13 characters long," +
                " and consist of only numbers");
        String EAN = "";
        done = false;
        while (!done) {
            try {
                EAN = scanner.nextLine();
                Double.parseDouble(EAN);
                if (EAN.length() != 13) {
                    System.out.println("EAN numbers must contain 13 numbers");
                } else if (bookRegister.searchBookEAN(EAN) == null) {
                    done = true;
                } else {
                    System.out.println("A book with this EAN number already exists, please enter a different EAN number");
                }
            }
            catch (Exception e) {
                System.out.println("This is not a valid EAN number");
            }
        }

        System.out.println("Is the book rented, type 'yes' or 'no'");
        boolean loaned = false;
        done = false;
        while (!done) {
            String rented = scanner.nextLine().toLowerCase();
            if (rented.contains("yes")) {
                done = true;
                loaned = true;
            } else if (rented.contains("no")) {
                done = true;
            } else {
                System.out.println("Please type in 'yes' or 'no'");
            }
        }
        System.out.println("The book has been added to the registry");

        bookRegister.addBook(title, author, publisher, yearReleased, pages, EAN, loaned);
    }

    /**
     * Method which removes a book.
     */
    private void removeBook() {
        System.out.println("Type in the title, author, or EAN number of the book you wish to delete");
        String search = scanner.nextLine();
        System.out.println("---------------------------------------------------------------------------------------");
        ArrayList<Book> foundBooks = bookRegister.searchBookPrint(search);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Choose which book you want to delete by typing in its number.");
        int number = scanner.nextInt();
        bookRegister.deleteBook(foundBooks, number);
    }

    /**
     * Method which prints all the books.
     */

    private void listAllBooks() {
        Iterator<Book> bookIterator = bookRegister.getIterator();
        if (!bookIterator.hasNext()) {
            System.out.println("The registry is empty.");
        } else {
            System.out.println("Here is a list of all the books in the bookregistry");
            System.out.println("----------------------------------");
            while (bookIterator.hasNext()) {
                Book book = bookIterator.next();
                book.printBook();
            }
        }
    }

    /**
     * Method which lists all the books in the registry with minimal information.
     */
    private void listAllBooksSimple() {
        Iterator<Book> bookIterator = bookRegister.getIterator();
        if (!bookIterator.hasNext()) {
            System.out.println("The registry is empty.");
        } else {
            System.out.println("Here is a list of all the books in the bookregistry");
            System.out.println("---------------------------------------------------------------------------------------");
            while (bookIterator.hasNext()) {
                Book book = bookIterator.next();
                book.printBookSimple();
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }


    /**
     * Method that checks if input is a valid integer.
     */
    private int checkInt() {
        boolean done = false;
        int input = 0;
        do {
            try {
                scanner = new Scanner(System.in);
                input = Integer.parseInt(scanner.nextLine());
                done = true;
            } catch (Exception e) {
                System.out.println("This is not a valid number, please input a number");
            }
        } while (!done);
        return input;
    }

    public static void main(String[] args) {
        BookLibraryApp bookLibrary = new BookLibraryApp();
        bookLibrary.init();
    }
}
