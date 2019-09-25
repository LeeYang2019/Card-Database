import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * A simple servlet to welcome the user.
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserData userData = new UserData();

        //get user input
        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        System.out.println("Searchtype is: " + searchType);
/**
        //if there is not an input, searchTerm is null
        if (searchTerm != null) {
            try {
                req.setAttribute("users", userData.getUserByTerm(searchTerm, searchType));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("users", userData.getAllUsers()); //for view all users
        }
**/
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}