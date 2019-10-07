package controller;

import persistence.CardDao;

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

        CardDao newCardDao = new CardDao();

        //get user input
        String searchTerm = req.getParameter("searchTerm");
        String searchType = req.getParameter("searchType");

        //if there is not an input, searchTerm is null
        if (searchTerm != null) {
            try {
                req.setAttribute("users", newCardDao.getByCardName(searchTerm));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("users", newCardDao.getAll()); //for view all users
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);

    }
}