package com.mtanevski.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;
import org.lightcouch.View;

import com.mtanevski.models.Comment;
import com.mtanevski.models.Post;
import com.mtanevski.models.User;

public class HomeTest {
	
	List empty = null;
	
    @Before public void initialize() {
       empty= new ArrayList();
    }
	@After
	public void execAfter(){
		empty =null;
	}
	@Test
	public void test() {
		assertEquals(2,2);
	}
	
	
	//initialize a client instance - reuse
	private static CouchDbClient dbClient;

	@Before
	public void getCouchDbProperties() throws IOException{
			/*try (InputStream is = getClass().getResourceAsStream("/couchdb-test.properties")) {
				Properties props = new Properties();
				props.load(is);
				couchDbProps.setDbName(props.)
			}*/
			dbClient = new CouchDbClient("couchdb.properties");
	}
	
	@Test
	public void createReadUpdateDeleteDocumentTest() {	
		
		//Create
		/*Post post = new Post(); 
		post.setTitle("Only you !");
		post.setContent("Appropriately engineer performance based catalysts for change before customized collaboration and idea-sharing. Holisticly disseminate tactical process improvements after resource maximizing methods of empowerment. Synergistically strategize 24/7 relationships with granular users. Proactively procrastinate high-quality models for adaptive benefits. Assertively scale timely action items with flexible innovation."

+"Continually pontificate robust results through.");
		List<String> tags = new ArrayList<String>();
		post.setPostDate(new Date(post.getPostDate().getDate() - 1));
		tags.add("ghj");
		tags.add("7567567");
		tags.add("567");
		tags.add("Hero");
		post.setTags(tags);
		User user = new User();
		user.setEmail("b@b.com");
		user.setName("b");
		user.setWebsite("b");
		post.setUser(user);
		Response response = dbClient.save(post); 
		assertEquals(null,response.getReason()); //document created*/
		//Via map
		Comment comment = new Comment();
		comment.setApproved(true);
		comment.setPost_id("10d0acc44c2a43a9a3cb9d63394b72e4");
		User user = new User();
		user.setEmail("g@g.com");
		user.setName("g");
		user.setWebsite("g");
		comment.setUser(user);
		comment.setContent("Don't listen to this guy, any blog with "
				+ "the categories 'dinosaurs, spaceships, fried foods,"
				+ " wild animals, alien abductions, business casual,"
				+ " robots, and fireworks' has true potential.");
		Response response = dbClient.save(comment); 
		assertEquals(null,response.getReason()); //document created*/
		
		/*List<Post> listOfPosts = dbClient.view("posts/by_date")
				  .includeDocs(true)
				  .descending(true)
				  .limit(7)
				  .skip(5)
				  .query(Post.class);
		listOfPosts = null;*/
	}

}
