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
                    <c:when test="${!empty(cards)}">
                        <table id="myTable" class="table table-hover">
                            <thead class="thead-dark">
                            <tr>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Rarity</th>
                                <th>Set</th>
                                <!--<th>No.</th>
                                <th>Price</th>
                                <th>Qty</th>-->
                            </tr>
                            </thead>
                            <c:forEach var="card" items="${cards}">
                                <tr>
                                    <td class="tbColumns"><a href="displayCard?param=${card.id}">${card.cardName}</a></td>
                                    <td class="tbColumns">${card.cardType}</td>
                                    <td class="tbColumns">${card.cardRarity}</td>
                                    <td class="tbColumns">${card.cardSet}-${card.cardIndex}</td>
                                    <!--<td class="tbColumns">$ {card.cardIndex}</td>
                                    <td class="tbColumns">$ {card.price}</td>
                                    <td class="tbColumns">$ {card.quantity}</td>-->
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

                for (i = 1; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0];

                    if (td) {
                        txtValue = td.textContent || td.innerText;
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


