package bookProject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class BookRegister {

    private final ArrayList<Book> bookList;

    //Constructor
    BookRegister(){
        bookList = new ArrayList<>();
    }

    //Adds 4 test books to the library
    void addTestBooks() {
        bookList.add(new Book("Title1", "Author1", "Publisher1", 2019, 500, "1234567890123", false));
        bookList.add(new Book("Title2", "Author1", "Publisher2", 2018, 600, "1234567890124", true));
        bookList.add(new Book("Title3", "Author1", "Publisher3", 2017, 700, "1234567890125", true));
        bookList.add(new Book("Title4", "Author1", "Publisher4", 2016, 800, "1234567890126", false));
    }

    //method which adds a book to the library
    void addBook(String title, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        Book book = new Book(title, author, publisher, yearReleased, pages, EAN, loaned);
        bookList.add(book);
    }

    Book searchBookTitle(String searchBook) {
        Iterator<Book> bookIterator = this.getIterator();
        while(bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if(book.getBookTitle().toLowerCase().equals(searchBook.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    ArrayList<Book> searchBookAuthor(String searchAuthor) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        Iterator<Book> bookIterator = this.getIterator();
        while(bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if (book.getBookAuthor().toLowerCase().equals(searchAuthor.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    Book searchBookEAN(String ean) {
        Iterator<Book> bookIterator = this.getIterator();
        while(bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if(book.getBookEAN().toLowerCase().equals(ean.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    //Method to delete a book
    void deleteBook(String deleteBook) {
        boolean found = false;
        Scanner sc = new Scanner(System.in);
        //for loop that loops through the entire library
        for(int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookTitle().toLowerCase().equals(deleteBook.toLowerCase())
                    || bookList.get(i).getBookAuthor().toLowerCase().equals(deleteBook.toLowerCase())
                    || bookList.get(i).getBookEAN().equals(deleteBook)) {
                bookList.get(i).printBook();
                System.out.println("Type 'yes' if this is the book you wish to remove, type 'no' if you do not wish to remove this book");
                String ans = sc.next().toLowerCase();
                if(ans.contains("yes")) {
                    found = true;
                    System.out.println("You have deleted " + bookList.get(i).getBookTitle() + " From the bookregistry");
                    bookList.remove(i);
                    i--;
                }
                else {
                    System.out.println("You have not deleted the book");
                }
            }
        }
        if(!found) {
            System.out.println("This book does not exist");
        }
    }

    Iterator<Book> getIterator() {
        return this.bookList.iterator();
    }
}