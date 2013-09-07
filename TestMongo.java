import java.net.UnknownHostException;
import java.util.Date;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class TestMongo {
	
	public static void main(String args[]) {
		try {
		
		MongoClient mongo = new MongoClient("localhost",27017);
		DB db = mongo.getDB("testdb");
		DBCollection table = db.getCollection("user");
		
		//insert
		BasicDBObject document = new BasicDBObject();
		document.put("name", "Hitesh");
		document.put("age", "21");
		document.put("college", "Institute of Engineering & Management");
		document.put("createdDate", new Date());
		
		table.insert(document);
		
		//display 
		
		DBCursor cursor2 = table.find();
		
		while(cursor2.hasNext()) {
			System.out.println(cursor2.next());
		}
		
		System.out.println("Done");
		
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		catch(MongoException m) {
			m.printStackTrace();
		}
	}

}
