<%@ page import = "javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>

<body>

Hello World of Spring!

<br><br>

<!--- Student name: <%=request.getParameter("studentName")%> --->
Student name: ${param.studentName}

<br><br>

Message: ${message}

</body>

</html>





