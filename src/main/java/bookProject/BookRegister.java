package bookProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

class BookRegister {

    private HashMap<String, Book> bookEANHashMap;

    //Constructor
    BookRegister() {
        bookEANHashMap = new HashMap<>();
    }

    //Adds 4 test books to the library
    void addTestBooks() {
        bookEANHashMap.put("1234567890123", new Book("Title1", "Author1", "Publisher1", 2019, 500, "1234567890123", false));
        bookEANHashMap.put("1234567890124", new Book("Title2", "Author2", "Publisher2", 2019, 500, "1234567890124", false));
        bookEANHashMap.put("1234567890125", new Book("Title3", "Author3", "Publisher3", 2019, 500, "1234567890125", false));
        bookEANHashMap.put("1234567890126", new Book("Title4", "Author4", "Publisher4", 2019, 500, "1234567890126", false));
    }

    //method which adds a book to the library
    void addBook(String title, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        Book book = new Book(title, author, publisher, yearReleased, pages, EAN, loaned);
        bookEANHashMap.put(EAN, book);
    }

    ArrayList<Book> searchBookTitle(String searchBook) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        Iterator<Book> bookIterator = this.getIterator();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if (book.getBookTitle().toLowerCase().equals(searchBook.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    ArrayList<Book> searchBookAuthor(String searchAuthor) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        Iterator<Book> bookIterator = this.getIterator();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if (book.getBookAuthor().toLowerCase().equals(searchAuthor.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;

    }

    Book searchBookEAN(String ean) {
        for (Map.Entry<String, Book> stringBookEntry : bookEANHashMap.entrySet()) {
            if (stringBookEntry.getKey().equals(ean)) {
                return stringBookEntry.getValue();
            }
        }
        return null;
    }

    //Method to delete a book
    void deleteBook(String deleteBook) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        Iterator<Book> bookIterator = this.getIterator();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if(book.getBookTitle().toLowerCase().equals(deleteBook.toLowerCase())
                    || book.getBookAuthor().toLowerCase().equals(deleteBook.toLowerCase())
                    || book.getBookEAN().equals(deleteBook)) {
                found = true;
                book.printBook();
                System.out.println("Type 'yes' if this is the book you wish to remove, type 'no' if you do not wish to remove this book");
                String ans = sc.next().toLowerCase();
                if (ans.equals("yes")) {
                    System.out.println("You have deleted " + book.getBookTitle() + " From the bookregistry");
                    bookEANHashMap.remove(book.getBookEAN());
                } else {
                    System.out.println("You have not deleted the book");
                }
            }
        }
        if (!found) {
            System.out.println("This book does not exist");
        }
    }


    Iterator<Book> getIterator() {
        return this.bookEANHashMap.values().iterator();
    }
}