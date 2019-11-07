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

                    <select id="searchType" class="dropdown-menu" name="searchType">
                        <option class="dropdown-item" value="All">All</option>
                        <option class="dropdown-item" value="Monster">Monster</option>
                        <option class="dropdown-item" value="Spell">Spell</option>
                        <option class="dropdown-item" value="Trap">Trap</option>
                    </select>

                    <input type="searchTerm" class="form-control" id="searchTerm" placeholder="Search Card" name="searchTerm" aria-label="card" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </form>

            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
