<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">

<title>Administration panel</title>

<!-- Core CSS - Include with every page -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- Page-Level Plugin CSS - Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- SB Admin CSS - Include with every page -->
<link href="css/sb-admin.css" rel="stylesheet">
<script type="text/javascript">
	function submitLogout() {
		$("#logout").submit();
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
							</form>
							<a href="#" onclick="submitLogout()"><i
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
			<a style="width: 200px" class="btn btn-default btn-block" href="panel?submit=">Create
				new post</a> <br>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Advanced management table</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-posts">
									<thead>
										<tr>
											<th>Title</th>
											<th>Content</th>
											<th>Date</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="post" items="${posts}" varStatus="i">
											<c:choose>
												<c:when test="${i.index%2==0}">
													<c:set var="numb" value="even" />
												</c:when>
												<c:otherwise>
													<c:set var="numb" value="odd" />
												</c:otherwise>
											</c:choose>

											<tr class="${numb} gradeX">
												<td>${post.title}</td>
												<td><c:choose>
														<c:when test="${fn:length(post.content) > 30}">
															<p>${fn:substring(post.content,0,29)}...</p>
														</c:when>
														<c:otherwise>
															<p>${post.content}</p>
														</c:otherwise>
													</c:choose></td>
												<td>${post.postDate}</td>
												<td class="center"><div class="btn-group">
														<button type="button"
															class="btn btn-primary btn-sm dropdown-toggle"
															data-toggle="dropdown">
															<i class="fa fa-cog"></i> <span class="caret"></span>
														</button>
														<ul class="dropdown-menu" role="menu">
															<li><a href="panel?edit=${post._id}"><i
																	class="glyphicon glyphicon-edit"></i>&nbsp;Edit</a></li>
															<li><a href="panel?remove=${post._id}"><i
																	class="glyphicon glyphicon-remove"></i>&nbsp;Remove</a></li>
														</ul>
													</div></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->

						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
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
