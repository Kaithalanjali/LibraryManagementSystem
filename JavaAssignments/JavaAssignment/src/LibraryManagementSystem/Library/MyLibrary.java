package LibraryManagementSystem.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import LibraryManagementSystem.Customer.Customer;
import LibraryManagementSystem.Entities.Entity;

public class MyLibrary {
	//this will store store the list of entities according to the genre
	static public Map<String, List<Entity>> entitiesByGenre;
	//Storing customer with their ids;
	private Map<Integer, Customer> customers;
	//this will store the number of each entity have been borrowed 
	private Map<Entity, Integer> borrowedCounts;
	private boolean alreadyborrowed(Customer c, Entity e) {
		List<Entity> listOfBorrowedEntity = c.getAllBorrowedentities();
		for(Entity entity:listOfBorrowedEntity) {
			if(entity.getId()==e.getId()) {
				return true;
			}
		}
		return false;
	}
	public MyLibrary() {
		super();
		entitiesByGenre = new HashMap<>();
		customers = new HashMap<>();
		borrowedCounts = new HashMap<>();
	}
	//for adding the new entity
	public void addnewEntity(Entity entity) {
		//here we are getting the list of genre, as same genre of entity to be added
		String genre = entity.getGenre();
		List<Entity> entities = entitiesByGenre.getOrDefault(genre, new ArrayList<>());

		boolean alreadyexistinList = false;
		
		//for(Entity singleentity:entities) {
		for(int i=0;i<entities.size();i++) {
			Entity singleentity=entities.get(i);
			if(singleentity.getId()== entity.getId() && singleentity.getTitle()==entity.getTitle() && singleentity.getEntityType()==entity.getEntityType()) {
				int temp=singleentity.getNoOfCopies();
				temp+=entity.noOfCopiesToBeAdded();
				singleentity.setNoOfCopies(temp);
				//System.out.println("No of copies"+ entities.get(i).getNoOfCopies());
				Integer temp1 = borrowedCounts.getOrDefault(singleentity, 0);
				borrowedCounts.put(entity, temp1);
				alreadyexistinList=true;
				break;
			}else if(singleentity.getId()== entity.getId()) {
				System.out.println("Entity with ID"+ entity.getId()+" already exist");
			}
		}
		if(!alreadyexistinList) {
			entities.add(entity);
			entitiesByGenre.put(genre, entities);
			borrowedCounts.put(entity, 0);
		}
		
		
	}
	public void removeEntity(Entity entity) {
		List<Entity> entities = entitiesByGenre.get(entity.getGenre());
		if(entities!=null && borrowedCounts.get(entity)==0) {
			entities.remove(entity);
			borrowedCounts.remove(entity);
		}else if(borrowedCounts.get(entity)!=0) {
			System.out.println("Cannot remove "+entity.getTitle()+" "+entity.getGenre()+" because it has not been returned by customers");
		}else {
			System.out.println("Cannot remove "+entity.getTitle()+" "+entity.getGenre()+" because it doesn't exist in the library");
		}
	}
	
	public void addnewCustomer(Customer customer) {
		if(customers.containsKey(customer.getId())) 
			System.out.println("Customer with "+customer.getId()+" already exists");
		customers.put(customer.getId(), customer);
	}
	public void removeCustomer(Customer customer) {
		customers.remove(customer.getId());
	}
	public void borrowEntity(Customer customer,Entity entity) {
		int borrowedCount = borrowedCounts.getOrDefault(entity, 0);
		//checking if that entity is already borrowed by customer 
		if(alreadyborrowed(customer,entity)) {
			System.out.println(customer.getName()+" has already borrowed "+entity.getTitle()+".");
		}
		//checking if customer can borrow further and the entity is available to be borrowed
		else if(customer.getCurrentlyBorrowed() < customer.getBorrowingLimit() && entity.getNoOfCopies()>=1) {
			borrowedCounts.put(entity, borrowedCount+1);
			
			customer.setCurrentlyBorrowed(customer.getCurrentlyBorrowed()+1);
			customer.addtoallBorrowedentities(entity);
 			System.out.println(customer.getName()+ " has borrowed "+ entity.getTitle()+".");
 			//checking is entity will be further available or not
 			int noofcopy= entity.noOfCopiesToBeAdded();
			entity.setNoOfCopies(noofcopy-1);
			if(noofcopy==1 ) {
				entity.setAvailable(false);
			}
			
		}else {
			System.out.println(customer.getName()+ " can not borrow "+ entity.getTitle()+" at the moment.");
		}
	}
	public void returnEntity(Customer customer, Entity entity, Double days) {
		if(borrowedCounts.containsKey(entity)) {
			customer.removeBorrowedEntity(entity);
			int borrowedCount = borrowedCounts.get(entity);
			if(borrowedCount>0) {
				borrowedCounts.put(entity, borrowedCount-1);
				Integer temp =entity.getNoOfCopies();
				entity.setNoOfCopies(temp+1);
				entity.setAvailable(true);
				System.out.println(customer.getName() + " has returned " + entity.getTitle()+".");
				if(days>10) {
					Double fine = (days-10.0)*10.0;
					Double settingnewFine = customer.getOverdueFine() + fine;
					customer.setOverdueFine(settingnewFine);
					System.out.println("Over due fine for the customer " + customer.getName() +" "+ customer.getOverdueFine());
				}
			}else {
				System.out.println(customer.getName()+"did not borrow" + entity.getTitle()+".");
			}
		}else {
			System.out.println("Entity not found in the library");
		}
	}
	
	public void displayAvailableEntities() {
		System.out.println("Available Entities:");
		for(String genre: entitiesByGenre.keySet()) {
			List<Entity> entities = entitiesByGenre.get(genre);
			for(Entity entity: entities) {
				if(entity.isAvailable() && entity.getNoOfCopies()>0) {
					System.out.println("-"+entity.getNoOfCopies()+" copy of "+entity.getEntityType()+" of title "+ entity.getTitle()+" "+"("+entity.getGenre()+")");
					}
			}
		}
	}
	public void searchEntitesByGenre(String genre) {
		List<Entity> entities = entitiesByGenre.get(genre);
		if(entities!=null) {
			System.out.println("Entities in the genre "+genre+" genre");
			for(Entity entity:entities) {
				System.out.println("-"+entity.getTitle()+" "+entity.getEntityType());
			}
		}else {
			System.out.println("No entities found of "+genre+" genre");
		}
	}
	//list my borrowed items with their genre and type
	public void displayBorrowedEntities() {
		System.out.println("Borrowed Entities:");
		for(String genre:entitiesByGenre.keySet()) {
			List<Entity> entities = entitiesByGenre.get(genre);
			for(Entity entity: entities) {
				if(borrowedCounts.get(entity)>0) {
					System.out.println("-"+entity.getEntityType()+" "+entity.getTitle()+" ("+entity.getGenre()+")");
				}
			}
		}
	}
	
	public void displayBorrowedEntitiesByCustomer(Customer customer) {
		for(Entity entity: customer.getAllBorrowedentities()) {
			System.out.println("-"+entity.getEntityType()+" "+entity.getTitle()+" ("+entity.getGenre()+")");
		}
	}
	
	void payfine(Customer c, Double amount) {
		Double currentFine = c.getOverdueFine();
		if(amount>currentFine) {
			System.out.println("Please collect remaining change amoung "+ (amount-currentFine));
			c.setOverdueFine(0);
		}else {
			c.setOverdueFine(currentFine-amount);
		}
		
	}
	
}
