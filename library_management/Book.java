import java.util.ArrayList;



public class Book extends library_object {
	private int id;
	public String name;
	public Publisher pub;
	public String isbn;
    public ArrayList<Integer> authors;
	public String category;
	public Double price;
	public String status;  //issued, damaged, available
	
	
	public Book() {
		library_manager<Book> lbb = new library_manager<Book>();
		id = lbb.get_id("book");
		name=""; isbn=""; category=""; price=0.0; status="available";
		authors = new ArrayList<Integer>();
	}
	
	public int get_id() {
		return id;
	}
	
	
}