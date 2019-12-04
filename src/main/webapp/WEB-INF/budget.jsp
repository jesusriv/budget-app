<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    

<!DOCTYPE html>
<html>
	<head>
		<title>Budget</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/main.css">
	</head>
	<body>		
	    <main>
	    	<div id="modal">
    			<form:form action="/api/create/bankaccount/${user.id}/${budget.id}" method="POST" modelAttribute="bankAccount">
    				<p class='title'>Add A Bank Account</p>
    				<p class='form-group'>
    					<form:label path="bankName">Bank Name</form:label>
    					<form:input class="form-control" type="text" path="bankName"/>
    				</p>
    				<p class='form-group'>
    					<form:label path="balance">Balance</form:label>
    					<form:input class="form-control" type="number" step="any" path="balance" />
    				</p>
    				<p class='form-group'>
    					<form:label path="typeOfAccount">Type of Account</form:label>
    					<form:input class="form-control" type="text" path="typeOfAccount"/>
    				</p>
    				<form:input type="hidden" path="user" value="${user.id}"/>
    				<input class="btn btn-primary" type="submit" value="Add Account"/>
    			</form:form>
    		</div>
	    	<aside>
				<div class="aside-header">
		    		<h1><c:out value="${budget.name}"/></h1>
		    		<h5><c:out value="${user.email}"/></h5>
			    </div>
	    		<ul class='aside bottom'>
	    			<li><a href="/budget" class="buttons-nav selected">
	    				<svg id="ember1214" viewBox="0 0 16 16" width="20" height="20" class="ember-view svg-icon budget"><path fill-rule="evenodd" d="M 10.6934 8.40459C 10.2736 8.69852 9.72653 8.69852 9.30665 8.40459L 0.556666 2.27959C 0.208912 2.03616 3.42321e-05 1.62635 4.09729e-05 1.1875L 4.09729e-05 1.3125C 4.09729e-05 0.587626 0.559685 0 1.25004 0L 18.75 0C 19.4404 0 20 0.587626 20 1.3125L 20 1.1875C 20.0001 1.62635 19.7912 2.03619 19.4434 2.27959L 10.6934 8.40459ZM 20 12.6875C 20 13.4124 19.4403 14 18.75 14L 1.25 14C 0.559643 14 1.81067e-10 13.4124 1.81067e-10 12.6875L 1.81067e-10 4.90875C -4.96278e-06 4.71424 0.102015 4.53555 0.265397 4.44388C 0.428779 4.35221 0.626963 4.36248 0.780956 4.47059L 8.8444 10.1326C 9.54417 10.6224 10.4558 10.6224 11.1556 10.1326L 19.2189 4.47046C 19.3729 4.36233 19.5711 4.35205 19.7345 4.4437C 19.8979 4.53536 19.9999 4.71406 19.9999 4.90858L 20 12.6875Z" transform="scale(.45) translate(5.75 10)"></path>
</svg>
	    				<p>Budget</p>
	    			</a></li>
	    		</ul>
	    		<div class="accounts">
	    			<div class="accounts-budget">
	    				<h4>Budget</h4>
	    				<p>$${budgetTotal}</p>
	    			</div>
		    		<ul class='banks'>
		    			<c:forEach items="${banks}" var="bA">
	    					<div>
	    						<a href="/bankaccount/${bA.id}" class="bank">
	    						<p><c:out value="${bA.bankName}"/></p>
					    		<p><c:out value="$${formatter.format(bA.balance)}"/></p>
	    						</a>
	    					</div>
		    			</c:forEach>
		    		</ul> 
		    		<p class="addAccount" onclick="createBankAccount()">Add an account</p>
		    		
	    		</div>
	    	</aside>
	    	<section>
	    		<header>
		    		<p>Available to Budget: <span>$${available}</span></p>
	    		</header>
	    		<div>
		    		<c:forEach items="${categories}" var="c">
		    			<h2><c:out value="${c.name}"/></h2>
		    			<table class="table table-striped table-dark">
		    				<thead>
		    					<tr>
		    						<th scope="col" class="col-1">Category</th>
		    						<th scope="col">Budgeted</th>
		    						<th scope="col">Activity</th>
		    						<th scope="col">Available</th>
		    					</tr>
		    				</thead>
		    				<tbody>
				    			<c:forEach items="${sub}" var="s">
					    		<c:if test="${s.category.name == c.name}">
				    				<tr>
					    				<td class="col-1">
					    					<c:out value="${s.name}"></c:out>
					    				</td>
					    				<td>
					    					<form class="budgeted-form" method="POST" action="/api/subcategory/update/budgeted/${s.id}">
					    						<input type="number" name="amount" step="any" placeholder="$${formatter.format(s.budgeted)}"/>
					    					</form>
					    				</td>
					    				<td>
					    					<c:out value="$${formatter.format(s.activity)}"></c:out>
					    				</td>
					    				<td>
					    					<c:out value="$${formatter.format(s.available)}"></c:out>
					    				</td>
					    			</tr>
					    		</c:if>
					    		</c:forEach>
					    	</tbody>
			    		</table>
		    		</c:forEach>
		    	</div>
	    	</section>
	    </main>
	    <script>
			function createBankAccount() {
				document.getElementById("modal").classList.add("show");
				document.querySelector('body').classList.add('stop-scrolling');
			}
	    </script>
	</body>
</html>