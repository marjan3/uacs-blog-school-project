package com.mtanevski.tests.couchdb;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lightcouch.CouchDbClient;
import org.lightcouch.DesignDocument;
import org.lightcouch.Response;
import org.lightcouch.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mtanevski.models.Comment;

public class CrudDocTest {

	//initialize a client instance - reuse
	private static CouchDbClient dbClient;

	@Before
	public void getCouchDbProperties() throws IOException{
			/*try (InputStream is = getClass().getResourceAsStream("/couchdb-test.properties")) {
				Properties props = new Properties();
				props.load(is);
				couchDbProps.setDbName(props.)
			}*/
			dbClient = new CouchDbClient("couchdb-test.properties");
	}
	
	@After
	public void shutdownCouchDbClient() {
		if(dbClient != null && dbClient.context() !=null)
			dbClient.shutdown(); //proper release of resources
	}

	@Test
	public void createReadUpdateDeleteDocumentTest() {	
		
		//Create
		Comment firstComment1 = new Comment(); 
		Response response = dbClient.save(firstComment1); 
		assertEquals(null,response.getReason()); //document created
		//Via map
		/*Map<String, Object> map = new HashMap<>();
		map.put("_id", "map-doc-id" + new Random().hashCode());
		map.put("title", "map_value");
		assertEquals("",dbClient.save(map)); //document created*/
		//Via Json
		/*JsonObject json = new JsonObject();
		json.addProperty("_id", "doc-id" + new Random().hashCode());
		json.add("array", new JsonArray());
		assertEquals(null,dbClient.save(json)); */
		//Via string
		/*String jsonstr = "{\"title\":\"val\"}";
		JsonObject jsonobj = dbClient.getGson().fromJson(jsonstr, JsonObject.class);
		assertEquals(null,dbClient.save(jsonobj));*/
		//Read
		firstComment1 = null;
		firstComment1 = dbClient.find(Comment.class, "64122cdc70184ed8b8bc8d517e2e3e59");
		assertEquals(true, firstComment1.isApproved());
		assertEquals("64122cdc70184ed8b8bc8d517e2e3e59",firstComment1.get_id());
		assertEquals("",firstComment1.getUser().getName());
		//Update
		Comment updateComment = new Comment();
		dbClient.save(updateComment); 
		updateComment = dbClient.find(Comment.class, "e44a85df842448c4870d8b73eaa3d85d");
		updateComment.getUser().setEmail("someemail@gmail.com");
		updateComment.getUser().setWebsite("www.whatwebsite.org");
		Response updateResponse = dbClient.update(updateComment);
		assertEquals(null,updateResponse.getReason()); //document updated
		
		//Delete
		//only works once
		//Comment deleteComment = new Comment();
		//dbClient.save(deleteComment);
		//deleteComment = dbClient.find(Comment.class, "f3923166abbc437883079dfb47147282");
		//Response deleteResponse = dbClient.remove(deleteComment); //only works once
		//assertEquals(null,deleteResponse.getReason()); //document updated
		
		//Contains
		assertEquals(false,dbClient.contains("TEST"));
	}
	
	@Test
	public void saveAttachementTest() throws IOException{
		
		//Save attachement
		try (InputStream is = getClass().getResourceAsStream("/attachment-test.jpg")) {
			// attachments 
			Response attachmentResponse = dbClient.saveAttachment(is, "attachment-test.jpg", "image/jpg");
			assertEquals(null,attachmentResponse.getReason()); //document updated
		}
		//Read attachment
		InputStream in = dbClient.find("994468b48fed46f6b80f776fe121a1f5/attachment-test.jpg");
		assertEquals(0,in.available());
	}
	
	@Test
	public void localDesignDocumentTest() {
		DesignDocument designDoc1 = dbClient.design().getFromDesk("test");
		//designDoc.
		Response response = dbClient.design().synchronizeWithDb(designDoc1);
		//dbClient.syncDesignDocsWithDb();
		assertEquals(null, response);
	}
	
	@Test
	public void designDocumentResponseTest(){
		List<Comment> listOfComments = dbClient.view("test/by_date")
				  .includeDocs(true)
				  .startKey("")
				  .endKey("")
				  .limit(10)
				  .query(Comment.class);
		for(Comment comment : listOfComments){
			assertEquals("",comment.getComment_id());
		}
		
		// View entries (keys/values), with Documents 
		/*View view = dbClient.view("test/by_date")
		  .key("null") // complex key as: values, or array
		  .reduce(false) 
		  .includeDocs(true); 
		assertEquals("",view.queryForString());*/
		// primitive types
		//int count = dbClient.view("test/by_tag")/*.key("couchdb")*/.queryForInt(); 
		//assertEquals(9,count);
	
	}
	
	@Test
	public void specialViewsTest(){
		
		// _all_docs 
		List<JsonObject> allDocs = dbClient.view("_all_docs").query(JsonObject.class);
		assertEquals(true,allDocs!=null);
		assertEquals(true,allDocs.size() > 10);
		/*// _temp_view
		List<Comment> list = dbClient.view("_temp_view")
		  .tempView("temp_1")
		// .tempView(mapRedObj) 
		  .includeDocs(true)
		  .reduce(false)
		  .query(Comment.class);*/
	}

}
