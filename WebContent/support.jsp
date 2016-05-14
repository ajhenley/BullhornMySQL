<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BullHorn</title>
<jsp:include page="bootstrap.jsp"></jsp:include>
</head>
<body background="images/background_1280.jpg">
This is the support page
<jsp:include page="navbar.jsp"></jsp:include>

<div class="alert alert-success">
  <strong>Profile</strong><br>
   ${message}
</div>

<div class="alert alert-success">
  <strong>Posts</strong><br>
   ${message2}
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>