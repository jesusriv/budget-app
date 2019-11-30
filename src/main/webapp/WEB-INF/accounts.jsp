<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<!DOCTYPE html>
<html>
	<head>
		<title>Budget</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<link rel="stylesheet" type="text/css" href="../css/accounts.css">
	</head>
	<body>		
	    <main>
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
	    				<p>$${thousand.format(budgetTotal)}</p>
	    			</div>
		    		<ul class='banks'>
		    			<c:forEach items="${banks}" var="bA">
	    					<div class="bank">
	    						<p><c:out value="${bA.bankName}"/></p>
		    					<c:choose>
				    				<c:when test="${bA.balance > 999}">
					    				<p><c:out value="$${thousand.format(bA.balance)}"/></p>
					    			</c:when>
					    			<c:otherwise>
					    				<p><c:out value="$${formatter.format(bA.balance)}"/></p>
					    			</c:otherwise>
				    			</c:choose>
	    					</div>
		    			</c:forEach>
		    		</ul> 
	    		</div>
	    	</aside>
	    	<section>
	    		<header>
	    			<!-- Available to Budget is total sum of money minus amount already budgeted -->
	    			<c:choose>
	    				<c:when test="${available > 999}">
		    				<p>Available to Budget: <span>$${thousand.format(available)}</span></p>
		    			</c:when>
		    			<c:when test="${available < 1}">
		    				<p>Available to Budget: <span>$0.00</span></p>
		    			</c:when>
		    			<c:otherwise>
		    				<p>Available to Budget: <span>$${formatter.format(available)}</span></p>
		    			</c:otherwise>
	    			</c:choose>
	    			
	    		</header>
	    		<div class="transactions">
	    			<ul>
	    				<li>Date</li>
	    				<li>Payee</li>
	    				<li>Category</li>
	    				<li>Amount</li>
	    			</ul>
					<form:form action="/api/bankaccounts/newTransaction/${account.id}" modelAttribute="transaction" method="POST">
						<form:input type="text" path="date" pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" placeholder="11/29/2019"/>
						<form:input type="text" path="payee" placeholder="E.g. Verizon"/>
						<form:select path="subcategory">
							<c:forEach items="${budget.subCategories}" var="sub">
								<form:option value="${sub.id}">${sub.category.name}: ${sub.name}</form:option>
							</c:forEach>
						</form:select>
						<form:input type="number" step="any" path="amount" placeholder="$0.00"/>
						<input type="submit" value="Save"/>
					</form:form>
   					<c:forEach items="${transactions}" var="t">
 						<form:form action="/api/bankaccounts/newTransaction/${account.id}" modelAttribute="transaction" method="POST">
							<form:input type="text" path="date" pattern="(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\d\d" value="${t.date}"/>
							<form:input type="text" path="payee" value="${t.payee}"/>
							<form:select path="subcategory" >
								<form:option value="${t.subcategory.id}" >${t.subcategory.category.name}: ${t.subcategory.name}</form:option>
								<c:forEach items="${budget.subCategories}" var="sub">
									<form:option value="${sub.id}">${sub.category.name}: ${sub.name}</form:option>
								</c:forEach>
							</form:select>
							<form:input type="text" path="amount" value="$${formatter.format(t.amount)}"/>
							<input type="submit" value="Save"/>
						</form:form>
	    			</c:forEach>
	    		</div>
	    	</section>
	    </main>
	</body>
</html>