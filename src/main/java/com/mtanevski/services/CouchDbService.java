package com.mtanevski.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.lightcouch.CouchDbClient;
import org.lightcouch.NoDocumentException;
import org.lightcouch.Response;
import org.lightcouch.View;
import org.springframework.beans.factory.annotation.Autowired;

import com.mtanevski.models.About;
import com.mtanevski.models.Comment;
import com.mtanevski.models.Post;
import com.mtanevski.models.User;

public class CouchDbService {
	
	@Autowired
	CouchDbClient dbClient;
	
	public CouchDbService(){
		dbClient = new CouchDbClient("couchdb.properties");
	}
	public CouchDbService(String couchDbProperties){
		this.dbClient =  new CouchDbClient(couchDbProperties);
	}
	
	
	
	public About getAbout(){
		About about = null;
		try{
			about = dbClient.find(About.class, "about");
		}catch(NoDocumentException exc){
			exc.printStackTrace();
		}
		return about;
	}
	
	public List<Comment> getCommentsForComment(String commentId){
		List<Comment> listOfComments = dbClient.view("comments/by_comment_id")
				  .includeDocs(true)
				  .key(commentId)
				  .query(Comment.class);
		return listOfComments;
	}
	
	public List<Comment> getCommentsForPost(String postId){
		List<Comment> listOfComments = dbClient.view("comments/by_post_id")
				  .includeDocs(true)
				  .key(postId)
				  .query(Comment.class);
		return listOfComments;
	}
	
	public boolean newComment(Comment comment){
		try{
			dbClient.save(comment); 
		 	return true;
		}catch(NoDocumentException nde){
			return false;
		}
	}
	

	public boolean updateComment(Comment comment){
		try{
			dbClient.update(comment); 
		 	return true;
		}catch(NoDocumentException nde){
			return false;
		}
	}
	
	public Comment getCommentBy_id(String _id){
		Comment comment = null;
		try{
			comment = dbClient.find(Comment.class, _id);
		}catch(NoDocumentException nde){
			nde.printStackTrace();
		}
		return comment;
	}
	
	
	public Post getPostBy_id(String _id){
		Post post = null;
		try{
			post = dbClient.find(Post.class, _id);
		}catch(NoDocumentException nde){
			nde.printStackTrace();
		}
		return post;
	}
	
	public boolean newPost(Post post){
		try{
			dbClient.save(post); 
		 	return true;
		}catch(NoDocumentException nde){
			return false;
		}
	}
	
	public boolean updatePost(Post post){
		try{
			dbClient.update(post); 
		 	return true;
		}catch(NoDocumentException nde){
			return false;
		}
	}
		
	public boolean removePost(Post post){
		try{
			dbClient.remove(post); 
		 	return true;
		}catch(NoDocumentException nde){
			return false;
		}
	}
	public List<Post> getLatestPosts(int limit, int skip, boolean descending){
		List<Post> listOfPosts = dbClient.view("posts/by_date")
				  .includeDocs(true)
				  .descending(descending)
				  .limit(limit)
				  .skip(skip)
				  .query(Post.class);
		return listOfPosts;
	}
	
	public List<Post> getLatestPosts(int limit, int skip){
		List<Post> listOfPosts = dbClient.view("posts/by_date")
				  .includeDocs(true)
				  .descending(true)
				  .limit(limit)
				  .skip(skip)
				  .query(Post.class);
		return listOfPosts;
	}

	public List<Post> getLatestPosts(int limit){
		List<Post> listOfPosts = dbClient.view("posts/by_date")
				  .includeDocs(true)
				  .descending(true)
				  .limit(limit)
				  .query(Post.class);
		return listOfPosts;
	}
	
	public List<Post> getLatestPosts(){
		List<Post> listOfPosts = dbClient.view("posts/by_date")
				   .includeDocs(true)
				  .descending(true)
				  .limit(7)
				  .query(Post.class);
		return listOfPosts;
	}
	
	public List<Post> getAllPosts(){
		List<Post> listOfPosts = dbClient.view("posts/by_date")
				   .includeDocs(true)
				  .descending(true)
				  .query(Post.class);
		return listOfPosts;
	}
	
	
	public int getPostsSize(){
		int size = 0;
		try{
			size = dbClient.view("posts/by_date_count").queryForInt();
		}catch(NoDocumentException nde){
			nde.printStackTrace();
		}
		return size;
	}
	
	public List<Post> getPostsForUser(User user){
		List<Post> listOfPosts = dbClient.view("posts/by_user")
				  .includeDocs(true)
				  .key(user)
				  .query(Post.class);
		return listOfPosts;
	}
	
	public List<User> getAllOrderedUsers(){
		List<Post> listOfPosts = dbClient.view("users/by_posts")
				   .includeDocs(true)
				  .descending(true)
				  .query(Post.class);
		List<User> listOfUsers = new ArrayList<User>();
		for(Post post : listOfPosts){
			listOfUsers.add(post.getUser());
		}
		return listOfUsers;
	}
}
