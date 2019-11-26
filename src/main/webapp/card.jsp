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
                <c:choose>
                    <c:when test="${!empty(card)}">
                        <div class="row ml-4">
                            <h1>${card.cardName}</h1>
                        </div>
                        <div class="row">
                            <div class="col-3 border ml-4">
                                Picture to be entered
                            </div>
                            <div class="col-3">
                                <table id="myTable">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <dl>
                                                    <dt>Type:</dt>
                                                    <dd>${card.cardType}</dd>
                                                    <dt>Rarity:</dt>
                                                    <dd>${card.cardRarity}</dd>
                                                    <dt>Set:</dt>
                                                    <dd>${card.cardSet}-${card.cardIndex}</dd>
                                                    <dt>Price:</dt>
                                                    <dd>${card.price}</dd>
                                                    <dt>QTY:</dt>
                                                    <dd>${card.quantity}</dd>
                                                </dl>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <hr>
                            </div>

                            <div class="col-3 border mr-4">
                                Picture to be entered
                            </div>
                        </div>

                        <!--<table id="myTable" class="table table-hover">
                            <thead class="thead-dark">
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
                                    <td class="tbColumns">$ {card.cardName}</td>
                                    <td class="tbColumns">$ {card.cardType}</td>
                                    <td class="tbColumns">$ {card.cardRarity}</td>
                                    <td class="tbColumns">$ {card.cardSet}</td>
                                    <td class="tbColumns">$ {card.cardIndex}</td>
                                    <td class="tbColumns">$ {card.price}</td>
                                    <td class="tbColumns">$ {card.quantity}</td>
                                </tr>

                        </table> -->
                    </c:when>
                    <c:otherwise>
                        <p class="validations">This card does not exist</p>
                    </c:otherwise>
                </c:choose>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
