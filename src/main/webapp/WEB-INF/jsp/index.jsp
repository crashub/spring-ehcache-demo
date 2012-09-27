<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ page import="fr.dr.sandbox.controller.Customer"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8" />
<link rel="stylesheet" href="./resources/css/design.css" />
<script src="./resources/js/script.js"></script>
<title>Crash Demo Application</title>
</head>
<body>
	<section>
		<header>
			<h2>Introduction</h2>
		</header>
		<article>
			<p>Hello ${name} This is the main page of this demo application.
				You can show datas available in cache ,add Customer and
				remove all datas. The goal of this demo application is to
				test Crash.</p>
		</article>
	</section>
	<section id="main">
		<header>
			<h2>Manage Customer in cache</h2>
		</header>
		<article>
			<table>
				<tr>
					<td><a href="${pageContext.request.contextPath}/add">Add a customer</a></td>
					<td><a href="${pageContext.request.contextPath}/clearCache">Clear
							customer cache</a></td>
				</tr>
			</table>

			<table>
				<tr>
					<caption style="caption-side: bottom">Customers in cache</caption>
				<thead>
					<th>Id</th>
					<th>Name</th>
					<th>Address</th>
				</thead>
				</tr>
				<%
					List<Customer> lst = (List<Customer>) request
							.getAttribute("customers");
					if (null != lst) {
						for (Customer c : lst) {
							out.println("<tr>");

							out.println("<td>");
							out.println(c.getId());
							out.println("</td>");
							out.println("<td>");
							out.println(c.getName());
							out.println("</td>");
							out.println("<td>");
							out.println(c.getAddress());
							out.println("</td>");

							out.println("</tr>");
						}
					}
				%>
			</table>
		</article>
	</section>
	<section id="howto">
		<header>
			<h2>How to test Crash with this application ?</h2>
		</header>	
		<article>
			<p>Here is the step you have to do for connecting Crash to this demo application.</p>
			<ol>
				<li>Connect crash on this JVM</li>
					<p id="txt">
					%telnet 5000 localhost<br/>
					%ssh -p 2000 -l admin localhost (mdp admin)<br/>

					$JDK_HOME/bin/jps and get the id. <br/>
					%cd $CRASH_HOME/bin<br/>
					%./crash.sh ID<br/></p>
				<li>Execute the following command : <p id="txt">% spring_cache showcache</p></li>
				<li>Add a Customer with this web interface : <a href="${pageContext.request.contextPath}/add">Add a customer</a></li>
				<li>Execute the spring_cache command again : <p id="txt">spring_cache showcache</p></li>
			</ol>
		</article>
	</section>	
</body>
</html>
