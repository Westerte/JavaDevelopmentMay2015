<%@ page isErrorPage="true" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Error Page ${pageContext.errorData.statusCode}</title>
</head>
<body>
	Request from ${pageContext.errorData.requestURI} is failed  
	<hr/>  
	<br/>  
	Servlet name: ${pageContext.errorData.servletName}  
	<hr/>  
	<br/>  
	Status code: ${pageContext.errorData.statusCode}  
	<hr/>  
	<br/>  
	Exception: ${pageContext.exception}  
	<hr/>  
	<br/>  
	Message from exception: ${pageContext.exception.message}
</body>
</html>