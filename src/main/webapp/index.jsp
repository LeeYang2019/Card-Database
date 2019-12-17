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
            <div class="row">
                <div class="col mx-auto mb-3">
                    <p>This is a free to use website for uploading and tracking Yu-Gi-Oh! cards, as well as getting the most current and up-to-date pricing for cards.</p>
                    <p>To use, please signup.</p>
                </div>
                <div class="col-4 mx-auto mb-3">
                    <div class="card border-muted rounded-0">
                        <div class="card-header bg-muted">
                            <h4 class="text-dark font-italic font-weight-bold">Top 20 Selling Cards</h4>
                        </div>
                        <div class="card-body">
                            <FORM id="myForm" ACTION="signup" METHOD="POST">

                                <p>To create an account, enter a username and password.
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

                                <button type="submit" class="btn btn-primary" value="singup">Submit</button><br />
                            </FORM>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
