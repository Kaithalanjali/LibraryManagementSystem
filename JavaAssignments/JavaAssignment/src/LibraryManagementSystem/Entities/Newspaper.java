package LibraryManagementSystem.Entities;

public class Newspaper extends Entity {
	private String publisher;
	private String date;
	private String entityType;
	public Newspaper(int id, String genre, String title, String date, String publisher,Integer noOfCopies) {
		super(id, genre, title, noOfCopies);
		// TODO Auto-generated constructor stub
		this.publisher = publisher;
		this.date = date;
		this.entityType="Newspaper";
	}
	public String getEntityType() {
		return entityType;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getDate() {
		return date;
	}
	
}
