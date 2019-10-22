package bookProject;

import java.util.Iterator;
import java.util.Scanner;

public class BookLibraryApp {

    private BookRegister bookRegister;
    private Scanner scanner;

    public void init() {
        bookRegister = new BookRegister();
        bookRegister.addTestBooks();
        System.out.println("Welcome to this book register.");
        System.out.println("You can search for books, add books and remove books");
        System.out.println("Type 'help' for list of commands");
        boolean finished = false;
        while(!finished) {
            System.out.print(">");
            scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase().trim();
            //help function
            if (command.equals("help")) {
                printHelp();
            }
            //Search by title function
            else if(command.equals("search title")) {
                searchTitle();
            }
            //Search by author function
            else if(command.equals("search author")) {
                searchAuthor();
            }
            //Search by EAN function
            else if(command.equals("search ean")) {
                searchEAN();
            }
            //function to add a book
            else if(command.equals("add")) {
                addBook();
            }
            //function to remove a book
            else if(command.equals("remove")) {
                removeBook();
            }
            //function to list all books
            else if(command.equals("list")) {
                listAllBooks();
            }
            else if(command.equals("list simple")) {
                listAllBooksSimple();
            }
            //function to quit the program
            else if(command.equals("quit")) {
                finished = true;
            }
            else {
                System.out.println("This command does not exist");
            }
        }
    }

    //method which prints out available commands
    public void printHelp() {
        System.out.println("Type one of these commands to get started");
        System.out.println("Available commands:");
        System.out.println("help, Search Title, Search Author, Search EAN, add, remove, list, list simple, quit");
    }

    //method which searches by title
    public void searchTitle() {
        System.out.println("Type the title of the book you are searching for");
        String book = scanner.nextLine();
        if(bookRegister.searchBookTitle(book) == null) {
            System.out.println("There is no book in the registry with this title");
        }
        else {
            System.out.println("----------------------------------");
            bookRegister.searchBookTitle(book).printBook();
        }
    }

    //method which searches by author
    public void searchAuthor() {
        System.out.println("Type the author of the book or books you are searching for");
        String author = scanner.nextLine().toLowerCase();
        if(bookRegister.searchBookAuthor(author).size() == 0) {
            System.out.println("There is no book in the register written by this author");
        }
        else {
            System.out.println("----------------------------------");
            for (int i = 0; i < bookRegister.searchBookAuthor(author).size(); i++) {
                bookRegister.searchBookAuthor(author).get(i).printBook();
            }
        }
    }

    public void searchEAN() {
        System.out.println("Type the EAN number of the book you are searching for");
        String EAN = scanner.nextLine().toLowerCase();
        if(bookRegister.searchBookEAN(EAN) == null) {
            System.out.println("There is no book in the registry with this EAN number");
        }
        else {
            System.out.println("----------------------------------");
            bookRegister.searchBookEAN(EAN).printBook();
        }
    }

    //method which adds a book
    public void addBook() {
        System.out.println("Type the title of the book you wish to add");
        String title = scanner.nextLine();
        System.out.println("Type the author of the book you wish to add");
        String author = scanner.nextLine();
        System.out.println("Type in the publisher of the book you wish to add");
        String publisher = scanner.nextLine();
        System.out.println("Type in the year the book was released");
        int yearReleased = checkInt();
        System.out.println("Type in the amount of pages in the book");
        int pages = checkInt();
        System.out.println("Type the EAN number of the book you wish to add");
        String EAN = scanner.nextLine();
        System.out.println("Is the book rented, type 'yes' or 'no'");
        Boolean loaned = false;
        Boolean answered = false;
        while(!answered) {
            String rented = scanner.nextLine().toLowerCase();
            if (rented.equals("yes")) {
                answered = true;
                loaned = true;
            } else if (rented.equals("no")) {
                answered = true;
            } else {
                System.out.println("Please type in 'yes' or 'no'");
            }
        }
        System.out.println("The book has been added to the registry");
        bookRegister.addBook(title, author, publisher, yearReleased, pages, EAN, loaned);
    }

    //method which removes a book
    public void removeBook() {
        System.out.println("Type in the title, author, or EAN number of the book you wish to delete");
        String search = scanner.nextLine();
        System.out.println("----------------------------------");
        bookRegister.deleteBook(search);
    }

    //method which prints all the books
    public void listAllBooks() {
        System.out.println("Here is a list of all the books in the bookregistry");
        System.out.println("----------------------------------");
        Iterator<Book> bookIterator = bookRegister.getIterator();
        while(bookIterator.hasNext()) {
            bookIterator.next().printBook();
        }
    }

    public void listAllBooksSimple() {
        System.out.println("Here is a list of all the books in the bookregistry");
        Iterator<Book> bookIterator = bookRegister.getIterator();
        int index = 0;
        while(bookIterator.hasNext()) {
            index++;
            System.out.print(index + ": ");
            bookIterator.next().printBookSimple();
        }
    }

    public int checkInt() {
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
        } while(!done);
        return input;
    }

    public static void main(String[] args) {
       BookLibraryApp bookLibrary = new BookLibraryApp();
       bookLibrary.init();
    }
}
