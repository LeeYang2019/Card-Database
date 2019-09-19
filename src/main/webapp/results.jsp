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
                    <c:choose>
                        <c:when test="${!empty(users)}">
                            <table class="table table-dark table-hover">
                                <thead>
                                    <tr>
                                        <th>UserID</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Age</th>
                                    </tr>
                                </thead>
                                <c:forEach var="individual" items="${users}">
                                    <tr>
                                        <td class="tbColumns">${individual.id}</td>
                                        <td class="tbColumns">${individual.firstName}</td>
                                        <td class="tbColumns">${individual.lastName}</td>
                                        <td class="tbColumns">${individual.age}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p>No users were found</p>
                        </c:otherwise>
                    </c:choose>
            </div>
            <footer class="row">
                User Database
            </footer>
        </div>
    </body>
</html>


