<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp"/>
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
                <div class="row">

                    <form action="searchCards" method="GET" class="needs-validation" novalidate>

                        <div class="form-group">
                            <label for="searchTerm">Please enter a search word:</label>
                            <input type="text" class="form-control" id="searchTerm" name="searchTerm">
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
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <button type="reset" class="btn btn-danger">Cancel</button>
                    </form>
                </div>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
