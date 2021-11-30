<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>

<!DOCTYPE html>
<html>
<head>
    <title> Student Confirmation Form </title>
</head>

<body>
The student is confirmed : ${student.firstName} ${student.lastName}
<br><br>
Country : ${student.country}                              <!---Student.getCountry is called--->
<br><br>
Favourite Language : ${student.favouriteLanguage}         <!---Student.getFavouriteLanguage is called--->
<br><br>
Operating Systems:
<ul>
    <c:forEach var="temp" items="${student.operatingSystems}">
    <li> ${temp} </li>
    </c:forEach>
</ul>
<br><br>

</body>
</html>