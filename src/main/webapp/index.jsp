<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp"%>

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
