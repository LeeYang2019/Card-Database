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
 * Login servlet to get the remote user and store it in the session
 * @author Lee Yang
 */

@WebServlet(
        urlPatterns = {"/login"}
)

public class Login extends HttpServlet {

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

        logger.info("entering Login servlet");

        //create session
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;

        //get remote user
        String userName = req.getRemoteUser();

        //get user from db
        ProjectDao userDao = new ProjectDao(User.class);
        User loggedInUser = (User) userDao.getByProperty("userName", userName);

        logger.info("Logging in for user: " + loggedInUser.getUserName());

        //store user in session
        session.setAttribute("user", loggedInUser);

        //make service call
        //getUser cards by price

        //if the user collection size is 0
        logger.info("this user's card collection size is : " + loggedInUser.getCards().size());

        if (loggedInUser.getCards().size() == 0) {
            logger.info("entering file upload page");
            dispatcher = req.getRequestDispatcher("/fileupload.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/index.jsp");
        }

        dispatcher.forward(req, resp);
    }
}