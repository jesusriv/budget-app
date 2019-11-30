<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
	<head>
		<title>Budget App</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	
	<body class="login-body">
    	<p><c:out value="${error}"/></p>
    	<form class="hide" id="login" method="POST" action="/login">
    		<p class='title'>Login</p>
	        <p class="form-group">
	            <label for="email">Email</label>
	            <input class="form-control" type="text" id="email" name="email"/>
	        </p>
	        <p class="form-group">
	            <label for="password">Password</label>
	            <input class="form-control" type="password" id="password" name="password"/>
	        </p>
	        <input class="btn btn-primary" type="submit" value="Login"/>
	        <p onclick="register()">Don't have an account? <span >Register here</span></p>
	    </form>
		
		<form:form action="/api/create/user" id="register" modelAttribute="user">	
			<p class='title'>Register</p>
			<p class="form-group">
				<form:label path="name">Name:</form:label>
				<form:errors/>
				<form:input class="form-control" path="name"/>
			</p>
			
			<p class="form-group">
				<form:label path="email">Email:</form:label>
				<form:errors/>
				<form:input class="form-control" path="email" type="email"/>
			</p>
			
			<p class="form-group">
				<form:label path="password">Password:</form:label>
				<form:errors/>
				<form:input class="form-control" path="password" type="password"/>
			</p>
	
			<p>
				<form:label path="confirmPassword">Confirm Password:</form:label>
				<form:errors/>
				<form:input class="form-control" path="confirmPassword" type="password"/>
			</p>
			<input class="btn  btn-primary" type="submit" value="Create User"/>
			<p onclick="login()">Have an account already? <span >Log in here</span></p>
		</form:form>
		<script>
			function login() {
				document.getElementById("login").classList.add("show");
				document.getElementById("login").classList.remove("hide");
				document.getElementById("register").classList.add("hide");
			}
			function register() {
				document.getElementById("register").classList.add("show");
				document.getElementById("register").classList.remove("hide");
				document.getElementById("login").classList.add("hide");
			}
		</script>
	</body>
</html>