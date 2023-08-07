<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Customer</h1>
<form action="<%=request.getContextPath()%>/Customers">
    <input type="text" name="search-name" value="${searchname}">
    <br>
    <label>Sort</label>
    <%
        if(request.getAttribute("sort")!=null)
            System.out.println(request.getAttribute("sort"));
        if(request.getAttribute("sort")!=null)
            System.out.println(request.getAttribute("sort"));
    %>
    <select name="sort" >
        <option value="name" selected="<%=request.getAttribute("sort")!=null&&request.getAttribute("sort").equals("name")%>">Name</option>
        <option value="email" selected="<%=request.getAttribute("sort")!=null&&request.getAttribute("sort").equals("email")%>">Email</option>
        <option value="address" selected="<%=request.getAttribute("sort")!=null&&request.getAttribute("sort").equals("address")%>">Address</option>
    </select>
    <br>
    <label>By</label>
    <select name="by" >
        <option value="ASC">Tăng Dần</option>
        <option value="DESC">Giảm Dần</option>
    </select>
    <br>
    <input type="submit" value="SEARCH" name="action">
</form>
<a href="<%=request.getContextPath()%>/Customers?action=CREATE">Add</a>
<table border="10" cellpadding="20" cellspacing="10">
    <thead>
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="c" varStatus="item">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td>${c.email}</td>
            <td>${c.address}</td>
            <td><a href="<%=request.getContextPath()%>/Customers?action=EDIT&id=${c.id}">Edit</a></td>
            <td><a onclick="return confirm('do you want to delete this item ? ')" href="<%=request.getContextPath()%>/Customers?action=DELETE&id=${c.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>