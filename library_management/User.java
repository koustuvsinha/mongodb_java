import java.util.ArrayList;

public class User extends library_object {

	private int id;
	public String name;
	public String address;
	public String email;
	
	public String keys[] = new String[] { "id","name","address","email" }; 
	public User() {
		id = 0; name = ""; address = ""; email = "";
	}
	
	public void put(ArrayList<String> b) {
		id = Integer.parseInt((String)b.get(0));
		name = b.get(1);
		address = b.get(2);
		email = b.get(3);
	}
	
	public ArrayList<String> get() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(0,((Integer)id).toString());
		res.add(1,name);
		res.add(2,address);
		res.add(3,email);
		
		return res;
		
	}
	
	public int get_id() {
		return id;
	}
}
