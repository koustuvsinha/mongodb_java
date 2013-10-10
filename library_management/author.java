import java.util.ArrayList;

public class Author extends library_object {

	private int id;
	public String name;
	public String address;
	public String email;
	public String phone;
	public ArrayList<Integer> books;
	
	public Author() {
		library_manager<Author> lba = new library_manager<Author>();
		id = lba.get_id("author");
		name= ""; address = ""; email = ""; phone = "";
		books = new ArrayList<Integer>();
	}
	
	public Author(String name) {
		this.name = name;
	}
	
	public String keys[] = new String[] { "id", "name", "address", "email", "phone" };
	
	public void put(ArrayList<String> p) {
		id = Integer.parseInt(p.get(0));
		name = p.get(1);
		address = p.get(2);
		email = p.get(3);
		phone = p.get(4);
	}
	
	public ArrayList<String> get() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(0, ((Integer)id).toString());
		res.add(1,name);
		res.add(2,address);
		res.add(3,email);
		res.add(4,phone);
		
		return res;
	}
	
	public int get_id() {
		return id;
	}
	
}
