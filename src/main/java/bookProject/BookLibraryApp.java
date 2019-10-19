package bookProject;

import java.util.Iterator;
import java.util.Scanner;

public class BookLibraryApp {

    private BookRegister bookRegister;

    public void init() {
        bookRegister = new BookRegister();
        bookRegister.addTestBooks();
        System.out.println("Welcome to this book register.");
        System.out.println("You can search for books, add books and remove books");
        System.out.println("Type 'help' for list of commands");
        boolean finished = false;
        while(!finished) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next().toLowerCase();

            //help function
            if (command.equals("help")) {
                printHelp();
            }
            //Search by title function
            else if(command.equals("searchtitle")) {
                System.out.println("Type the title of the book you are searching for");
                String book = scanner.next();
                if(bookRegister.searchBookTitle(book) == null) {
                    System.out.println("There is no book in the registry with this title");
                }
                else {
                    bookRegister.searchBookTitle(book).printBook();
                }
            }
            //Search by author function
            else if(command.equals("searchauthor")) {
                System.out.println("Type the author of the book or books you are searching for");
                String author = scanner.next().toLowerCase();
                if(bookRegister.searchBookAuthor(author).size() == 0) {
                    System.out.println("There is no book in the register written by this author");
                }
                else {
                    for (int i = 0; i < bookRegister.searchBookAuthor(author).size(); i++) {
                        bookRegister.searchBookAuthor(author).get(i).printBook();
                    }
                }
            }
            //Search by EAN function
            else if(command.equals("searchean")) {
                System.out.println("Type the EAN number of the book you are searching for");
                String EAN = scanner.next().toLowerCase();
                if(bookRegister.searchBookEAN(EAN) == null) {
                    System.out.println("There is no book in the registry with this EAN number");
                }
                else {
                    bookRegister.searchBookEAN(EAN).printBook();
                }
            }
            //function to add a book
            else if(command.equals("add")) {
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
                Boolean loaned = false;
                bookRegister.addBook(title, author, publisher, yearReleased, pages, EAN, loaned);
            }
            //function to remove a book
            else if(command.equals("remove")) {
                System.out.println("Type in the title, author, or EAN number of the book you wish to delete");
                String search = scanner.next();
                bookRegister.deleteBook(search);
            }
            //function to list all books
            else if(command.equals("list")) {
                System.out.println("Here is a list of all the books in the bookregistry");
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
    //method which prints all the books
    public void listAllBooks() {
        Iterator<Book> bookIterator = bookRegister.getIterator();
        while(bookIterator.hasNext()) {
            bookIterator.next().printBook();
        }
    }
    //method which prints out available commands
    public void printHelp() {
        System.out.println("Available commands:");
        System.out.println("help, searchTitle, searchAuthor, searchEAN, add, remove, list, quit");
    }

    public static void main(String[] args) {
       BookLibraryApp bookLibrary = new BookLibraryApp();
       bookLibrary.init();
    }
}
