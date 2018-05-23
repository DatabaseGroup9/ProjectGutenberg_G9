package data;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Cherry Rose Semeña
 */

public class DBConnectorMongoDB { 
    
    private MongoClient mongoClient= null;
    
//    private String URI = "mongodb://localhost/dbtest";
//      private String JAVA_ENV = System.getenv("JAVA_HOME");
//      private String PROD_MONGOURI = System.getenv("MONGO_URI");
      private String URI = "mongodb://myUserAdmin:abc123@206.189.21.241:27017";;
//              "mongodb://myUserAdmin:abc123@206.189.21.241:27017/gutenberg";
//    mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
//    mongodb://root:secret@db1.server.com:27027
      
    
    public MongoClient getConnection(){
        try{
//            if(PROD_MONGOURI != null){
//                URI = PROD_MONGOURI;
//            }else{
////                 String curdir = System.getProperty("user.dir");
//                Ini ini = new Ini(new File("/home/cjs/Desktop/DBTest Final Project/ProjectGutenberg_G9/src/main/java/data/secret.ini"));
//                URI = ini.get("header", "MONGO_URI");
//                System.out.println(URI);
//            }
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
