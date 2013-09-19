import java.util.ArrayList;

public class Book extends library_object {
	private int id;
	public String name;
	public int publisher_id;
	public String isbn;

	public String category;
	public Double price;
	public String status;  //issued, damaged, available
	
	public String[] keys = new String[]{ "id", "name", "publisher_id", "isbn", "category", "price", "status" };
	
	public Book() {
		
		id=0; name=""; publisher_id=0; isbn=""; category=""; price=0.0; status="available";
	}
	
	public void put(ArrayList<String> b) {
		id = Integer.parseInt(b.get(0));
		name = b.get(1);
		publisher_id = Integer.parseInt(b.get(2));
		isbn = b.get(3);

		category = b.get(4);
		price = Double.parseDouble(b.get(5));
		status = b.get(6);
			
	}
	
	public ArrayList<String> get() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(0,((Integer)id).toString());
		res.add(1,name);
		res.add(2,((Integer)publisher_id).toString());
		res.add(3,isbn);

		res.add(4,category);
		res.add(5,((Double)price).toString());
		res.add(6,status);
		
		return res;
		
	}
	
	public int get_id() {
		return id;
	}
	
}