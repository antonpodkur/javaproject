<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Subscription List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Subscription List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Date Start</th>
        <th>Date End</th>
    </tr>
    <c:forEach items="${subscriptionList}" var="subscription" >
        <tr>
            <td>${subscription.name}</td>
            <td>${subscription.price}</td>
            <td>${subscription.date_start}</td>
            <td>${subscription.date_end}</td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>