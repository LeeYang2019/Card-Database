<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp"/>
<html>
    <body>
    <div class="container">
        <div class="row">
            <header class="jumbotron">

            </header>
        </div>
        <div class="row">
            <c:import url="header.jsp"/>
        </div>
        <div class="row">
            <form action="searchUser" method="GET" class="needs-validation" novalidate>

                <div class="form-group">
                    <label for="pwd">Please Enter User Information:</label>
                    <input type="password" class="form-control" id="pwd" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
<!--
                <label for="user">Search Type:</label><br/>
                <div class="form-check-inline">
                    <label class="form-check-label" for="radio1">
                        <input type="radio" class="form-check-input" id="home" name="searchType" value="ID" checked="checked">ID
                    </label>
                </div>
                <div class="form-check-inline">
                    <label class="form-check-label" for="radio2">
                        <input type="radio" class="form-check-input" id="cell" name="searchType" value="firstName">First Name
                    </label>
                </div>
                <div class="form-check-inline">
                    <label class="form-check-label">
                        <input type="radio" class="form-check-input" id="work" name="searchType" value="lastName">Last Name
                    </label>
                </div>
                <br/>
                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="reset" class="btn btn-danger">Cancel</button> -->
            </form>

        </div>
        <footer class="row">
            User Database
        </footer>
    </div>
    </body>
</html>
