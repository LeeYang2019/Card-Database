<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <c:import url="head.jsp"/>
    <body>
        <div class="container">
            <div class="row">
                <c:import url="header.jsp"/>
            </div>
            <div class="row">
                <c:import url="menu.jsp"/>
            </div>
            <div class="row">
                <div class="col mx-auto mb-3">
                    <div class="col mx-auto">
                        <p>This is a free to use website for managing one's Yu-Gi-Oh! card collection, as well as getting the most current and up-to-date pricing for cards.</p>
                        <p>To use, please sign-up. Otherwise, login if you already have an account.</p>
                    </div>
                    <div class="col mx-auto">
                        <img id="yugioh_image" src="images/yugioh.jpg" alt="Yu-Gi-OhCard"/>
                    </div>
                </div>
                <div class="col-4 mx-auto mb-3">
                    <div class="card border-muted rounded-0">
                        <div class="card-header bg-muted">
                            <h4 class="text-dark font-weight-bold">Signup</h4>
                        </div>
                        <div class="card-body">
                            <FORM id="myForm" ACTION="signup" METHOD="POST" oninput='up2.setCustomValidity(confirmPwd.value != password.value ? "Passwords do not match." : "")'>
                                <p>To create an account, enter a username and password. Afterwards, you will be navigated back to the login page
                                    and then prompted to provide your newly create username and password to login.
                                </p>

                                <div class="form-group">
                                    <label for="username">Username:</label>
                                    <input type="text" class="form-control" id="username" placeholder="Enter username" name="username"
                                           data-error="Please enter a username." required>
                                    <div class="help-block with-errors"></div>
                                </div>

                                <div class="form-group">
                                    <label for="password">Password:</label>
                                    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password"
                                           data-error="Please provide a password."
                                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                           title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required>
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

                                <button type="submit" class="btn btn-primary btn-block" value="Signup">Signup</button><br />
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
