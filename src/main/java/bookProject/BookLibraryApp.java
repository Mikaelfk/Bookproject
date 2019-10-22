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
            scanner = new Scanner(System.in);
            String command = scanner.next().toLowerCase();
            //help function
            if (command.equals("help")) {
                printHelp();
            }
            //Search by title function
            else if(command.equals("searchtitle")) {
                searchTitle();
            }
            //Search by author function
            else if(command.equals("searchauthor")) {
                searchAuthor();
            }
            //Search by EAN function
            else if(command.equals("searchean")) {
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
        System.out.println("help, searchTitle, searchAuthor, searchEAN, add, remove, list, quit");
    }

    //method which searches by title
    public void searchTitle() {
        System.out.println("Type the title of the book you are searching for");
        String book = scanner.next();
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
        String author = scanner.next().toLowerCase();
        if(bookRegister.searchBookAuthor(author).size() == 0) {
            System.out.println("There is no book in the register written by this author");
        }
        else {
            for (int i = 0; i < bookRegister.searchBookAuthor(author).size(); i++) {
                System.out.println("----------------------------------");
                bookRegister.searchBookAuthor(author).get(i).printBook();
            }
        }
    }

    public void searchEAN() {
        System.out.println("Type the EAN number of the book you are searching for");
        String EAN = scanner.next().toLowerCase();
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
        String title = scanner.next();
        System.out.println("Type the author of the book you wish to add");
        String author = scanner.next();
        System.out.println("Type in the publisher of the book you wish to add");
        String publisher = scanner.next();
        System.out.println("Type in the year the book was released");
        int yearReleased = scanner.nextInt();
        System.out.println("Type in the amount of pages in the book");
        int pages = scanner.nextInt();
        System.out.println("Type the EAN number of the book you wish to add");
        String EAN = scanner.next();
        System.out.println("Is the book rented, type 'yes' or 'no'");
        Boolean loaned = false;
        Boolean answered = false;
        while(!answered) {
            String rented = scanner.next().toLowerCase();
            if (rented.equals("yes")) {
                answered = true;
                loaned = true;
            } else if (rented.equals("no")) {
                loaned = false;
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
        String search = scanner.next();
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

    public static void main(String[] args) {
       BookLibraryApp bookLibrary = new BookLibraryApp();
       bookLibrary.init();
    }
}
