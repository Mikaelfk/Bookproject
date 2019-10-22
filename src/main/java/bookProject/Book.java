package bookProject;
public class Book {

    //Fields:
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private int bookYearReleased;
    private int bookPages;
    private String bookEAN;
    private boolean bookLoaned;
    
    //Constructor:
    public Book(String name, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        this.bookTitle = name;
        this.bookAuthor = author;
        this.bookPublisher = publisher;
        this.bookYearReleased = yearReleased;
        this.bookPages = pages;
        this.bookEAN = EAN;
        this.bookLoaned = loaned;
    }
    //Methods:

    public void printBook() {
            System.out.println("Title: "+ this.getBookTitle());
            System.out.println("Author: "+ this.getBookAuthor());
            System.out.println("Publisher: "+ this.getBookPublisher());
            System.out.println("Release year: "+ this.getBookYearReleased());
            System.out.println("Pages: "+ this.getBookPages());
            System.out.println("EAN code: "+ this.getBookEAN());
            if (this.getBookLoaned()){
                System.out.println("The book is currently not available");
            }
            else {
                System.out.println("The book is currently available");
            }
            System.out.println("----------------------------------");

    }

    public void printBookSimple() {
        System.out.println("Title: " + this.getBookTitle() + " | Author: " + this.getBookAuthor() + " | Publisher: " + this.getBookPublisher() + " | Year published: " + this.getBookYearReleased());
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public int getBookYearReleased() {
        return bookYearReleased;
    }

    public int getBookPages() {
        return bookPages;
    }

    public String getBookEAN() {
        return bookEAN;
    }

    public boolean getBookLoaned() {
        return bookLoaned;
    }
}