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
                <h3> Choose File to Upload in Server </h3>
                <form action="uploadFile" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" />
                    <input type="submit" value="upload" />
                </form>
            </div>
            <div class="row">
                <p><a href="fileDownload">I do not have an account.</a></p>
            </div>
            <div class="row">
                <div id="result">
                    <h3>${requestScope["message"]}</h3>
                </div>
            </div>
            <footer class="row">
                <c:import url="footer.jsp"/>
            </footer>
        </div>
    </body>
</html>
