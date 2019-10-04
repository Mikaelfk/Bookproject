package bookProject;
import java.util.ArrayList;
import java.util.Iterator;

public class BookRegister {

    private ArrayList<Book> bookList;

    //Constructor
    public BookRegister(){
        bookList = new ArrayList<>();
    }

    public void addTestBooks() {
        bookList.add(new Book("Name1", "Author1", "Publisher1", 2019, 500, "1234567890123", false));
        bookList.add(new Book("Name2", "Author1", "Publisher2", 2018, 600, "1234567890124", true));
        bookList.add(new Book("Name3", "Author3", "Publisher3", 2017, 700, "1234567890125", true));
        bookList.add(new Book("Name4", "Author4", "Publisher4", 2016, 800, "1234567890126", false));
    }

    public void addBook(String title, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        bookList.add(new Book(title, author, publisher, yearReleased, pages, EAN, loaned));
    }

    public void printBooks() {
        for(Book book : bookList) {
            book.printBook();
        }
    }
    public void listAllBooks2() {
        Iterator<Book> bookIterator = this.getIterator();
        while(bookIterator.hasNext()) {
            bookIterator.next().printBook();
        }
    }

    public void searchBook(String searchBook) {
        boolean found = false;
        for(Book book : bookList) {
            if (book.getBookTitle().toLowerCase().contains(searchBook.toLowerCase())
                    || book.getBookAuthor().toLowerCase().contains(searchBook.toLowerCase())
                    || book.getBookEAN().contains(searchBook)) {
                found = true;
                book.printBook();
            }
        }
        if(!found) {
            System.out.println("This book does not exist");
        }
    }

    public Book searchBookTitle(String searchBook) {
        Iterator<Book> bookIterator = this.getIterator();
        while(bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if(book.getBookTitle().toLowerCase().contains(searchBook.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    public ArrayList<Book> searchBookAuthor(String searchAuthor) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        Iterator<Book> bookIterator = this.getIterator();
        while(bookIterator.hasNext()) {
            Book book = bookIterator.next();
            if (book.getBookAuthor().toLowerCase().contains(searchAuthor.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public void deleteBook(String deleteBook) {
        boolean found = false;
        for(int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookTitle().toLowerCase().equals(deleteBook.toLowerCase())
                    || bookList.get(i).getBookAuthor().toLowerCase().equals(deleteBook.toLowerCase())
                    || bookList.get(i).getBookEAN().equals(deleteBook)) {
                found = true;
                bookList.remove(i);
            }
        }
        if(!found) {
            System.out.println("This book does not exist");
        }
    }

    public Iterator<Book> getIterator() {
        return this.bookList.iterator();
    }
}