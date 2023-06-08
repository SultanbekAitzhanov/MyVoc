<%--
  Created by IntelliJ IDEA.
  User: sulta
  Date: 6/8/2023
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach items="${integerList}" var="groupNumber">
        <li id="group_<c:out value="${groupNumber}"/>">
            <a href="<c:url value="/groups/${groupNumber}"/>">
                GROUP # ${groupNumber}
            </a>
        </li>
    </c:forEach>
</body>
</html>
