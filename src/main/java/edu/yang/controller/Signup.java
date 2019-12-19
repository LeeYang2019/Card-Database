package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A simple servlet to search.
 */

@WebServlet(
        urlPatterns = {"/signup"}
)

public class Signup extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * handles HTTP GET requests.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole("general");

        ProjectDao projectDao = new ProjectDao(User.class);
        projectDao.insert(user);


        HttpSession session = req.getSession();
        session.invalidate();

        String url = "singupConfirmation.jsp";
        resp.sendRedirect(url);
    }
}