package hw6;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  Represents book in the Library System
 */

class Book {
	private String title;
	private String author;
	private String isbn;
	private boolean isAvailable;
	
/*
 * Create a new book instance
 * @param title is the book's title
 * @param author is the book's author
 * @param isbn is the book's ISBN (10-13 digits)
 */
	
	public Book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.isAvailable = true; // Default: book is available
	}
	
	/*
	 * Getters and setters
	 * 
	 * Gets the title of the book
	 * @return the title of the book
	 */
	public String getTitle() {
		return title;
	}
	/*
	 * Gets the author of the book
	 * @return the author of the book
	 */
	public String getAuthor() {
		return author;
	}
	/*
	 * Gets the isbn of the book
	 * @return the isbn of the book
	 */
	public String getIsbn() {
		return isbn;
	}
	/*
	 * Checks if the book is available for checkout
	 * @return true if the book is available, false if not
	 */
	public boolean isAvailable() {
		return isAvailable;
	}
	/*
	 * Sets the availability status of the book
	 * @param available true if book is available, false if checked out
	 */
	public void setAvailable(boolean available) {
		isAvailable = available;
	}
	
	/*
	 * Returns formatted book details
	 * @return String containing book information
	 */
	
	public String getBookDetails() {
		String availability = isAvailable ? "Available" : "Checked Out";
		return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Status: " + availability;
	}
}

/*
 * Manages a collection of books in a library
 */
class Library {
	private ArrayList<Book> books;
	
	/*
	 * Constructs an empty Library Array
	 */
	public Library() {
		this.books = new ArrayList<>();
	}
	
	/*
	 * Adds a book to the library if ISBN is unique
	 * @param book  the book to add
	 * @return true if added successfully, false if ISBN already exists
	 */
	
	public boolean addBook(Book book) {
		for (Book b : books) {
			if (b.getIsbn().equals(book.getIsbn())) {
				return false;
			}
		}
		books.add(book);
		return true;
	}
	
