import java.util.ArrayList;
import java.net.UnknownHostException;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class library_manager {

	private DB db;
	private MongoClient mongo;
	
	public library_manager() {
		try {
			
			mongo = new MongoClient("localhost",27017);
			db = mongo.getDB("library");
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void add_book(Book b) {
		
		try {
		
		//BasicDBObject document = new BasicDBObject();
		
		DBCollection table = db.getCollection("book");
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", b.name);
		doc.put("author", b.author);
		doc.put("category", b.category);
		doc.put("isbn", b.isbn);
		doc.put("price", b.price);
		doc.put("publisher", b.publisher);
		
		table.insert(doc);
		
		}catch(MongoException m) {
			m.printStackTrace();
		}
	}
	
   public Book get_book() {
		
	   DBCollection table = db.getCollection("book");
	   Book b = new Book();
	   BasicDBObject db = (BasicDBObject) table.findOne();
	   
	   b.name = (String) db.get("name");
	   b.author = (String) db.get("author");
	   b.category = (String) db.get("category");
	   b.isbn = (String) db.get("isbn");
	   b.price = (Double)db.get("price");
	   b.publisher = (String) db.get("publisher");
	   
	   return b;
	}
   
   public ArrayList<Book> get_book(String key,String value) {
		
	   BasicDBObject query = new BasicDBObject(key,value);
	   DBCollection table = db.getCollection("book");
	   
	   DBCursor cursor = table.find(query);
	   ArrayList<Book> blist = new ArrayList<Book>();
	   while(cursor.hasNext()) {
		   BasicDBObject db = (BasicDBObject)cursor.next();
		   Book b = new Book();
		   b.name = (String) db.get("name");
		   b.author = (String) db.get("author");
		   b.category = (String) db.get("category");
		   b.isbn = (String) db.get("isbn");
		   b.price = (Double)db.get("price");
		   b.publisher = (String) db.get("publisher");
		   
		   blist.add(b);
	   }
	   	   
	   return blist;
	}
	
}

class Book {
	public String name;
	public String publisher;
	public String isbn;
	public String author;
	public String category;
	public Double price;
	
	public Book() {
		name=""; publisher=""; isbn=""; category=""; author=""; price=0.0;
	}	
}


