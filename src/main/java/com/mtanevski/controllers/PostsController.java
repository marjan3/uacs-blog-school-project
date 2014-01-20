package com.mtanevski.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchProviderException;
import java.util.List;

import org.lightcouch.NoDocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtanevski.models.About;
import com.mtanevski.models.Comment;
import com.mtanevski.models.Post;
import com.mtanevski.models.User;
/**
 * Handles requests for the application posts.
 */
@Controller
@RequestMapping(value = "/posts/*")
public class PostsController extends AbstractController {

	@RequestMapping(value="/{_id}")
	public ModelAndView doPosts(@PathVariable String _id, Model model,
			@RequestParam(value = "submit", required = false) String submit,
			@RequestParam(value = "comment_username", required = false) String username,
			 @RequestParam(value = "comment_email", required = false) String email,
			 @RequestParam(value = "comment_website", required = false) String website,
			 @RequestParam(value = "comment_content", required = false) String content,
			 @RequestParam(value = "comment_comment_id", required = false) String commentCommentId) throws UnsupportedEncodingException {
		
		ModelAndView mav = new ModelAndView("post");
		mav.clear();
		mav= new ModelAndView("post");
		
		if(null!= submit){
			try{
			Comment comment = new Comment();
			comment.setApproved(true);
			content = content.trim();
			content = URLEncoder.encode(content, "UTF-8"); 
			comment.setContent(content);
			User user = new User();
			user.setName(username);
			user.setEmail(email);
			user.setWebsite(website);
			comment.setUser(user);
			comment.setPost_id(_id);
			if(null!=commentCommentId && !commentCommentId.isEmpty()){
				comment.setComment_id(commentCommentId);
			}
			service.newComment(comment);
			}catch(Throwable err){
				err.printStackTrace();
			}
		}
		
		Post post = service.getPostBy_id(_id);
		List<Comment> comments = service.getCommentsForPost(_id);
		
		if(null!= post){
			mav.addObject("post",post);
			if(null != comments){
				for(Comment comment : comments)
					comment.setContent(URLDecoder.decode(comment.getContent(), "UTF-8"));
				mav.addObject("comments",comments);
			}
		}else{
			// TODO: handle this
		}
		return mav;
	}
}
