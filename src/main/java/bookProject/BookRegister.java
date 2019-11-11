import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

/**
 * A class for the registry.
 *
 * @author Mikael Falkenberg Krog
 */
public class BookRegister {
    /**
     * The HashMap which contains all the books
     */
    private final HashMap<String, Book> bookEANHashMap;

    BookRegister() {
        bookEANHashMap = new HashMap<>();
    }

    /**
     * Method which adds 4 test books.
     */
    void addTestBooks() {
        bookEANHashMap.put("1234567890123", new Book("Title1", "Author1", "Publisher1",
                2019, 500, "1234567890123", false));
        bookEANHashMap.put("1234567890124", new Book("Title1", "Author2", "Publisher2",
                2019, 500, "1234567890124", false));
        bookEANHashMap.put("1234567890125", new Book("Title1", "Author3", "Publisher3",
                2019, 500, "1234567890125", false));
        bookEANHashMap.put("1234567890126", new Book("Title1", "Author4", "Publisher4",
                2019, 500, "1234567890126", false));
    }


    /**
     * Method which adds a book
     *
     * @param title        Book title
     * @param author       Book author
     * @param publisher    Book publisher
     * @param yearReleased Year book was published
     * @param pages        Pages in book
     * @param EAN          EAN number of book
     * @param loaned       Status of book.
     */
    void addBook(String title, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        Book book = new Book(title, author, publisher, yearReleased, pages, EAN, loaned);
        bookEANHashMap.put(EAN, book);
    }

    /**
     * Method which searches based on the title of a book.
     *
     * @param searchBook Title of book the user wishes to search for
     * @return All the books with the title in an ArrayList
     */
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

    /**
     * Method which searches based on the author of a book.
     *
     * @param searchAuthor Author of the book the user wishes to search for
     * @return All the books with the author in an ArrayList.
     */
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

    /**
     * Method which returns the book with a certain EAN number.
     *
     * @param ean EAN number of the book the user wishes to search for
     * @return The book with the given EAN number.
     */
    Book searchBookEAN(String ean) {
        return bookEANHashMap.get(ean);
    }

    /**
     * This method returns an ArrayList, and also prints it. This is used for the delete function
     *
     * @param searchBook The input the user wanted to search for
     * @return An ArrayList with all the matching books
     */
    ArrayList<Book> searchBookPrint(String searchBook) {
        Iterator<Book> bookIterator = this.getIterator();
        ArrayList<Book> foundBooks = new ArrayList<>();
        while (bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if (book.getBookTitle().toLowerCase().equals(searchBook.toLowerCase())
                    || book.getBookAuthor().toLowerCase().equals(searchBook.toLowerCase())
                    || book.getBookEAN().equals(searchBook)) {
                foundBooks.add(book);
            }
        }
        for (int i = 0; i < foundBooks.size(); i++) {
            System.out.print(i + 1 + ": ");
            foundBooks.get(i).printBookSimple();
        }
        return foundBooks;
    }

    /**
     * Method used to delete a book from the registry.
     *
     * @param array  The ArrayList found by the method searchBookPrint
     * @param number The number of the book the user wants to delete
     */
    void deleteBook(ArrayList<Book> array, int number) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < array.size(); i++) {
            if (i == (number - 1)) {
                System.out.println("----------------------------------");
                array.get(i).printBook();
                System.out.println("Is this the book you want to remove? Type 'yes' if you wish to remove this book. " +
                        "Type 'no' if you don't wish to remove this book");
                String command = sc.nextLine().toLowerCase().trim();
                if (command.equals("yes")) {
                    bookEANHashMap.remove(array.get(i).getBookEAN());
                    System.out.println("The book has been removed from the registry");
                } else {
                    System.out.println("The book has not been removed");
                }
            }
        }
        if(number > array.size() || number < 1){
            System.out.println("No book has been deleted");
        }
    }

    /**
     * Method which gets an iterator.
     *
     * @return An iterator for the HashMap values of bookEANHashMap.
     */
    Iterator<Book> getIterator() {
        return this.bookEANHashMap.values().iterator();
    }
}