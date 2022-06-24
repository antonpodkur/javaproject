<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Edition List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Topic Id</th>
        <th></th>
    </tr>
    <c:forEach items="${editionList}" var="edition" >
        <tr>
            <td>${edition.name}</td>
            <td>${edition.price}</td>
            <td>${edition.topic_id}</td>
            <td>
                <a href="purchase?id=${edition.id}">Purchase</a>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>