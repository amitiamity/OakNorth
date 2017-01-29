<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"></link>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.min.css"></link>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap-responsive.min.css"></link>
<link rel="stylesheet" type="text/css" href="css/oaknorth.css"></link>
<script type="text/javascript" src="js/oaknorth.js"></script>

<title>OakNorth</title>
</head>
<body>

	<div class="container">

		<header>
		<h2>OakNorth</h2>
		</header>

		<div class="main">
			<div class="searchbar">
				<form class="form-inline" action="search" method="get">
					<div class="form-group">
						<input type="text" class="form-control" id="query" name="q"
							placeholder="Enter the Company Name" value="${searchedString}" required/>
						<button type="submit" class="btn btn-default" id="btnsubmit">Submit</button>
					</div>
				</form>
			</div>
			<c:set var="counter" value="1" />
			<div class="result">
			<c:if test="${empty items && !empty searchedString }">
				<div class="alert alert-info">
  						 No result found with the name <strong>${searchedString}</strong>
				</div>
			</c:if>
				<c:forEach items="${items}" var="map">
					<h4>
						Company Name : <span style="color: red;"> <c:out
								value="${map.key}" />
						</span>
					</h4>
					<hr/>
					<table class="table table-striped"
						style="margin-left: 100px; width: 100px;">
						<thead>
							<tr>
								<th>#</th>
								<th>Officer Id/Name</th>
							</tr>
						</thead>
						<c:forEach items="${map.value}" var="list">
							<c:forEach items="${list}" var="officerid">
								<tr>
									<td><c:out value="${counter}" />
									</td>
									<td>${officerid}</td>
								</tr>
								<c:set var="counter" value="${counter+1}" />
							</c:forEach>

						</c:forEach>

					</table>
					<c:set var="counter" value="1" />
				</c:forEach>

			</div>
		</div>
        <hr/>
		<footer> POC Developed by Amit Patel (9971246904) </footer>


	</div>
</body>
</html>