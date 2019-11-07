package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
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

        //get a session
        //HttpSession session = req.getSession();
        logger.info("entering signup servlet");


        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        logger.info("got data for " + userName);
        logger.info("got data for " + password);

        if (userName != null && password != null) {
            ProjectDao userDao = new ProjectDao(User.class);
            User newUser  = new User(userName, password, "general");
            int id = userDao.insert(newUser);
        }

        logger.info("leaving servlet");

        String url = "/login";
        resp.sendRedirect(url);
        //RequestDispatcher dispatcher = req.getRequestDispatcher("/login");
        //dispatcher.forward(req, resp);
    }
}