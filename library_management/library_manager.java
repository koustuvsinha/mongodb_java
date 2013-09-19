import java.util.ArrayList;
import java.util.Iterator;
import java.net.UnknownHostException;
import org.bson.BSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class library_manager<T extends library_object> {

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
	
	public int add(T b,String tab) {
		
		try {
		
		ArrayList<String> val = get_values(b);
		String keys[] = get_keys(b);
		
		int ct = 0;	
			
			
		DBCollection table = db.getCollection(tab);
		BasicDBObject doc = new BasicDBObject();
		
		Iterator<String> it = val.iterator();
		
		int acc = get_id(tab);
		
		while(it.hasNext()) {
			String v = it.next();
			
			if(ct==0) { 
				doc.put(keys[ct], ((Integer)(acc)).toString());
			}
			else { 
				doc.put(keys[ct],v);
			}
			
			ct ++ ;
		}
		
		table.insert(doc);
		
		return acc;
		
		}catch(MongoException m) {
			m.printStackTrace();
		}
		
		return 1;
		
	}
	
   public T get(String tab,T b) {
		
	   DBCollection table = db.getCollection(tab);
	   
	   BasicDBObject db = (BasicDBObject) table.findOne();
	   ArrayList<String> val = get_values(b);
	   String[] keys = get_keys(b);
	   
	   for(int i=0;i<b.keys.length;i++) {
		   val.add(i,(String)db.get(keys[i]));
	   }
	   
	   b.put(val);
	   
	   return b;
	}
   
   public ArrayList<T> get(String key,String value,String tab,T b) {
		
	   BasicDBObject query = new BasicDBObject(key,value);
	   DBCollection table = db.getCollection(tab);
	   
	   ArrayList<T> blist = null;
	   String[] keys = get_keys(b);
	   
	   DBCursor cursor = table.find(query);
	   if(cursor!=null) {
	   blist = new ArrayList<T>();
	   while(cursor.hasNext()) {
		   BasicDBObject db = (BasicDBObject)cursor.next();
		   
		   ArrayList<String> val = new ArrayList<String>();
		   for(int i=0;i<keys.length;i++) {
			   val.add(i,(String)db.get(keys[i]));
		   }
		   b.put(val);
		   
		   blist.add(b);
	   }
	   }
	   	   
	   return blist;
	}
   
   public void update(T b, String tab) {
	   DBCollection table = db.getCollection(tab);
	   BasicDBObject up = new BasicDBObject();
	   ArrayList<String> val = get_values(b);
	   String keys[] = get_keys(b);
	   for(int i=1;i<keys.length;i++) {
		   up.append(keys[i], val.get(i));
	   }
	   BasicDBObject search = new BasicDBObject().append(keys[0], val.get(0));
	   table.update(search, up);
   }
   
   public void remove(T b, String tab) {
	   DBCollection table = db.getCollection(tab);
	   BasicDBObject rem = new BasicDBObject();
	   String[] keys = get_keys(b);
	   ArrayList<String> val = get_values(b);
	   rem.put(keys[0], val.get(0));
	   table.remove(rem);
   }
   
   private int get_id(String tab) {
	   
	   DBCollection table = db.getCollection(tab);
	   BasicDBObject orderBy = new BasicDBObject().append("id", -1);
	   DBCursor dbo = (DBCursor) table.find().sort(orderBy).limit(1);
	   int id = 0;
	   
	   if(dbo.hasNext()) {
		   BasicDBObject res = (BasicDBObject)dbo.next();
		   if(res.get("id")!=null) {
		   id = Integer.parseInt((String) res.get("id"));
		   }
	   }
	   
	   return id + 1;
   }
   
   private String[] get_keys(T b) {
	   String[] keys = null;
	   
	   if(b instanceof publisher) {
			publisher obj = (publisher) b;
			keys = obj.keys;
		}
		if(b instanceof author) {
			author obj = (author)b;
			keys = obj.keys;
		}
		if(b instanceof User) {
			User obj = (User)b;
			keys = obj.keys;
		}
		if(b instanceof written_by) {
			written_by obj = (written_by)b;
			keys = obj.keys;
		}
		if(b instanceof Issue) {
			Issue obj = (Issue)b;
			keys = obj.keys;
		}
		if(b instanceof Book) {
			Book obj = (Book)b;
			keys = obj.keys;
		}
		
		
	   
	   return keys;
   }
   
   private ArrayList<String> get_values(T b) {
	   ArrayList<String> val = null;
	   if(b instanceof publisher) {
			publisher obj = (publisher) b;
			val = obj.get();
		}
		if(b instanceof author) {
			author obj = (author)b;
			val = obj.get();
		}
		if(b instanceof User) {
			User obj = (User)b;
			val = obj.get();
		}
		if(b instanceof written_by) {
			written_by obj = (written_by)b;
			val = obj.get();
		}
		if(b instanceof Issue) {
			Issue obj = (Issue)b;
			val = obj.get();
		}
		if(b instanceof Book) {
			Book obj = (Book)b;
			val = obj.get();
		}
		
		return val;
   }
	
}




