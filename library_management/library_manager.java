import java.util.ArrayList;

import java.net.UnknownHostException;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.google.gson.Gson;

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
	
	
  public void add_j(T b,String tab) {
	  
	  try {
	  DBCollection table = db.getCollection(tab);  
      Gson gson = new Gson();
      String json = gson.toJson(b);
      DBObject doc = (DBObject)JSON.parse(json);
      table.insert(doc);
	  }catch(MongoException m) {
		  m.printStackTrace();
	  }
  }
  
  public ArrayList<T> getObj(String key,String val,String tab, T b, Class<T> c ) {
	  
	  BasicDBObject query = new BasicDBObject(key,val);
	  DBCollection table = db.getCollection(tab);
	  //query.append("_id", 0);
	  
	  Gson gson = new Gson();
	  
	  ArrayList<T> blist = null;
	  DBCursor cursor = table.find(query);
	   if(cursor!=null) {
	   blist = new ArrayList<T>();
	   while(cursor.hasNext()) {
		   BasicDBObject db = (BasicDBObject)cursor.next();

		   String json = db.toString();
		   //System.out.println(json);
		   b = gson.fromJson(json, c);

		   blist.add(b);
	   }
	   }
	  
	  return blist;
  }


   public int get_id(String tab) {
	   
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
   
/*
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
	*/
}