	/*
	 * removes a book to the library by ISBN
	 * @param ISBN of the book to remove
	 * @return true if the book was removed,false if ISBN not found
	 */	
	public boolean removeBook(String isbn) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				books.remove(book);
				return true;
			}
		}
		return false;
	}
	/*
	 * Displays all books in the library
	 * Prints "No books in the library" if empty
	 */
	public void displayAllBooks() {
		if (books.isEmpty()) {
			System.out.println("No books in the library");
			return;
		}
		System.out.println("\n All Books in Library ");
		for (Book book : books) {
			System.out.println(book.getBookDetails());
		}
		System.out.println("\n End of List");
	}
	/*
	 * Searches for books by title (case-insensitive)
	 * @param title the title to search for
	 */
	public void searchByTitle(String title) {
		boolean found = false;
		System.out.println("\n Search Results for Title: " + title);
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				System.out.println(book.getBookDetails());
				found = true;
			}
		}
		if (!found) {
			System.out.println("No books found with title: " + title);
		}
		System.out.println("\n ");
	}
	
	/*
	 * Searches for books by author (case-insensitive)
	 * @param author the author to search for
	 */
	public void searchByAuthor(String author) {
		boolean found = false;
		System.out.println("\n Search Results for Author: " + author);
		for (Book book : books) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				System.out.println(book.getBookDetails());
				found = true;
			}
		}
		if (!found) {
			System.out.println("No books found by author: " + author);
		}
		System.out.println("\n ");
	}
	
	/*
	 * Checks out a book by ISBN (marks as unavailable)
	 * @param isbn the ISBN of the book to checkout
	 * @return true if checkout successful, false if book not found or already checked out
	 */
	public boolean checkOutBook(String isbn) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				if (book.isAvailable()) {
					book.setAvailable(false);
					
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	/*
	 * Returns a book by ISBN (marks as available)
	 * @param isbn the ISBN of the book to return
	 * @return true if return successful, false if book not found or already available
	 */
	public boolean returnBook(String isbn) {
		for (Book book : books) {
			if (book.getIsbn().equals(isbn)) {
				if (!book.isAvailable()) {
					book.setAvailable(true);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}

public class Main {

	public static void main(String[] args) {
			
			/*
			 * Entry point
			 * Initializes the library and handles user input in a loop
			 */

				Scanner scanner = new Scanner(System.in);
				Library library = new Library();
				
				while (true) {
					displayMenu();
					System.out.print("Enter your choice(1-8): ");
					String choice = scanner.nextLine();
					
					switch (choice) {
					case "1":
						addBookToLibrary(scanner, library);
						break;
						
					case "2":
						removeBookFromLibrary(scanner, library);
						break;
						
					case "3":
						library.displayAllBooks();
						break;
						
					case "4":
						searchByTitle(scanner, library);
						break;
						
					case "5":
						searchByAuthor(scanner, library);
						break;
						
					case "6":
						checkOutBook(scanner, library);
						break;
					
					case "7":
						returnBook(scanner, library);
						break;
						
					case "8":
						System.out.println("Exit Library Management System.");
						scanner.close();
						System.exit(0);
					
					default:
						System.out.println("Invalid choice. Please enter a number between 1 and 8");
					}
				}

			}
			/*
			 * Displays the main menu of the Library Management System
			 */
			
			private static void displayMenu() {
				System.out.println("\n Library Management System ");
				System.out.println("1. Add Book");
				System.out.println("2. Remove Book");
				System.out.println("3. Display All Books");
				System.out.println("4. Search by Title");
				System.out.println("5. Search by Author");
				System.out.println("6. Checkout Book");
				System.out.println("7. Return Book");
				System.out.println("8. Exit");
				
			}
			
			/*
			 * Prompts the user to enter book details and adds the book to the library
			 * @param library the Library object to which the book will be added
			 */
			private static void addBookToLibrary(Scanner scanner, Library library) {
				System.out.println("\n Add New Book");
				System.out.print("Enter book title: ");
				String title = scanner.nextLine();
				
				System.out.print("Enter author name: ");
				String author = scanner.nextLine();
				
				/*
				 * ISBN entry and Validation
				 */
				String isbn;
				String confirmIsbn;
				boolean isValid;
				
				do {
					/*
					 * First entry
					 */
					System.out.print("Enter ISBN (10-13 digits, digits only): ");
					isbn = scanner.nextLine();
					
					/*
					 * Confirm entry
					 */
					System.out.print("Re-enter ISBN to confirm: ");
					confirmIsbn = scanner.nextLine();
					
					/*
					 * Check if they match
					 */
					if (!isbn.equals(confirmIsbn)) {
						System.out.println("Error: ISBNs do not match");
						isValid = false;
						continue;
					}
					
					/*
					 * Check length
					 */
					if (isbn.length() < 10 || isbn.length() > 13) {
						System.out.println("Error: ISBN must be 10-13 digits.");
						isValid = false;
						continue;
				}

					/*
					 * Validate all characters are digits
					 */
					isValid = true;
					for (char c : isbn.toCharArray()) {
						if (!Character.isDigit(c)) {
							isValid = false;
							break;
						}
					}
				} while (!isValid);
				
				/*
				 * Add book to library
				 */
				Book newBook = new Book(title, author, isbn);
				if (library.addBook(newBook) ) {
					System.out.println("Book Added");
				} else {
					System.out.println("Error: A book with this ISBN already exists");
				}
			}
					
					
			/*
			 * Prompts the user to enter an ISBN and removes the corresponding book
			 * @param library the Library object to which the book will be removed
			 */
			private static void removeBookFromLibrary(Scanner scanner, Library library) {
				System.out.println("\n Remove Book ");
				System.out.print("Enter ISBN of the book to remove: ");
				String isbn = scanner.nextLine();
				
				if (library.removeBook(isbn)) {
					System.out.println("Book removed");
				} else {
					System.out.println("Error: Book with this ISBN not found.");
				}
			}
			
			/*
			 * Prompts the user to enter a title and searches for matching book in the library
			 * @param library the Library object to search
			 */
			private static void searchByTitle(Scanner scanner, Library library) {
				System.out.println("\n Search by Title ");
				System.out.println("Enter title to search: ");
				String title = scanner.nextLine();
				library.searchByTitle(title);
			}
			
			/*
			 * Prompts the user to enter a author and searches for matching book in the library
			 * @param library the Library object to search
			 */
			private static void searchByAuthor(Scanner scanner, Library library) {
				System.out.println("\n Search by Author ");
				System.out.println("Enter Author to search: ");
				String author = scanner.nextLine();
				library.searchByAuthor(author);
			}
			
			/*
			 * Prompts the user to enter an ISBN and checks out the corresponding book
			 * @param library the Library object to containing the book
			 */
			private static void checkOutBook(Scanner scanner, Library library) {
				System.out.println("\n Checkout Book ");
				System.out.println("Enter ISBN of the book to checkout");
				String isbn = scanner.nextLine();
				
				if (library.checkOutBook(isbn)) {
					System.out.println("Book checkout");
				} else { 
					System.out.println("Error: Book not found or already checked out.");
				}
			}
			
			/*
			 * Prompts the user to enter an ISBN and returns the corresponding book
			 * @param library the Library object to containing the book
			 */
			private static void returnBook(Scanner scanner, Library library) {
				System.out.println("\n Return Book");
				System.out.print("Enter ISBN of the book to return: ");
				String isbn = scanner.nextLine();
				
				if (library.returnBook(isbn)) {
					System.out.println("Book return successfully");
				} else {
					System.out.println("Error: Book not found or already available.");
				}
		}

}


