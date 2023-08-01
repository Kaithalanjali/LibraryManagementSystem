package LibraryManagementSystem.Entities;

import java.util.ArrayList;
import java.util.List;

import LibraryManagementSystem.Library.MyLibrary;

public class Entity {
	private int id;
	private String genre;
	private String title;
	private boolean isAvailable;
	private Integer noOfCopies;
	private String entityType;
	private Integer AvailableNoCopies;
	public Entity(int id, String genre, String title, Integer noOfCopies) {
		super();
		this.id = id;
		this.genre = genre;
		this.title = title;
		this.isAvailable = true;
		this.noOfCopies = noOfCopies;
		this.AvailableNoCopies=noOfCopies;
		
	}
	public int getId() {
		return id;
	}
	public String getGenre() {
		return genre;
	}
	public String getTitle() {
		return title;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getEntityType() {
		return entityType;
	}
	public Integer getNoOfCopies() {
		String genre = this.getGenre();
		
		List<Entity> entities = MyLibrary.entitiesByGenre.getOrDefault(genre, new ArrayList<>());
		for(int i=0;i<entities.size();i++) {
			Entity e=entities.get(i);
			if(e.getId()==this.getId()) {
				return e.AvailableNoCopies;
			}
		}
		return 0;
	}
	public void setNoOfCopies(int temp) {
		// TODO Auto-generated method stub
		this.AvailableNoCopies=temp;
	}
	public Integer noOfCopiesToBeAdded() {
		return noOfCopies;
	}
}
