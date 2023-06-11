<%--
  Created by IntelliJ IDEA.
  User: sulta
  Date: 6/8/2023
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Welcome To MyVoc</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/styles.css"/>">
  </head>
  <body>
    <div class="container-header">
      <h1 class="container-text">Welcome To MyVoc!</h1>
    </div>
    <div class="container-button" onclick="${pageContext.request.contextPath}/groups">
      <h2 class="button-text">
        <a href="${pageContext.request.contextPath}/groups" style="color: white;">Word Groups</a>
      </h2>
    </div>
  </body>
</html>
