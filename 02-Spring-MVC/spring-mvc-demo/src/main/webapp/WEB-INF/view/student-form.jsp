<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <h2> Student Registration Form </h2>
</head>

<body>

    <form:form action="processForm" modelAttribute="student">
    First Name: <form:input path="firstName"/>
    <br><br>
    Last Name: <form:input path="lastName"/>
    <br><br>

    Country:
    <form:select path="country">

        <!--- <form:option value="Brazil" label="Brazil"/>
        <form:option value="France" label="France"/>
        <form:option value="Germany" label="Germany"/>
        <form:option value="India" label="India"/> --->

        <form:options items="${student.countryOptions}"/>

    </form:select>
    <br><br>

    Favourite Language:
    <form:radiobutton path="favouriteLanguage" value="JAVA"/> Java
    <form:radiobutton path="favouriteLanguage" value="C#"/> C#
    <form:radiobutton path="favouriteLanguage" value="php"/> php
    <form:radiobutton path="favouriteLanguage" value="Ruby"/> Ruby
    <br><br>

    Operating Systems:
    <form:checkbox path="operatingSystems" value="MacOS"/> MacOS
    <form:checkbox path="operatingSystems" value="MS Windows"/> Windows
    <form:checkbox path="operatingSystems" value="Linux"/> Linux
    <br><br>

    <input type="submit" value="Submit"/>
    </form:form>

</body>

</html>