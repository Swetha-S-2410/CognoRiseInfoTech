import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isCheckedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void checkOut() {
        isCheckedOut = true;
    }

    public void returnBook() {
        isCheckedOut = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Status: " + (isCheckedOut ? "Checked Out" : "Available");
    }
}

class LibraryCatalog {
    private ArrayList<Book> books;

    public LibraryCatalog() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void searchByAuthor(String author) {
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(book);
            }
        }
    }

    public void displayAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isCheckedOut()) {
                    book.checkOut();
                    System.out.println("Book checked out successfully: " + book);
                } else {
                    System.out.println("Book is already checked out.");
                }
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                System.out.println("Book returned successfully: " + book);
                return;
            }
        }
        System.out.println("Book with title '" + title + "' not found.");
    }
}

class LibraryApp {
    public static void main(String[] args) {
        LibraryCatalog library = new LibraryCatalog();

        // Adding books to the catalog
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("1984", "George Orwell"));

        Scanner scanner = new Scanner(System.in);

        // Sample usage: Search by title
        System.out.print("Enter the title to search: ");
        String searchTitle = scanner.nextLine();
        library.searchByTitle(searchTitle);

        // Sample usage: Search by author
        System.out.print("Enter the author to search: ");
        String searchAuthor = scanner.nextLine();
        library.searchByAuthor(searchAuthor);

        // Sample usage: Check out a book
        System.out.print("Enter the title to check out: ");
        String checkoutTitle = scanner.nextLine();
        library.checkOutBook(checkoutTitle);

        // Sample usage: Return a book
        System.out.print("Enter the title to return: ");
        String returnTitle = scanner.nextLine();
        library.returnBook(returnTitle);

        // Display all books in the catalog
        System.out.println("All books in the catalog:");
        library.displayAllBooks();

        scanner.close();
    }
}
