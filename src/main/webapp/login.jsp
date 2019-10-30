<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="userOnly/head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
    <div class="container">
        <div class="row">
            <c:import url="userOnly/header.jsp"/>
        </div>
        <div class="row">
            <c:import url="userOnly/navbar.jsp"/>
        </div>
        <div class="row">
            <FORM ACTION="j_security_check" METHOD="POST">
                <TABLE>
                    <TR><TD>User name: <INPUT TYPE="TEXT" NAME="j_username">
                    <TR><TD>Password: <INPUT TYPE="PASSWORD" NAME="j_password">
                    <TR><TH><INPUT TYPE="SUBMIT" VALUE="Log In">
                </TABLE>
            </FORM>
        </div>
        <footer class="row">
            <c:import url="userOnly/footer.jsp"/>
        </footer>
    </div>
</body>
</html>
