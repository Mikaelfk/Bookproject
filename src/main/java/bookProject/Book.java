/**
 * A class for books.
 * @author Mikael Falkenberg Krog
 */
public class Book {

    private final String bookTitle;
    private final String bookAuthor;
    private final String bookPublisher;
    private final int bookYearReleased;
    private final int bookPages;
    private final String bookEAN;
    private final boolean bookLoaned;

    /**
     * Constructor for the book class.
     * @param name Title of book
     * @param author Author of book
     * @param publisher Publisher of book
     * @param yearReleased Year the book was released
     * @param pages Pages in book
     * @param EAN The book's EAN number
     * @param loaned The status of the book
     */
    Book(String name, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        this.bookTitle = name;
        this.bookAuthor = author;
        this.bookPublisher = publisher;
        this.bookYearReleased = yearReleased;
        this.bookPages = pages;
        this.bookEAN = EAN;
        this.bookLoaned = loaned;
    }

    /**
     * A method which prints the information of the book.
     */
    void printBook() {
        System.out.println("Title: " + this.getBookTitle());
        System.out.println("Author: " + this.getBookAuthor());
        System.out.println("Publisher: " + this.getBookPublisher());
        System.out.println("Release year: " + this.getBookYearReleased());
        System.out.println("Pages: " + this.getBookPages());
        System.out.println("EAN code: " + this.getBookEAN());
        if (this.getBookLoaned()) {
            System.out.println("The book is currently not available");
        } else {
            System.out.println("The book is currently available");
        }
        System.out.println("----------------------------------");

    }

    /**
     * Method which prints the book with minimal information.
     */
    void printBookSimple() {
        System.out.println("Title: " + this.getBookTitle() + " | Author: " + this.getBookAuthor() +
                 " | EAN number: " + this.getBookEAN() + " | Rented: " + this.getBookLoaned());
    }

    /**
     * Method which gets the title of a book.
     * @return Title
     */
    String getBookTitle() {
        return bookTitle;
    }

    /**
     * Method which gets the author of a book.
     * @return Author
     */
    String getBookAuthor() {
        return bookAuthor;
    }

    /**
     * Method which gets the publisher of a book.
     * @return Publisher
     */
    private String getBookPublisher() {
        return bookPublisher;
    }

    /**
     * Method which gets the release year of a book.
     * @return Year released
     */
    private int getBookYearReleased() {
        return bookYearReleased;
    }

    /**
     * Method which gets the amount of pages in a book.
     * @return Pages
     */
    private int getBookPages() {
        return bookPages;
    }

    /**
     * Method which gets the EAN number of a book.
     * @return EAN number as string.
     */
    String getBookEAN() {
        return bookEAN;
    }

    /**
     * Method which gets the status of the book
     * @return Rented as boolean value
     */
    private boolean getBookLoaned() {
        return bookLoaned;
    }
}