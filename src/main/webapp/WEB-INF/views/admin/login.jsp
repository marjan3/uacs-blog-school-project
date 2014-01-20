<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	
	<%@include file="../part/metas.jsp"%>

    <title>Administration login</title>

    <!-- Core CSS - Include with every page -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- SB Admin CSS - Include with every page -->
    <link href="css/sb-admin.css" rel="stylesheet">

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Please Sign In</h3>
                    </div>
                    <div class="panel-body">
                    <form:form role="form" modelAttribute="user">
                    <fieldset>
                                <div class="form-group">
                                    <form:input path="email" cssClass="form-control" placeholder="E-mail" name="email" type="email" autofocus="autofocus" required="true" maxlength="35"></form:input>
                                    <div class="form-group has-error">
                                            <label class="control-label">
                                    <form:errors path="email" /></label>
                                            
                                        </div>
                                </div>
                                <div class="form-group">
                                    <form:input path="password" class="form-control" placeholder="Password" name="password" type="password" value="" required="true" maxlength="15"></form:input>
                                    <div class="form-group has-error">
                                            <label class="control-label">
                                    <form:errors path="password" /></label>
                                            
                                        </div>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button name="submit" type="submit" class="btn btn-lg btn-success btn-block">Login</button>
                            </fieldset> 
                    </form:form>
                    </div>
                </div>
                    <c:if test="${not empty loginMsg}">
                    <div class="alert alert-danger alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                ${loginMsg}
                            </div>
                    </c:if>
            </div>
        </div>
        <%@include file="../part/footer.jsp" %>
    </div>

    <!-- Core Scripts - Include with every page -->
      <!-- JavaScript -->
    <%@include file="../part/jscripts.jsp" %>
    
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- SB Admin Scripts - Include with every page -->
    <script src="js/sb-admin.js"></script>

</body>

</html>
