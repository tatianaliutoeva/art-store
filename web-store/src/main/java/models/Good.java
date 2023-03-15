package models;

public class Good {
	private int id;
	private String title;
	private String genre;
	private String info;
	private String photo;
	private int price;
	public Good(int id, String title, String genre, String info, String photo, int price) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.info = info;
		this.photo = photo;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public String getInfo() {
		return info;
	}
	public String getPhoto() {
		return photo;
	}
	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return title + " стоит " + price;
	}
	
}
