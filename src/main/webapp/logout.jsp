<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
    <div class="container">
        <div class="row">
            <c:import url="header.jsp"/>
        </div>
        <div class="row">
            <c:import url="navbar.jsp"/>
        </div>
        <div class="row">
            <p>You are now logged out. Goodbye!</p>
        </div>
        <footer class="row">
            <c:import url="footer.jsp"/>
        </footer>
    </div>
</body>
</html>
