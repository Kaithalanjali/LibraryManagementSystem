package LibraryManagementSystem.Customer;

import java.util.ArrayList;
import java.util.List;

import LibraryManagementSystem.Entities.Entity;

public class Customer {
	private int id;
	private String name;
	private int borrowingLimit;
	private double overdueFine;
	private int currentlyBorrowed;
	private List<Entity> allBorrowedentities;
	public Customer(int id, String name, int borrowingLimit, double overdueFine) {
		super();
		this.id = id;
		this.name = name;
		this.borrowingLimit = borrowingLimit;
		this.overdueFine = overdueFine;
		this.currentlyBorrowed =0;
		this.allBorrowedentities = new ArrayList<>();
	}
	public void setOverdueFine(double overdueFine) {
		this.overdueFine = overdueFine;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getBorrowingLimit() {
		return borrowingLimit;
	}
	public double getOverdueFine() {
		return overdueFine;
	}
	public void setCurrentlyBorrowed(int currentlyBorrowed) {
		this.currentlyBorrowed = currentlyBorrowed;
	}
	public int getCurrentlyBorrowed() {
		return currentlyBorrowed;
	}
	public void addtoallBorrowedentities(Entity entity) {
		allBorrowedentities.add(entity);
	}
	public void removeBorrowedEntity(Entity entity) {
		if(allBorrowedentities.contains(entity)) {
			allBorrowedentities.remove(entity);
			}
	}
	public List<Entity> getAllBorrowedentities() {
		return allBorrowedentities;
	}
	
	
	
}
