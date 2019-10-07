<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp"/>

<html>
    <body>
        <div class="container">
            <%@include file="header.jsp"%>
            <div class="row">
                <c:import url="header.jsp"/>
            </div>
            <div class="row">
                    <c:choose>
                        <c:when test="${!empty(cards)}">
                            <table class="table table-dark table-hover">
                                <thead>
                                    <tr>
                                        <th>Card Name</th>
                                        <th>Card Type</th>
                                        <th>Card Rarity</th>
                                        <th>Card Set</th>
                                        <th>Card Price</th>
                                    </tr>
                                </thead>
                                <c:forEach var="card" items="${cards}">
                                    <tr>
                                        <td class="tbColumns">${card.cardName}</td>
                                        <td class="tbColumns">${card.cardType}</td>
                                        <td class="tbColumns">${card.cardRarity}</td>
                                        <td class="tbColumns">${card.cardSet}</td>
                                        <td class="tbColumns">${card.cardPrice}</td>
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


