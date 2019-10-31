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

                    <form class="form-check-inline" action="searchCards" method="GET" class="needs-validation" novalidate>

                        <div id="mySearch" class="input-group mb-3">
                            <input type="searchTerm" class="form-control" id="searchTerm" placeholder="Search Card" name="searchTerm" aria-label="card" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </div>

<!--
                        <div class="form-group">
                            <input type="searchTerm" class="form-control" id="searchTerm" placeholder="Enter a card" name="searchTerm" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>

                        <div class="form-check-inline">
                            <label class="form-check-label" >
                                <input type="radio" class="form-check-input" id="home" name="searchType" value="cardName" checked="checked">Card Name
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label" >
                                <input type="radio" class="form-check-input" id="cell" name="searchType" value="cardType">Card Type
                            </label>
                        </div>
                        <div class="form-check-inline">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" id="work" name="searchType" value="cardRarity">Card Rarity
                            </label>
                        </div><br/>

                        <button type="submit" class="btn btn-primary">Submit</button> -->
                    </form><br/>

            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
