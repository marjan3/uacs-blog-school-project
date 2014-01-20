<!-- ${pageContext.request.servletPath} -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<%@include file="part/metas.jsp"%>

<title>About us</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Custom CSS for the 'Round About' Template -->
<link href="css/round-about.css" rel="stylesheet">

</head>

<body>

	<%@include file="part/header.jsp"%>

	<div class="container">

				<h1 class="page-header">
					About Us <small>It's Nice to Meet You!</small>
				</h1>
		<div class="row">

			<div class="col-md-9">
				<p>${about.description}
				</p>
			</div>
			<div class="well col-xs-6 col-md-3">
				<address>
					<strong>Tanevski, Inc.</strong><br> ${about.address}<br>
					<abbr title="Phone">P:</abbr>${about.phone} <abbr title="Fax">F:</abbr>${about.fax}
				</address>

				<address>
					<strong>Marjan Tanevski</strong><br> <a href="mailto:#">${about.email}</a>
				</address>
			</div>
			</div>
		<hr>

		<%@include file="part/footer.jsp"%>

	</div>
	<!-- /container -->

	<!-- JavaScript -->
	<%@include file="part/jscripts.jsp"%>
</body>

</html>
