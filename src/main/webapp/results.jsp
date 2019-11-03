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
                <div class="form-group">
                    <input type="text" id="myInput" class="form-control" onkeyup="myFunction()" placeholder="Filter Search Results" title="Type in a card" required>
                    <div class="valid-feedback">Valid.</div>
                    <div class="invalid-feedback">Please fill out this field.</div>
                </div>
            </div>
            <div class="row">
                    <c:choose>
                        <c:when test="${!empty(cards)}">
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
                                <c:forEach var="card" items="${cards}">
                                    <tr>
                                        <td class="tbColumns">${card.cardName}</td>
                                        <td class="tbColumns">${card.cardType}</td>
                                        <td class="tbColumns">${card.cardRarity}</td>
                                        <td class="tbColumns">${card.cardSet}</td>
                                        <td class="tbColumns">${card.cardIndex}</td>
                                        <td class="tbColumns">${card.price}</td>
                                        <td class="tbColumns">${card.quantity}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <p class="validations">No cards were found</p>
                        </c:otherwise>
                    </c:choose>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
        <script>
            function myFunction() {
                var input, filter, table, tr, td, i, txtValue;
                input = document.getElementById("myInput");
                filter = input.value.toUpperCase();
                table = document.getElementById("myTable");
                tr = table.getElementsByTagName("tr");

                for (i = 0; i < td.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0];

                    if (td) {
                        txtValue = a.textContent || a.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
            }
        </script>
    </body>
</html>


