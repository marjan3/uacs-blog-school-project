<!-- ${pageContext.request.servletPath} -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<%@include file="part/metas.jsp"%>

<title>Contact us</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<style>
body {
	margin-top: 60px;
}
</style>

</head>

<body>

	<%@include file="part/header.jsp"%>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<h1>Enter your contact details below: </h1>
				<p>Here are the contact details for various services throughout. Complete with pre-defined file paths that you won't have to
					change!</p>
			</div>
		</div>
		<form class="form-horizontal" enctype="text/plain"
			action="mailto:marjantanevski@gmail.com" method="post">
			<fieldset>
				<div class="control-group">
					<label class="control-label" for="inputName">Name</label>
					<div class="controls">
						<input type="text" id="inputName" placeholder="Name"
							maxlength="45">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputEmail">Email</label>
					<div class="controls">
						<input type="email" id="inputEmail" placeholder="Email"
							maxlength="45">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPhone">Phone</label>
					<div class="controls">
						<input type="text" id="inputPhone" placeholder="+389XXXXX"
							maxlength="15">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputCompany">Company</label>
					<div class="controls">
						<input type="text" id="inputCompany" placeholder="Company"
							maxlength="45">
					</div>
				</div>
				<br> <label class="control-label" for="message">Message:
				</label> <br>
				<textarea rows="3" cols="35" name="message" id="message"
					maxlength="1000"></textarea>
				<br><br>
				<!-- Standard button -->
				<button type="button" class="btn btn-default">Send</button>
			</fieldset>
		</form>

		<%@include file="part/footer.jsp"%>

	</div>
	<!-- /.container -->

	<!-- JavaScript -->
	<%@include file="part/jscripts.jsp"%>

</body>

</html>
