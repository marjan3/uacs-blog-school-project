package com.mtanevski.controllers;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mtanevski.models.About;
import com.mtanevski.models.Post;

/**
 * Handles requests for the application home page.
 */
@Controller
public class IndexController extends AbstractController{
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"","/","/index","/home"}, method = RequestMethod.GET)
	public ModelAndView doIndex(ModelMap model,
			 @RequestParam(value = "skip", required = false) String paramSkip,
			 @RequestParam(value = "search", required = false) String search,
			 @RequestParam(value = "tag", required = false) String tag) {
		
		ModelAndView mav = new ModelAndView("index");
		mav.clear();
		mav= new ModelAndView("index");
		
		List<Post> posts = new ArrayList<Post>();
		int postsSize = service.getPostsSize();
		int skip = 0;
		
		if(null!= search){
			try{
				search = search.trim();
				search = URLEncoder.encode(search, "UTF-8"); 
				List<Post> allPosts = service.getAllPosts();
				for(Post post : allPosts){
					if(post.getTitle().contains(search) || post.getContent().contains(search)){
						post.getTitle().replaceAll(search, "<abbr title=\"attribute\">" + search + "</abbr>");
						posts.add(post);
					}
				}
				skip= Integer.MAX_VALUE; // skip paging
				/*mav.clear();
				mav= new ModelAndView("index");*/
				mav.addObject("postsSize",postsSize);
				mav.addObject("posts", posts);
				mav.addObject("skip", skip);
				return mav;
			}catch(Throwable err){
				err.printStackTrace();
			}
		}
		
		if(null!= tag){
			try{
				tag = tag.trim();
				tag = URLEncoder.encode(tag, "UTF-8"); 
				List<Post> allPosts = service.getAllPosts();
				for(Post post : allPosts){
					if(post.getTags().contains(tag)){
						posts.add(post);
					}
				}
				skip= Integer.MAX_VALUE; // skip paging

				mav.addObject("postsSize",postsSize);
				mav.addObject("posts", posts);
				mav.addObject("skip", skip);
				return mav;
			}catch(Throwable err){
				err.printStackTrace();
			}
		}
		
		
		if(null!= paramSkip){
			try{
				skip = Integer.parseInt(paramSkip);
			}catch(NumberFormatException nfe){
				nfe.printStackTrace();
			}
		}
		
		if(skip > 0 && skip < postsSize){
			if(skip%7==0)
				posts = service.getLatestPosts(7, skip, true);
		}else{
			posts = service.getLatestPosts();
		}
		
		mav.addObject("postsSize",postsSize);
		mav.addObject("posts", posts);
		mav.addObject("skip", skip);
		return mav;
	}
	
	
}
