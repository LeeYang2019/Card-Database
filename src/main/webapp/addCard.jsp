<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp"/>
<html>
    <body>
        <div class="container">
            <c:import url="header.jsp"/>
            <div class="row">
                <c:import url="navbar.jsp"/>
            </div>

                <form id="cardForm" action="addCards" class="needs-validation" novalidate>
                    <div class="row">
                        <div class="col-4">
                            <p>This is for the picture</p>
                        </div>
                        <div class="col-4">
                            <div class="col">
                                <label for="cardName">Name:</label>
                                <input type="cardName" class="form-control" id="cardName" placeholder="Enter a card name" name="cardName" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div class="col">
                                <label for="cardType">Type:</label>
                                <input type="cardType" class="form-control" id="cardType" placeholder="Enter a card type" name="cardType" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div class="col">
                                <label for="cardRarity">Rarity:</label>
                                <input type="cardRarity" class="form-control" id="cardRarity" placeholder="Enter a card rarity" name="cardRarity" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div class="col">
                                <label for="cardSet">Set:</label>
                                <input type="cardSet" class="form-control" id="cardSet" placeholder="Enter a card set" name="cardSet" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div class="col">
                                <label for="cardIndex">No:</label>
                                <input type="cardIndex" class="form-control" id="cardIndex" placeholder="Enter a card no." name="cardIndex" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div class="col">
                                <label for="cardPrice">Price:</label>
                                <input type="cardPrice" class="form-control" id="cardPrice" placeholder="Enter a price" name="cardPrice" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>
                            <div class="col">
                                <label for="cardQuantity">Quantity:</label>
                                <input type="cardQuantity" class="form-control" id="cardQuantity" placeholder="Enter a quantity." name="cardQuantity" required>
                                <div class="valid-feedback">Valid.</div>
                                <div class="invalid-feedback">Please fill out this field.</div>
                            </div>
                        </div>
                        <div class="col-4">
                            <p>Something else will go here</p>
                        </div>
                    </div>
                    <div class="row">
                        <button type="submit" class="btn btn-primary">Add</button>
                        <button type="reset" class="btn btn-danger">Cancel</button>
                    </div>
                </form>
                <br/>
            <br/>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
