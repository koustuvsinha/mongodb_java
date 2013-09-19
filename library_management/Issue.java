import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Issue extends library_object {

	private int id;
	public int user_id;
	public int book_id;
	public Date issued_on;
	public Date return_by;
	
	public String keys[] = new String[] { "accno","user_id","book_id","issued_on","return_by" }; 
	public Issue() {
		id = 0;
	}
	
	public void put(ArrayList<String> b) {
		id = Integer.parseInt((String)b.get(0));
		user_id = Integer.parseInt((String)b.get(1));
		book_id = Integer.parseInt((String)b.get(2));
		
		SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
		try {
		
			issued_on = form.parse(b.get(3));
			return_by = form.parse(b.get(4));
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public ArrayList<String> get() {
		ArrayList<String> res = new ArrayList<String>();
		res.add(0,((Integer)id).toString());
		res.add(1,((Integer)user_id).toString());
		res.add(2,((Integer)book_id).toString());
		
		SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy");
		try {
		
			res.add(3, form.format(issued_on));
			res.add(4, form.format(return_by));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
		
		return res;
		
	}
	
	public int get_id() {
		return id;
	}
	
}
