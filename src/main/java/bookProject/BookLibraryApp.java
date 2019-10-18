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

            //help function
            if (command.equals("help")) {
                printHelp();
            }
            //Search by title function
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
            else if(command.equals("searchEAN")) {
                System.out.println("Type the EAN number of the book you are searching for");
                String EAN = scanner.next().toLowerCase();

            }
            //function to add a book
            else if(command.equals("add")) {

            }
            //function to remove a book
            else if(command.equals("remove")) {

            }
            //function to list all books
            else if(command.equals("list")) {
                listAllBooks();
            }
            //function to quit the program
            else if(command.equals("quit")) {
                finished = true;
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
