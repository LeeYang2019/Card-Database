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
            <div class="col-10 mx-auto">
                <div class="card border-dark mb-3">
                    <div class="card-header bg-dark">
                        <h4 class="text-white">Card Search</h4>
                    </div>
                    <div class="card-body">
                        <form class="form-group" action="searchCards" method="GET" class="needs-validation" novalidate>

                            <div class="form-group">
                                <input type="searchTerm" class="form-control" id="searchTerm" placeholder="Search Card" name="searchTerm" aria-label="card" aria-describedby="basic-addon2">
                            </div>

                            <div class="form-group">
                                <select class="form-control" id="searchType" name="searchType">
                                    <option selected>All Cards</option>
                                    <option value="monster">Monster</option>
                                    <option value="spell">Spell</option>
                                    <option value="trap">Trap</option>
                                </select>
                            </div>

                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
