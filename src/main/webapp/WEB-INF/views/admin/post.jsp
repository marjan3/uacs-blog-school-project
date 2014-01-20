<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">

<title>Administration posts management</title>

<!-- Core CSS - Include with every page -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- Page-Level Plugin CSS - Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- SB Admin CSS - Include with every page -->
<link href="css/sb-admin.css" rel="stylesheet">
<script src="js/plugins/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
	tinymce.init({
		selector : 'textarea'
	});
	function submitLogout() {
		$("#logout").submit();
	}
	function submitComment(that) {
		$(that).parent().submit();
	}
</script>
</head>

<body>

	<div id="wrapper">

		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">Posts admin</a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<%-- <li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
						</li>
						<li class="divider"></li>--%>
						<li>
							<form id="logout" action="" method="post" style="display: none;">
								<input type="hidden" name="logout" /> <input type="submit" />
							</form> <a href="#" onclick="submitLogout()"><i
								class="fa fa-sign-out fa-fw"></i> Logout</a>
						</li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

		</nav>
		<!-- /.navbar-static-top -->

		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li><a href="panel"><i class="fa fa-dashboard fa-fw"></i>
							Dashboard</a></li>
				</ul>
				<!-- /#side-menu -->
			</div>
			<!-- /.sidebar-collapse -->
		</nav>
		<!-- /.navbar-static-side -->

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Posts</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<form:form role="form"  modelAttribute="post" action="post" commandName="post">
						<div class="form-group">
							<label>Title:</label> <form:input path="title" cssClass="form-control" autofocus="autofocus" required="true" maxlength="55" value="${post.title}" ></form:input>
							<p class="help-block">Please enter title for the post.</p>
							<label>Content:</label>
							<form:textarea  path="content"></form:textarea>
							<form:input path="_id" type="hidden" value="${post._id}" />
							<form:input path="_rev" type="hidden" value="${post._rev}"/>
						</div>
						<br>
						<button name="${submitOrEdit}" type="submit" style="width: 200px"
							class="btn btn-default btn-block">
							<c:choose>
								<c:when test="${submitOrEdit == 'edit' }">
								Save&nbsp;
							</c:when>
								<c:otherwise>
								Create new&nbsp;
							</c:otherwise>
							</c:choose>
							post
						</button>
					</form:form>
					<c:if test="${not empty errMsg}">
						<div class="alert alert-danger alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							${errMsg}
						</div>
					</c:if>
					<br>
					<c:forEach var="comment" items="${comments}">
					<c:choose>
                        	<c:when test="${comment.approved == true }">
                            	<c:set var="state" value="success" />
                        	</c:when>
                        	<c:otherwise>
                        		<c:set var="state" value="warning" />
                        	</c:otherwise>
                        	</c:choose>
					<div class="panel panel-${state}">
                        <div class="panel-heading">
                        <c:choose>
                        	<c:when test="${comment.approved == true }">
                            	Approved
                        	</c:when>
                        	<c:otherwise>
                        		Not approved
                        	</c:otherwise>
                        	</c:choose>
                        </div>
                        <div class="panel-body">
                            <p>${comment.content }</p>
                        </div>
                        <div class="panel-footer">
                            
                            
						<form id="comment" action="post" method="post">
							<c:choose>
                        	<c:when test="${comment.approved == true }">
							<input type="checkbox" id="approved" value="${comment.approved}" onchange="submitComment(this)" checked>
                        	</c:when>
                        	<c:otherwise>
							<input type="checkbox" id="approved" value="${comment.approved}" onchange="submitComment(this)" >
                        	</c:otherwise>
                        	</c:choose>
                        	<input type="hidden" name="approved" value="${comment.approved}">
							<input type="hidden" name="comment_id" value="${comment._id}">
							<input type="hidden" name="_id" value="${post._id}">
							<input type="hidden" name="comment_rev" value="${comment._rev}">
							 <input type="submit" style="display:none;"/>
						</form>
                        </div>
                    </div>
					</c:forEach>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<%@include file="../part/footer.jsp"%>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<!-- Core Scripts - Include with every page -->
	<!-- JavaScript -->
	<%@include file="../part/jscripts.jsp"%>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

	<!-- Page-Level Plugin Scripts - Tables -->
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- SB Admin Scripts - Include with every page -->
	<script src="js/sb-admin.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-posts').dataTable({
				"aoColumns" : [ null, null, null, {
					"bSortable" : false
				} ]
			});
		});
	</script>

</body>

</html>
