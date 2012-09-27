<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="fr.dr.sandbox.controller.Customer"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="./resources/css/design.css" />
<script src="./resources/js/script.js"></script>

<title>Add a customer</title>
</head>
<body>
	<section>
		<article>
			<header>
				<h2>Add a customer</h2>
			</header>
			<form:form modelAttribute="premierFormulaire" method="POST"
				action="${pageContext.request.contextPath}/save">
				<table>
					<tr>
						<td>Id:</td>
						<td><input type="text" name="id"></td>
					</tr>
					<tr>
						<td>Name:</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>Address:</td>
						<td><input type="text" name="address" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Submit" /></td>
					</tr>

				</table>
			</form:form>
		</article>
	</section>
</body>