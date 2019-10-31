<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp"/>
<html>
    <body>
        <div class="container">
            <c:import url="header.jsp"/>
            <div class="row">
                <c:import url="navbar.jsp"/>
            </div>
            <div class="row">
                <form action="http://itins3.madisoncollege.edu/echo.php" class="needs-validation" novalidate>
                    <div class="form-group">
                        <label for="cardName">Name:</label>
                        <input type="cardName" class="form-control" id="cardName" placeholder="Enter Card Name" name="firstName" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>

                    <div class="input-group">
                        <input type="cardType" class="form-control" aria-label="Text input with dropdown button">
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" href="#">Common</a>
                                <a class="dropdown-item" href="#">Rare</a>
                                <a class="dropdown-item" href="#">Super</a>
                                <a class="dropdown-item" href="#">Ultra</a>
                                <a class="dropdown-item" href="#">Secret</a>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lastName">Type:</label>
                        <input type="lastName" class="form-control" id="lastName" placeholder="Enter Last Name" name="lastName" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label for="email">Set:</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter Email Address" name="email" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    <div class="form-group">
                        <label for="pwd">Phone Number:</label>
                        <input type="password" class="form-control" id="pwd" placeholder="(###) ###-####" name="pswd" required>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div><br />
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <button type="reset" class="btn btn-danger">Cancel</button>
                </form>
                <br/>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
