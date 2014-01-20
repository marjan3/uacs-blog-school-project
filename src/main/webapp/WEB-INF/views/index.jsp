<!-- ${pageContext.request.servletPath} -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <%@include file="part/metas.jsp" %>

    <title>Uacs Blog</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- Add custom CSS here -->
    <link href="css/blog-home.css" rel="stylesheet">
	<script type="text/javascript">
		function search(that){
			$( "#searchInput" ).val($(that).parent().prev().val());
			$( "#search" ).submit();
		}
	</script>
  </head>

  <body>

    <%@include file="part/header.jsp" %>

    <div class="container">

      <div class="row">
        <div class="col-lg-8">
        <c:if test="${empty posts}">
        	<h1> No posts were found!</h1>
        </c:if>
        <c:forEach var="post" items="${posts}">
			<!-- blog entry -->
        	<h1><a href="posts/${post._id}">${post.title}</a></h1>
        	 <p class="lead">by <a href="#">${post.user.name}</a></p>
	        	 
		     <hr>
	         <p><span class="glyphicon glyphicon-time"></span> Posted on ${post.postDate}</p>
	                  
          	<hr>
          	<img src="http://placehold.it/900x300" class="img-responsive">
          	<hr>
          	<c:choose>
          		<c:when test="${fn:length(post.content) > 200}">
         			<p>${fn:substring(post.content,0,199)}...</p>
          		</c:when>
          		<c:otherwise>
         			<p>${post.content}</p>
          		</c:otherwise>
          	</c:choose>
         	<a class="btn btn-primary" href="posts/${post._id}">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>
             
          	<hr>
          
        </c:forEach>

         <%-- <!-- second blog entry -->
          <h1><a href="#">Another Blog Post</a></h1>
          <p class="lead">by <a href="index.php">Start Bootstrap</a></p>
          <hr>
          <p><span class="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:45 PM</p>
          <hr>
          <img src="http://placehold.it/900x300" class="img-responsive">
          <hr>
          <p>Science cuts two ways, of course; its products can be used for both good and evil. But there's no turning back from science. The early warnings about technological dangers also come from science...</p>
          <a class="btn btn-primary" href="#">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>
                    
          <hr>

          <!-- third blog entry -->
          <h1><a href="#">Third Blog Post Entry</a></h1>
          <p class="lead">by <a href="index.php">Start Bootstrap</a></p>
          <hr>
          <p><span class="glyphicon glyphicon-time"></span> Posted on August 28, 2013 at 10:45 PM</p>
          <hr>
          <img src="http://placehold.it/900x300" class="img-responsive">
          <hr>
          <p>Science cuts two ways, of course; its products can be used for both good and evil. But there's no turning back from science. The early warnings about technological dangers also come from science...</p>
          <a class="btn btn-primary" href="#">Read More <span class="glyphicon glyphicon-chevron-right"></span></a>
                    
          <hr>
          --%> 
          
          <!-- pager -->
          <ul class="pager">
          	<c:if test="${skip+7 < postsSize}" >
           		<li class="previous"><a href="?skip=${skip+7}">&larr; Older</a></li>
            </c:if>
            <c:if test="${skip-7 >= 0 and skip-7 < postsSize}" >
            	<li class="next"><a href="?skip=${skip-7}">Newer &rarr;</a></li>
            </c:if>
          </ul>

        </div>
        
        <div class="col-lg-4">
          <div class="well">
            <h4>Blog Search</h4>
            <form id="search" action="/uacsblog/" method="get" style="display:none;">
            	<input id="searchInput" type="hidden" name="search" />
            	<input name="search" type="submit" />
            </form>
            <div class="input-group">
              <input type="text" class="form-control">
              <span class="input-group-btn">
                <button class="btn btn-default" type="button" onclick="search(this);"><span class="glyphicon glyphicon-search"></span></button>
              </span>
            </div><!-- /input-group -->
          </div><!-- /well -->
          <%-- <div class="well">
            <h4>Popular Blog Tags</h4>
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
          </div><!-- /well -->
          --%>
          <div class="well">
            <h4>Side Widget Well</h4>
            <p>Bootstrap's default wells work great for side widgets! What is a widget anyways...?</p>
          </div><!-- /well -->
        </div>
      </div>
      
      <hr>
      
  <%@include file="part/footer.jsp" %>

    </div><!-- /.container -->

    <!-- JavaScript -->
    <%@include file="part/jscripts.jsp" %>

  </body>
</html>