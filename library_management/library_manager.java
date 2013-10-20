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
	private DBCollection backup_add;									//backup collection to store new additions	
	private DBCollection backup_update;									//backup collection to store old updates
	
	public library_manager() {
		try {
			
			mongo = new MongoClient("localhost",27017);
			db = mongo.getDB("library");
			
			
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	
  public boolean add_j(T b,String tab) {
	  
	  try {
	  DBCollection table = db.getCollection(tab);  
	  backup_add = db.getCollection("backup_add");
	  
	  Gson gson = new Gson();
      String json = gson.toJson(b);
      DBObject doc = (DBObject)JSON.parse(json);
      table.insert(doc);
      BasicDBObject docadd = ((BasicDBObject)doc).append("table", tab);
      backup_add.insert(docadd);											//backup new inserts in backup collection
      
      return true;
	  }catch(MongoException m) {
		  m.printStackTrace();
		  return false;
	  }
  }
  public boolean update(T b,String tab) {
	  try {
		  DBCollection table = db.getCollection(tab);  
		  backup_update = db.getCollection("backup_update");
		  
		  Gson gson = new Gson();
	      String json = gson.toJson(b);
	      DBObject doc = (DBObject)JSON.parse(json);
	      BasicDBObject query = new BasicDBObject("id",b.get_id());
	      BasicDBObject docup = ((BasicDBObject)table.findOne(query)).append("table", tab);
	      backup_update.insert(docup);										//backups old update values in backup collection
	      table.update(query, doc);
	      return true;
		  
	  }catch(MongoException m) {
		  m.printStackTrace();
		  return false;
	  }
  }
  
  public boolean rollback() {													//new method, reverts to original
	  backup_add = db.getCollection("backup_add");
	  backup_update = db.getCollection("backup_update");
	  while(backup_add.find().size() != 0) {
		  DBCursor dc = backup_add.find();
		  while(dc.hasNext()) {
			  BasicDBObject bdb = (BasicDBObject)dc.next();
			  String tab = bdb.getString("table");
			  bdb.removeField("table");
			  DBCollection table = db.getCollection(tab);
			  BasicDBObject rem = new BasicDBObject().append("id", bdb.get("id"));
			  table.remove(rem);
		  }
	  }
	  
	  
	  
	  
	  backup_add.drop();												//destroys backup
	  backup_update.drop();
	  return true;
  }
  
  public boolean commit() {
	  backup_add = db.getCollection("backup_add");						//destroys backup so no further rollback possible
	  backup_update = db.getCollection("backup_update");
	  
	  backup_add.drop();
	  backup_update.drop();
	  return true;
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
  
  
  
  public T getOneObj(int id, String tab, T b, Class<T> c) {
	  
	  BasicDBObject query = new BasicDBObject("id",id);
	  DBCollection table = db.getCollection(tab);
	  
	  Gson gson = new Gson();
	  
	  BasicDBObject db = (BasicDBObject)table.findOne(query);
	  String json = db.toString();
	  
	  b = gson.fromJson(json, c);	  
	  
	  return b;  
  }


   public int get_id(String tab) {
	   
	   DBCollection table = db.getCollection(tab);
	   BasicDBObject orderBy = new BasicDBObject().append("id", -1);
	   DBCursor dbo = (DBCursor) table.find().sort(orderBy).limit(1);
	   int id = 0;
	   
	   if(dbo.hasNext()) {
		   BasicDBObject res = (BasicDBObject)dbo.next();
		   if(res.get("id")!=null) {
		   id = ((Integer)res.get("id"));
		   }
	   }
	   
	   return id + 1;
   }
   

}




