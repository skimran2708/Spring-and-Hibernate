<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

<head>
	<h2>Customer Confirmation</h2>
</head>

<body>

The customer is confirmed: ${customer.firstName} ${customer.lastName}
<br><br>

Free passes: ${customer.freePasses}
<br><br>

Postal Code: ${customer.postalCode}

</body>

</html>









