package data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 *
 * @author Cherry Rose Semeña
 */

public class DBConnectorMongoDB { 
    
    private MongoClient mongoClient= null;
    
//    private String URI = "mongodb://localhost/dbtest";
      private String URI = "mongodb://cph-cs241:cjs192@ds017514.mlab.com:17514/cjs_db";
//    mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
//    mongodb://root:secret@db1.server.com:27027
    
    
    public MongoClient getConnection(){
        try{
           this.mongoClient = new MongoClient(new MongoClientURI(URI));
        
        }catch(Exception e){
            System.out.println("ERROR IN MONGODB CONNECTION" + e.toString());
        }
         
        return this.mongoClient;
    }
    
    public void close(){
        this.mongoClient.close();
    }
   
}
