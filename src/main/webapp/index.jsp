<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<%@include file="head.jsp"%>

    <body>
        <div class="container">
            <c:import url="header.jsp"/>
            <div class="row">
                <c:import url="navbar.jsp"/>
            </div>
            <div class="row">
                <a href = "searchCards"> Return all cards</a>
            </div>
            <div class="row">
                <a href = "search.jsp"> Go to card search</a>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
