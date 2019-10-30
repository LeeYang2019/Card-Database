<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="head.jsp"/>

<html>
    <body>
        <div class="container">
            <div class="row">
                <c:import url="header.jsp"/>
            </div>
            <div class="row">
                <c:import url="navbar.jsp"/>
            </div>
            <div class="row">
                <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for cards.." title="Type in a card">
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
                            <p>No users were found</p>
                        </c:otherwise>
                    </c:choose>
            </div>
            <footer class="row">
                User Database
            </footer>
        </div>
        <script>
            function myFunction() {
                var input, filter, table, td, a, i, txtValue;
                input = document.getElementById("myTable");
                filter = input.value.toUpperCase();
                table = document.getElementById("myUL");
                td = table.getElementsByTagName("td");
                for (i = 0; i < td.length; i++) {
                    a = td[i].getElementsByTagName("a")[0];
                    txtValue = a.textContent || a.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        td[i].style.display = "";
                    } else {
                        td[i].style.display = "none";
                    }
                }
            }
        </script>
    </body>
</html>


