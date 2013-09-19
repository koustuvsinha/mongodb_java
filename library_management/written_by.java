import java.util.ArrayList;

public class written_by extends library_object {

	private int id;
	public int author_id;
	public int book_id;
	
	public written_by() {
		id =0 ; author_id = 0; book_id = 0;
	}
	
	public String keys[] = new String[] { "id", "author_id", "book_id" };
	
	public void put(ArrayList<String> p) {
		id = Integer.parseInt(p.get(0));
		author_id = Integer.parseInt(p.get(1));
		book_id = Integer.parseInt(p.get(2));
	}
	
	public ArrayList<String> get() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(0, ((Integer)id).toString());
		res.add(1, ((Integer)author_id).toString());
		res.add(2, ((Integer)book_id).toString());
		return res;
	}
	
	public int get_id() {
		return id;
	}
}
