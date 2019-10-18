package bookProject;

import java.util.Iterator;
import java.util.Scanner;

public class BookLibraryApp {

    private BookRegister bookRegister;
    private Scanner scanner;

    public void init() {
        bookRegister = new BookRegister();
        bookRegister.addTestBooks();
        scanner = new Scanner(System.in);
        System.out.println("Welcome to this book register.");
        System.out.println("You can search for books, add books and remove books");

    }

    public void listAllBooks() {
        Iterator<Book> bookIterator = bookRegister.getIterator();
        while(bookIterator.hasNext()) {
            bookIterator.next().printBook();
        }
    }

    public void printHelp() {
        System.out.println("Available commands:");
        System.out.println("help, searchTitle, searchAuthor, searchEAN, add, remove, list, quit");
    }

    public static void main(String[] args) {
       BookLibraryApp bookLibrary = new BookLibraryApp();
       bookLibrary.init();
    }
}
