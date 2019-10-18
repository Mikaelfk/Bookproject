package bookProject;

import java.util.Iterator;
import java.util.Scanner;

public class BookLibraryApp {

    private BookRegister bookRegister;


    public void init() {
        Scanner scanner;
        bookRegister = new BookRegister();
        bookRegister.addTestBooks();
        System.out.println("Welcome to this book register.");
        System.out.println("You can search for books, add books and remove books");
        System.out.println("Type help for list of commands");
        boolean finished = false;
        while(!finished) {
            scanner = new Scanner(System.in);
            String command = scanner.next().toLowerCase();
            if (command.equals("help")) {
                printHelp();
            }
            else if(command.equals("searchtitle")) {
                System.out.println("Type the title of the book you are searching for");
                String book = scanner.next();
                if(bookRegister.searchBookTitle(book) == null) {
                    System.out.println("This book does not exist");
                }
                else {
                    bookRegister.searchBookTitle(book).printBook();
                }

            }
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
            else if(command.equals("searchEAN")) {

            }
            else if(command.equals("add")) {

            }
            else if(command.equals("remove")) {

            }
            else if(command.equals("list")) {
                listAllBooks();
            }
            else if(command.equals("quit")) {
                finished = true;
            }
        }
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
