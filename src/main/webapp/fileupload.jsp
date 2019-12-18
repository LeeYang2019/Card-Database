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
                <div class="col-8 mx-auto mb-3">
                    <div class="card border-dark">
                        <div class="card-header bg-dark">
                            <h4 class="text-white">Upload a File</h4>
                        </div>
                        <div class="card-body">
                            <form action="uploadFile" method="post" enctype="multipart/form-data">
                                <input type="file" name="file" />
                                <input type="submit" value="upload" />
                            </form>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div id="result">
                        <h3>${requestScope["message"]}</h3>
                    </div>
                </div>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
