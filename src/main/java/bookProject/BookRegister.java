package bookProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

class BookRegister {

    private final ArrayList<Book> bookList;
    private HashMap<String, Book> bookHashMap;

    //Constructor
    BookRegister(){
        bookList = new ArrayList<>();
        bookHashMap = new HashMap<>();
    }

    //Adds 4 test books to the library
    void addTestBooks() {
        Book book1 = new Book("Title1", "Author1", "Publisher1", 2019, 500, "1234567890123", false);
        Book book2 = new Book("Title2", "Author2", "Publisher2", 2019, 500, "1234567890124", false);
        Book book3 = new Book("Title3", "Author3", "Publisher3", 2019, 500, "1234567890125", false);
        Book book4 = new Book("Title4", "Author4", "Publisher4", 2019, 500, "1234567890126", false);
        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookHashMap.put("1234567890123", book1);
        bookHashMap.put("1234567890124", book2);
        bookHashMap.put("1234567890125", book3);
        bookHashMap.put("1234567890126", book4);
    }

    //method which adds a book to the library
    void addBook(String title, String author, String publisher, int yearReleased, int pages, String EAN, boolean loaned) {
        Book book = new Book(title, author, publisher, yearReleased, pages, EAN, loaned);
        bookList.add(book);
        bookHashMap.put(EAN, book);
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
        Iterator bookIterator = bookHashMap.entrySet().iterator();
        while(bookIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)bookIterator.next();
            if(mapElement.getKey().equals(ean)) {
                return (Book)mapElement.getValue();
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
                found = true;
                bookList.get(i).printBook();
                System.out.println("Type 'yes' if this is the book you wish to remove, type 'no' if you do not wish to remove this book");
                String ans = sc.next().toLowerCase();
                if(ans.equals("yes")) {
                    System.out.println("You have deleted " + bookList.get(i).getBookTitle() + " From the bookregistry");
                    bookHashMap.remove(bookList.get(i).getBookEAN());
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