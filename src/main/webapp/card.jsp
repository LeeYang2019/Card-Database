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
                <c:choose>
                    <c:when test="${!empty(card)}">
                        <table id="myTable" class="table table-dark table-hover">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Rarity</th>
                                <th>Set</th>
                                <th>No.</th>
                                <th>Price</th>
                                <th>Qty</th>
                            </tr>
                            </thead>

                                <tr>
                                    <td class="tbColumns">${card.cardName}</td>
                                    <td class="tbColumns">${card.cardType}</td>
                                    <td class="tbColumns">${card.cardRarity}</td>
                                    <td class="tbColumns">${card.cardSet}</td>
                                    <td class="tbColumns">${card.cardIndex}</td>
                                    <td class="tbColumns">${card.price}</td>
                                    <td class="tbColumns">${card.quantity}</td>
                                </tr>

                        </table>
                    </c:when>
                    <c:otherwise>
                        <p class="validations">This card does not exist</p>
                    </c:otherwise>
                </c:choose>



            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
