<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <c:import url="head.jsp"/>
    <body>
        <div class="container">
            <div class="row">
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('general')}" >
                        <c:import url="/userMenu.jsp" />
                    </c:when>
                    <c:otherwise>
                        <c:import url="menu.jsp"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-6 mx-auto mb-3">
                <div class="card border-info">
                    <div class="card-header bg-info">
                        <h4 class="text-white">Login</h4>
                    </div>
                    <div class="card-body">
                        <FORM id="myForm" ACTION="signup" METHOD="POST">

                            <p>To create an account, enter a username and password. Afterwards, you will be navigated back to the login page
                                and then prompted to provide your newly create username and password to login.
                            </p>

                            <div class="form-group">
                                <label for="username">Username:</label>
                                <input type="text" class="form-control" id="username" placeholder="Enter username" name="username"
                                       data-error="Please enter a username." required>
                                <div class="help-block with-errors"></div>
                                <!--<div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>-->
                            </div>

                            <div class="form-group">
                                <label for="password">Password:</label>
                                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password"
                                       data-error="Please provide a password." required>
                                <div class="help-block with-errors"></div>
                            </div>

                            <div class="form-group">
                                <label for="confirmPwd">Re-Type Password:</label>
                                <input type="password" class="form-control" id="confirmPwd" placeholder="Enter password" name="confirmPwd"
                                       equalTo="#password"
                                       data-match="#password"
                                       data-match-error="Confirmation password does not match"
                                       required data-error="Please retype your password.">
                                <div class="help-block with-errors"></div>
                            </div>


                            <button type="submit" class="btn btn-primary btn-block" value="log in">Signup</button><br />
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
