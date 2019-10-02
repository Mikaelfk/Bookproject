package bookProject;

import java.util.Iterator;

public class BookLibraryApp {
        BookRegister bookRegister;

    public void init() {
        bookRegister = new BookRegister();
        bookRegister.addTestBooks();
        bookRegister.addBook("A Feast for Crows", "George R. R. Martin", "Voyager Books", 2005, 753, "4989081902332", false);
        bookRegister.searchBookAuthor("author");
    }

    public void listAllBooks() {
        init();
        Iterator<Book> bookIterator = bookRegister.getIterator();
        while(bookIterator.hasNext()) {
            bookIterator.next().printBook();
        }
    }

    public static void main(String[] args) {
       BookLibraryApp bookLibrary = new BookLibraryApp();
       bookLibrary.init();
       bookLibrary.listAllBooks();
    }
}
