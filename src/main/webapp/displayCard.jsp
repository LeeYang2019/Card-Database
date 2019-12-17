<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <c:import url="head.jsp"/>
    <body>
        <div class="container">
            <div class="row">
                <c:import url="header.jsp"/>
            </div>
            <div class="row">
                <c:import url="userMenu.jsp"/>
            </div>
            <div class="row">
                <c:choose>
                    <c:when test="${!empty(card)}">
                        <div class="col-12 mx-auto mb-3">
                            <div class="card rounded-0">
                                <div class="card-header bg-muted">
                                    <h4 class="text-dark font-weight-bold">Yu-Gi-Oh! Card</h4>
                                </div>
                                <div class="card-body">
                                    <div class="row">
                                        <h2 class="text-dark font-weight-bold ml-3">${card.cardName}</h2>
                                    </div>
                                    <div class="row">
                                        <div class="col-8">
                                            <div class="row">
                                                <h6 class="font-italic ml-3">SetName: ${card.setName}</h6>
                                            </div>
                                            <div class="row">
                                                <div class="col">
                                                    <img id="imageSize" src="${card.image}">
                                                </div>
                                                <div class="col">
                                                    <table id="myTable">
                                                        <tbody>
                                                        <tr>
                                                            <td>
                                                                <dl>
                                                                    <dt>Type:</dt>
                                                                    <dd>${card.cardType}</dd>
                                                                    <dt>Rarity:</dt>
                                                                    <dd>${card.cardRarity}</dd>
                                                                    <dt>Edition:</dt>
                                                                    <dd>${card.cardEdition}</dd>
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
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="card border-muted rounded-0">
                                                <div class="card-header bg-muted">
                                                    <h4 class="text-dark font-weight-bold">Action</h4>
                                                </div>
                                                <div class="card-body text-center">
                                                    <div class="card-body text-center">
                                                        <div class="btn-group-vertical">
                                                            <div class="btn btn-outline-warning bg-warning btn-lg rounded-0 mb-1">
                                                                <a href="editCard?param=${card.id}">Edit</a>
                                                            </div>
                                                            <div class="btn btn-outline-danger bg-danger btn-lg rounded-0 mt-1">
                                                                <a href="deleteCard?param=${card.id}">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
