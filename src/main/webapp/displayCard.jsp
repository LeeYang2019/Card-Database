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
                        <div class="col-12 mx-auto mb-3">
                            <div class="card rounded-0">
                                <div class="card-header bg-muted">
                                    <h4 class="text-dark font-weight-bold">Yu-Gi-Oh! Card</h4>
                                </div>
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col mx-auto mb-3">
                                            <h4 class="text-dark"><b>${card.cardName}</b></h4>
                                            <h6 class="font-italic">${card.setName}</h6>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-4 mx-auto mb-3">
                                            <img src="${card.image}" class="imageSize">
                                        </div>
                                        <div class="col-4 mx-auto mb-3">
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
                                        <div class="col-md-4 d-none d-md-block mx-auto mb-3">
                                            <div class="card border-muted rounded-0">
                                                <div class="card-header bg-muted">
                                                    <h4>Action</h4>
                                                </div>
                                                <div class="card-body text-center">
                                                   <!-- <form id="cardForm" action="addCards" class="needs-validation" novalidate>
                                                        <div class="col myButtons">
                                                            <button type="submit" class="btn btn-primary">Edit</button>

                                                        </div>
                                                        <div class="col myButtons">
                                                            <button type="reset" class="btn btn-danger">Sell</button>
                                                        </div> -->
                                                    </form>
                                                    <div class="card-body text-center">
                                                        <div class="btn-group-vertical">
                                                            <div class="btn btn-outline-dark btn-lg">
                                                                <a href="editCard?param=${card.id}">Edit</a>
                                                            </div>
                                                            <div class="btn btn-outline-dark btn-lg">
                                                                <a href="sellCard?param=${card.id}">Sell</a>
                                                            </div>
                                                            <div class="btn btn-outline-dark btn-lg">
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
