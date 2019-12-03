<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <c:import url="head.jsp"/>
    <body>
        <div class="container">
            <div class="row">
                <c:import url="header.jsp"/>
            </div>
            <div class="row">
                <c:import url="navbar.jsp"/>
            </div>
            <div class="col-6 mx-auto mb-3">
                <div class="card border-info">
                    <div class="card-header bg-info">
                        <h4 class="text-white">Login</h4>
                    </div>
                    <div class="card-body">
                        <FORM id="myForm" ACTION="j_security_check" METHOD="POST">

                            <p>To create an account, enter a username and password. Afterwards, you will be navigated back to the login page
                                and then prompted to provide your newly create username and password to login.
                            </p>

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

                            <div class="form-group">
                                <label for="pwd">Re-Type Password:</label>
                                <input type="password" class="form-control" id="pwd2" placeholder="Enter password" name="j_password" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>

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
