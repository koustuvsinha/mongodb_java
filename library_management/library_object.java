import java.util.ArrayList;

public class library_object {

	private int id;
	
	public String keys[] = new String[] { "id" };
	
	public void put(ArrayList<String> p) {
		id = Integer.parseInt(p.get(0));
	}
	
	public ArrayList<String> get() {
		
		ArrayList<String> res = new ArrayList<String>();
		res.add(0, ((Integer)id).toString());
		return res;
	}
	
	public int get_id() {
		return id;
	}
	
}
