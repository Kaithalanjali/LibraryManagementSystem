package LibraryManagementSystem.main;

import LibraryManagementSystem.Customer.Customer;
import LibraryManagementSystem.Entities.Book;
import LibraryManagementSystem.Entities.Newspaper;
import LibraryManagementSystem.Entities.Videos;
import LibraryManagementSystem.Library.MyLibrary;

public class MyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//creating library
		MyLibrary library = new MyLibrary();
		//creating entities
		Book book1 = new Book(1,"Action","Harry Potter","Author1",1);
		Book book2 = new Book(2,"Spritual","How to think like Monk","Jay Shetty",2);
		Book book3 = new Book(2,"Spritual","How to think like Monk","Jay Shetty",1);
		Videos video1 = new Videos(3,"Documentary","Video 1",2.5,1);
		Newspaper np1 = new Newspaper(4,"News","Amar ujala","2023-07-18","Newspaper1",1);
		
		//adding entities in library
		library.addnewEntity(book1);
		library.addnewEntity(book2);
		library.addnewEntity(book3);
		library.addnewEntity(video1);
		library.addnewEntity(np1);
		
		//creating customers
		Customer c1 = new Customer(1,"John",5,15);
		Customer c2 = new Customer(2,"Anjali",5,20);
		Customer c3 = new Customer(3,"Tapas",5,0);
		Customer c4 = new Customer(4,"Bhawak",5,0);
		//adding customer to the library
		library.addnewCustomer(c1);
		library.addnewCustomer(c2);
		library.addnewCustomer(c3);
		library.addnewCustomer(c4);
		
		library.displayAvailableEntities();
		//customer borrowing entity
		library.borrowEntity(c2, np1);
		library.borrowEntity(c1, np1);
		library.borrowEntity(c2, book2);
		library.displayAvailableEntities();
		library.borrowEntity(c1, book2);
		library.displayAvailableEntities();
		library.borrowEntity(c3, book3);
		library.displayAvailableEntities();
		library.borrowEntity(c2, book2);
		library.displayAvailableEntities();
		library.borrowEntity(c4, book3);
		//showing available an borrowed entities
		library.displayAvailableEntities();
		library.displayBorrowedEntities();
		//customer c2 returning the newspaper
		library.returnEntity(c2, np1, 11.0);
		//customer now borrowing now available newspaper
		library.borrowEntity(c1, np1);
		//displaying borrowed entity by c1 customer
		library.displayBorrowedEntitiesByCustomer(c1);
		
		library.searchEntitesByGenre("Action");
		
	}

}
