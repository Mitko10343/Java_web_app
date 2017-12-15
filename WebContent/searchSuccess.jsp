<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<style>
	tr{
		border:1px solid black;
	}
	
	td{
		border:1px solid black;
	}
	
</style>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Successfull</title>
</head>

<body>
	<br/><br/>
    <table style="border:1px solid black;">
		<th>Game Id </th>
		<th>Game Name</th>
		<th>Game Rating </th>
		<th>Game Review </th>
		<th>Rate Game </th>
		<c:forEach var="games" items="${sessionScope.results}">
		
		   <tr>
			   <td><c:out value="${games.id}" /></td>
			   <td><c:out value="${games.name}" /></td>
			   <td><c:out value="${games.rating}" /></td>
			   <td><c:out value="${games.review}" /></td>
			   
			   <c:set var="name" value="${games.name}"/>
			   <td>
			  	  <form action="Controller" method="Post">
			  	   <input type="text" name="rating"/>
			  	   <input type="hidden" name="gameName" value="${games.name}"/>
				   <input type="hidden" name="action1" value="Games"/>
				   <input type="hidden" name="action" value="AddRating"/>
				   <input type="submit" value="AddRating"/>
				   </form>
			   </td>
			 
		   </tr>
		  
		</c:forEach>
	</table>
	<br/><br/>
</body>

</html>