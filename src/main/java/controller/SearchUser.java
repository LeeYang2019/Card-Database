package controller;

import persistence.YugiohCardDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        YugiohCardDao newYugiohCardDao = new YugiohCardDao();

        //get user input
        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        //if there is not an input, searchTerm is null
        if (searchTerm != null) {
            try {
                req.setAttribute("users", newYugiohCardDao.getByCardName(searchTerm));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("users", newYugiohCardDao.getAll()); //for view all users
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}