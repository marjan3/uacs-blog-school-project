<!-- ${pageContext.request.servletPath} -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="part/metas.jsp"%>

<title>Blog Title should be here</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="css/blog-post.css" rel="stylesheet">
<style type="text/css">
#comment-link:hover{
cursor: pointer;
text-decoration: none;
}
</style>
<script type="text/javascript">
	function search(that) {
		$("#searchInput").val($(that).parent().prev().val());
		$("#search").submit();
	}
	function showComment(that) {
		$("div.reply").hide();
		$("a.reply-link").show();
		$(that).first().next().slideToggle();
		$("a#comment-link").hide();
	}
	function showReply(that) {
		$("a.reply-link").show();
		$("div.reply").hide();
		$("div#comment").first().hide();
		$("a#comment-link").show();
		$(that).first().next().slideToggle();
		$(that).hide();
	}
	document.onready = function() {
		$("div#comment").hide();
		$("div.reply").hide();
		
	};
</script>
</head>

<body>

	<!-- header.jsp -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="../index"> UACS blog </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav">
            <li><a href="about">About</a></li>
            <li><a href="services">Services</a></li>
            <li><a href="contact">Contact</a></li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div><!-- /.container -->
    </nav>

	<div class="container">

		<div class="row">
			<div class="col-lg-8">

				<!-- the actual blog post: title/author/date/content -->
				<h1>${post.title}</h1>
				<p class="lead _content">
					by <a href="#"><c:if test="${not empty comment.user}">${post.user.name}</c:if></a>
				</p>
				<hr>
				<p>
					<span class="glyphicon glyphicon-time"></span> Posted on
					${post.postDate}
				</p>
				<hr>
				<img src="http://placehold.it/900x300" class="img-responsive">
				<hr>
				<p class="_content">${post.content}</p>
				<ul>
					<c:forEach var="tag" items="${post.tags}">
						<li><a href="/uacsblog/?tag=${tag}">${tag}</a></li>
					</c:forEach>
				</ul>

				<hr>

				<!-- the comment box -->
				<a onclick="showComment(this)" id="comment-link">Comment</a>
				<div class="well" id="comment">
					<h4>Leave a Comment:</h4>
					<form role="form" method="post">
						<input name="comment_username" type="text" class="form-control"
							placeholder="Username" required> <br> <input
							name="comment_email" type="text" class="form-control"
							placeholder="Email"> <br> <input
							name="comment_website" type="text" class="form-control"
							placeholder="Website"> <br>
						<div class="form-group">
							<textarea name="comment_content" class="form-control" rows="5"
								required></textarea>
						</div>
						<button name="submit" type="submit"
							class="btn btn-primary">Submit</button>
					</form>
				</div>

				<hr>

				<!-- the comments -->
				<c:forEach var="comment" items="${comments}">
					<c:if test="${comment.approved == true}">
						<h3 class="_content">
							<c:if test="${not empty comment.user}">${comment.user.name}</c:if>
							<small> on ${comment.postDate} said:</small>
						</h3>
						<p class="_content">${comment.content}</p>
						<%-- <c:choose>
							<c:when test="${empty comment.comment_id }">
								<a onclick="showReply(this)" class="reply-link">Reply</a>
								<div class="well reply">
									<h4>Leave a Reply:</h4>
									<form role="form" method="post">
									<input name="comment_comment_id" type="hidden" value="${comment._id}">
										<input name="comment_username" type="text"
											class="form-control" placeholder="Username" required>
										<br> <input name="comment_email" types="text"
											class="form-control" placeholder="Email"> <br> <input
											name="comment_website" type="text" class="form-control"
											placeholder="Website"> <br>
										<div class="form-group">
											<textarea name="comment_content" class="form-control"
												rows="5" required></textarea>
										</div>
										<button name="submit" type="submit"
											class="btn btn-primary">Submit</button>
									</form>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
						--%>
					</c:if>
				</c:forEach>
			</div>

			<div class="col-lg-4">
				<div class="well">
					<h4>Blog Search</h4>
					<form id="search" action="/uacsblog/" method="get"
						style="display: none;">
						<input id="searchInput" type="hidden" name="search" /> <input
							name="search" type="submit" />
					</form>
					<div class="input-group">
						<input type="text" class="form-control"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="button"
								onclick="search(this);">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
					<!-- /input-group -->
				</div>
				<!-- /well -->
				<%-- <div class="well">
            <h4>Popular Blog Categories</h4>
              <div class="row">
                <div class="col-lg-6">
                  <ul class="list-unstyled">
                    <li><a href="#dinosaurs">Dinosaurs</a></li>
                    <li><a href="#spaceships">Spaceships</a></li>
                    <li><a href="#fried-foods">Fried Foods</a></li>
                    <li><a href="#wild-animals">Wild Animals</a></li>
                  </ul>
                </div>
                <div class="col-lg-6">
                  <ul class="list-unstyled">
                    <li><a href="#alien-abductions">Alien Abductions</a></li>
                    <li><a href="#business-casual">Business Casual</a></li>
                    <li><a href="#robots">Robots</a></li>
                    <li><a href="#fireworks">Fireworks</a></li>
                  </ul>
                </div>
              </div>
              --%>
				<div class="well">
					<h4>Side Widget Well</h4>
					<p>Bootstrap's default wells work great for side widgets! What
						is a widget anyways...?</p>
				</div>
				<!-- /well -->
			</div>

		</div>
		<%@include file="part/footer.jsp"%>
	</div>
	<!-- /.container -->

	<hr>

	<!-- JavaScript -->
	<%@include file="part/jscripts.jsp"%>

</body>
</html>