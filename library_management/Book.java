

public class Book extends library_object {
	public int id;
	public String name;
	public Publisher pub;
	public String isbn;
    public Author authors[];
	public String category;
	public Double price;
	public String status;  //issued, damaged, available
	
	
	public Book() {
		
		id=0; name=""; isbn=""; category=""; price=0.0; status="available";
	}
	
	public int get_id() {
		return id;
	}
	
	
}