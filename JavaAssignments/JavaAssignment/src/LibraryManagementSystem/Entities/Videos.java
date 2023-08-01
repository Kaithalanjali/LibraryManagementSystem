package LibraryManagementSystem.Entities;

public class Videos extends Entity {
	private double length;
	private String entityType;
	public Videos(int id, String genre, String title, double length,Integer noOfCopies) {
		super(id, genre, title, noOfCopies);
		this.length = length;
		this.entityType="Video";
		// TODO Auto-generated constructor stub
	}
	public double getLength() {
		return length;
	}
	public String getEntityType() {
		return entityType;
	}

	
	
}
