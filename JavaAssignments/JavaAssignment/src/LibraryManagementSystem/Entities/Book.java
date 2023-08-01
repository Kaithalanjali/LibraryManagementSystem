package LibraryManagementSystem.Entities;



public class Book extends Entity {
	private String author;
	private String entityType;
	public Book(int id, String genre, String title, String Author,Integer noOfCopies) {
		super(id, genre, title, noOfCopies);
		this.author = Author;
		this.entityType="Book";
		// TODO Auto-generated constructor stub
	}
	public String getAuthor() {
		return author;
	}
	public String getEntityType() {
		return entityType;
	}

}
