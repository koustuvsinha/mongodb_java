import java.util.ArrayList;
import java.util.Map;

import java.net.UnknownHostException;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBAddress;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.google.gson.Gson;
import de.fhg.igd.mongomvcc.*;
import de.fhg.igd.mongomvcc.impl.MongoDBVFactory;



public class sandbox<T extends library_object> {

	private DB db;
	private MongoClient mongo;
	private VDatabase dba;
	private VFactory factory;
	private VBranch master;
	
	public sandbox() {
		try {
			
			mongo = new MongoClient("localhost",27017);
			db = mongo.getDB("library");
			
			factory = new MongoDBVFactory();
			dba = factory.createDatabase();
			dba.connect("sandbox");
			master = dba.checkout(VConstants.MASTER);
			  

		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
	}
		
		@SuppressWarnings("unchecked")
        public boolean add_j(T b,String tab) {
			  
			  try {
				  
			  VCollection tables = master.getCollection(tab);
			 
			  DBCollection table = db.getCollection(tab);  
			  
			  Gson gson = new Gson();
		      String json = gson.toJson(b);
		      DBObject doc = (DBObject)JSON.parse(json);
		      table.insert(doc);
		      Map<String,Object> docs = factory.createDocument();
		      
		      docs = gson.fromJson(json,docs.getClass());
		      
		      tables.insert(docs);
		      
		      
		      return true;
			  }catch(MongoException m) {
				  m.printStackTrace();
				  return false;
			  }
		  }
		
		public long commit() {
			return master.commit();
		}
	
}
