<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp"/>
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

                <form id="cardForm" action="addCards" class="needs-validation" novalidate>

                    <div class="form-group row">
                        <label for="cardName" class="col-md-4 col-form-label">Name</label>
                        <div class="col-md-6">
                            <input type="cardName" class="form-control" id="cardName" placeholder="Enter name of card" name="cardName" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="cardType" class="col-md-4 col-form-label">Type</label>
                        <div class="col-md-6">
                            <select class="form-control" id="cardType" name="cardType">
                                <option value="">-------</option>
                                <option value="Monster">Monster</option>
                                <option value="Spell">Spell</option>
                                <option value="Trap">Trap</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">

                        <label for="cardRarity" class="col-md-4 col-form-label">Rarity</label>
                        <div class="col-md-6">
                            <select class="form-control" id="cardRarity" name="cardRarity">
                                <option value="">-------</option>
                                <option value="Common">Common</option>
                                <option value="Rare">Rare</option>
                                <option value="Super">Super</option>
                                <option value="Ultra">Ultra</option>
                                <option value="Secret">Secret</option>
                                <option value="Prismatic">Prismatic</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="cardSet" class="col-md-4 col-form-label">Set</label>
                        <div class="col-md-6">
                            <input type="cardSet" class="form-control" id="cardSet" placeholder="Enter a card set" name="cardSet" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="cardIndex" class="col-md-4 col-form-label">No</label>
                        <div class="col-md-6">
                            <input type="cardIndex" class="form-control" id="cardIndex" placeholder="Enter a card no." name="cardIndex" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="cardPrice" class="col-md-4 col-form-label">Price</label>
                        <div class="col-md-6">
                            <input type="cardPrice" class="form-control" id="cardPrice" placeholder="Enter a price." name="cardPrice" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="cardQuantity" class="col-md-4 col-form-label">Quantity</label>
                        <div class="col-md-6">
                            <input type="cardQuantity" class="form-control" id="cardQuantity" placeholder="Enter a quantity." name="cardQuantity" required>
                            <div class="valid-feedback">Valid.</div>
                            <div class="invalid-feedback">Please fill out this field.</div>
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
