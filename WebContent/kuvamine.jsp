<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Piiripunktid</title>
</head>
<body>

<%
String nimi=request.getParameter("nimi");
String aadress=request.getParameter("aadress");
String number=request.getParameter("number");
if(nimi != null && !nimi.isEmpty() &&aadress != null && !aadress.isEmpty()&&number != null && !number.isEmpty()){
%>
<table>
<tr>
<th>
Nimi
</th>
<th>
Aadress
</th>
<th>
Piirivalvurite hulk
</th>
</tr>
<tr>
<td><%=nimi %></td>
<td><%= aadress %></td>
<td><%= number %></td>

</tr>
</table>
<% } else{
	%>
	Palun sisestage vaartused
	<%
} %>



</body>
</html>


