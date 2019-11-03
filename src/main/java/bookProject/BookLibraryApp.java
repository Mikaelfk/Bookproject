package bookProject;

import java.util.Iterator;
import java.util.Scanner;

class BookLibraryApp {

    private BookRegister bookRegister;
    private Scanner scanner;

    private void init() {
        bookRegister = new BookRegister();
        bookRegister.addTestBooks();
        System.out.println("Welcome to this book register.");
        System.out.println("You can search for books, add books and remove books");
        System.out.println("Type 'help' for list of commands");
        boolean finished = false;
        while(!finished) {
            System.out.println("Type a command");
            System.out.print("> ");
            scanner = new Scanner(System.in);
            String command = scanner.nextLine().toLowerCase();

            //removes all spaces in the command
            command = command.replaceAll("\\s","");

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
                case "list":
                    listAllBooks();
                    break;
                case "listsimple":
                    listAllBooksSimple();
                    break;
                case "quit":
                    finished = true;
                    break;
                default:
                    System.out.println("This command does not exist");
            }
        }
    }

    //method which prints out available commands
    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("help, Search Title, Search Author, Search EAN, add, remove, list, list simple, quit");
    }

    //method which searches by title
    private void searchTitle() {
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
    private void searchAuthor() {
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
    //method which searches by EAN number
    private void searchEAN() {
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
    private void addBook() {
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
        boolean loaned = false;
        boolean answered = false;
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
    private void removeBook() {
        System.out.println("Type in the title, author, or EAN number of the book you wish to delete");
        String search = scanner.nextLine();
        System.out.println("----------------------------------");
        bookRegister.deleteBook(search);
    }

    //method which prints all the books
    private void listAllBooks() {
        Iterator<Book> bookIterator = bookRegister.getIterator();
        if(!bookIterator.hasNext()) {
            System.out.println("The registry is empty.");
        }
        else {
            System.out.println("Here is a list of all the books in the bookregistry");
            System.out.println("----------------------------------");
            while (bookIterator.hasNext()) {
                bookIterator.next().printBook();
            }
        }
    }

    private void listAllBooksSimple() {
        Iterator<Book> bookIterator = bookRegister.getIterator();
        if(!bookIterator.hasNext()) {
            System.out.println("The registry is empty.");
        }
        else {
            System.out.println("Here is a list of all the books in the bookregistry");
            int index = 0;
            while (bookIterator.hasNext()) {
                index++;
                System.out.print(index + ": ");
                bookIterator.next().printBookSimple();
            }
        }
    }

    //method that checks if input is a valid integer.
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
        } while(!done);
        return input;
    }

    public static void main(String[] args) {
       BookLibraryApp bookLibrary = new BookLibraryApp();
       bookLibrary.init();
    }
}
