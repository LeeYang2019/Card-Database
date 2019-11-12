package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.entity.YugiohCard;
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
import javax.validation.constraints.Null;
import java.io.IOException;

/**
 * A simple servlet to search.
 */

@WebServlet(
        urlPatterns = {"/login"}
)

public class Login extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //get a session
        HttpSession session = req.getSession();

        logger.info("entered login servlet");

        String userName = req.getRemoteUser();

        logger.info(userName);

        if (userName != null) {

            ProjectDao userDao = new ProjectDao(User.class);

            logger.info("this user : " + userName);

            //get the user from the session
            User loggedInUser = (User) userDao.getByProperty("userName", req.getRemoteUser());

            logger.info("this user is " + loggedInUser.getUserName() + "; ID: " + loggedInUser.getId());

            //set the user id into the session
            session.setAttribute("id", loggedInUser.getId());
        }

        logger.info("leaving login servlet");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }
}