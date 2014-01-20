package com.mtanevski.controllers;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.common.xml.IfTag;
import org.lightcouch.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mtanevski.models.Comment;
import com.mtanevski.models.Post;
import com.mtanevski.models.User;

@Controller
@RequestMapping(value = "/admin/*")
public class AdminController extends AbstractController {

	@RequestMapping(value = { "", "/", "/login" })
	public String doLogin(HttpSession session, ModelMap model,
			@ModelAttribute User user, BindingResult br,
			@RequestParam(value = "submit", required = false) String submit) {

		if (null != submit) {
			List<User> users = service.getAllOrderedUsers();
			int i = 0;
			int j = 0;
			for (User u : users) {
				if (!user.getEmail().equals(u.getEmail())) {
					if (i == 0) {
						br.rejectValue("email", null,
								"Your email is incorrect!");
						i++;
					}
				} else {
					if (user.getPassword().equals(u.getPassword())) {
						user.setName(u.getName());
						user.setEmail(u.getEmail());
						user.setWebsite(u.getWebsite());
						user.setPassword(u.getPassword());
						session.setAttribute("user", user);
						return doPanel(session, model, null, null, null, null);
					} else {
						if (j == 0) {
							br.rejectValue("password", null,
									"Your password is incorrect!");
							j++;
						}
					}
				}
			}
		}

		// br.rejectValue("", "");
		model.addAttribute(user);
		return "admin/login";
	}

	@RequestMapping(value = "/panel")
	public String doPanel(HttpSession session, ModelMap model,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "submit", required = false) String submit,
			@RequestParam(value = "edit", required = false) String edit,
			@RequestParam(value = "remove", required = false) String remove) {

		User user = null;
		if (null != (user = (User) session.getAttribute("user"))) {
			if (null != logout) {
				session.removeAttribute("user");
				return "redirect:admin/login";
			}

			if (null != edit) {
				// Update
				Post post = new Post();
				post = service.getPostBy_id(edit);
				model.addAttribute("submitOrEdit", "edit");
				return doPost(session, model, post, null, null, null, null,
						null, null,null);
			}
			if (null != remove) {
				Post post = service.getPostBy_id(remove);
				service.removePost(post);

				List<Post> posts = service.getAllPosts();
				model.addAttribute("posts", posts);
				return "admin/panel";
			}

			if (null != submit) {
				Post post = new Post();
				model.addAttribute("submitOrEdit", "submit");
				return doPost(session, model, post, null, null, null, null,
						null, null, null);
			}

			List<Post> posts = service.getPostsForUser(user);
			model.addAttribute("posts", posts);
			return "admin/panel";

		}
		model.addAttribute("loginMsg",
				"Access restricted. Make sure you are logged in first!");
		model.addAttribute(user = new User());
		return "admin/login";

	}

	@RequestMapping(value = "/post")
	public String doPost(
			HttpSession session,
			ModelMap model,
			@ModelAttribute Post post,
			@RequestParam(value = "comment_id", required = false) String comment_id,
			@RequestParam(value = "approved", required = false) String approved,
			@RequestParam(value = "_id", required = false) String _id,
			@RequestParam(value = "_rev", required = false) String _rev,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "edit", required = false) String edit,
			@RequestParam(value = "submit", required = false) String submit) {

		User user = null;
		if (null != (user = (User) session.getAttribute("user"))) {

			if (null != logout) {
				session.removeAttribute("user");
				return "admin/login";
			}

			if (null != edit) {
				// Update
				if (null != _id) {
					post.set_id(_id);
				}
				if (null != _rev) {
					post.set_rev(_rev);
				}
				post.setUser(user);
				post.setPostDate(new Date());
				model.addAttribute("post", post);
				if (service.updatePost(post)) {
					return doPanel(session, model, null, null, null, null);
				} else {
					model.addAttribute("errMsg", "Post could not be updated!");
				}
				return "admin/post";
			}
			if (null != approved) {
				if (null != comment_id) {
					Comment comment = service.getCommentBy_id(comment_id);
					if (approved.equals("true")) {
						comment.setApproved(false);

					} else {
						comment.setApproved(true);

					}
					if (!service.updateComment(comment)) {
						model.addAttribute("errMsg",
								"Comment could not be updated!");
					}

					if(null!=_id){
					post = service.getPostBy_id(_id);
					List<Comment> comments = service.getCommentsForPost(_id);
					model.addAttribute("post", post);
					model.addAttribute("comments", comments);
					}
					return "admin/post";
				}
			}

			if (null != submit) {
				// Create new
				post.setUser(user);
				post.setPostDate(new Date());
				model.addAttribute("post", post);
				if (service.newPost(post)) {
					return doPanel(session, model, null, null, null, null);
				} else {
					model.addAttribute("errMsg", "Post could not be created!");
				}
			}

			List<Comment> comments = service.getCommentsForPost(post.get_id());

			model.addAttribute("post", post);
			model.addAttribute("comments", comments);
			return "admin/post";
		}

		return "admin/login";

	}
}
