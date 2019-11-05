package bookProject;

class Book {

    //Fields:
    private final String bookTitle;
    private final String bookAuthor;
    private final String bookPublisher;
    private final int bookYearReleased;
    private final int bookPages;
    private final String bookEAN;
    private final boolean bookLoaned;

    //Constructor:
    Book(String name, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        this.bookTitle = name;
        this.bookAuthor = author;
        this.bookPublisher = publisher;
        this.bookYearReleased = yearReleased;
        this.bookPages = pages;
        this.bookEAN = EAN;
        this.bookLoaned = loaned;
    }
    //Methods:

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

    void printBookSimple() {
        System.out.println("Title: " + this.getBookTitle() + " | Author: " + this.getBookAuthor() + " | Publisher: " + this.getBookPublisher() + " | Year published: " + this.getBookYearReleased());
    }

    String getBookTitle() {
        return bookTitle;
    }

    String getBookAuthor() {
        return bookAuthor;
    }

    private String getBookPublisher() {
        return bookPublisher;
    }

    private int getBookYearReleased() {
        return bookYearReleased;
    }

    private int getBookPages() {
        return bookPages;
    }

    String getBookEAN() {
        return bookEAN;
    }

    private boolean getBookLoaned() {
        return bookLoaned;
    }
}