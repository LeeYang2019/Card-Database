<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
    <div class="container">
        <div class="row">
            <c:import url="menu.jsp"/>
        </div>
        <div class="col-6 mx-auto mb-3">
            <div class="card border-primary">
                <div class="card-header bg-primary">
                    <h4 class="text-white">Login</h4>
                </div>
                <div class="card-body">
                    <FORM id="myForm" ACTION="j_security_check" METHOD="POST">

                        <h4>Authentication Failed. Please try logging in again.
                        </h4>

                        <div class="form-group">
                            <label for="j_username">Username:</label>
                            <input type="text" class="form-control" id="j_username" placeholder="Enter username" name="j_username" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>

                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="j_password" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>

                        <p><a href="signup.jsp">I do not have an account.</a></p>
                        <button type="submit" class="btn btn-primary" value="log in">Submit</button><br />
                    </FORM>
                </div>
            </div>
        </div>
        <footer class="row">
            <c:import url="footer.jsp"/>
        </footer>
    </div>
</body>
</html>
