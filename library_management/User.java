

public class User extends library_object {

	public int id;
	public String name;
	public String address;
	public String email;
	
	
	public User() {
		id = 0; name = ""; address = ""; email = "";
	}
	
	public int get_id() {
		return id;
	}
}
