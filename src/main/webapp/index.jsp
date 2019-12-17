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
            <div class="row">
                <div class="col-12 mx-auto mb-3">
                    <div class="card border-muted rounded-0">
                        <div class="card-header bg-muted">
                            <h4 class="text-dark font-italic font-weight-bold">Top 20 Selling Cards</h4>
                        </div>
                        <div class="card-body">
                            <c:choose>
                                <c:when test="${!empty(cards)}">
                                    <table id="myTable" class="table table-hover mx-auto">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th>Name</th>
                                            <th>Type</th>
                                            <th>Rarity</th>
                                            <th>Set</th>
                                            <th>Price</th>
                                        </tr>
                                        </thead>
                                        <c:forEach var="card" items="${cards}">
                                            <tr>
                                                <td class="tbColumns"><a href="displayCard?param=${card.id}">${card.cardName}</a></td>
                                                <td class="tbColumns">${card.cardType}</td>
                                                <td class="tbColumns">${card.cardRarity}</td>
                                                <td class="tbColumns">${card.cardSet}-${card.cardIndex}</td>
                                                <td class="tbColumns">${card.price}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <p class="validations">No cards were found</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
