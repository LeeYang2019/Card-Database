package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.persistence.ProjectDao;
import edu.yang.service.*;
import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * A simple servlet for uploading a the Cards.xlsx file the user provides
 * @author Yang
 */
@WebServlet(
        urlPatterns = {"/uploadFile"}
)

public class FileUpload extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String UPLOAD_DIRECTORY = "../temp";

    /**
     * GET
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //create session and get user
        HttpSession session = req.getSession();
        User loggedInUser = (User)session.getAttribute("user");
        ProjectDao newYugiohCardDao = new ProjectDao(YugiohCard.class);

        Map<String, Object> propsAndValues = new HashMap<>();
        List<YugiohCard> userList = new ArrayList<>();

        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(req)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));

                        //create helper class to parse Cards.xlsx file and return list of cards
                        // (list is returned for testing purposes, is not returned anywhere)
                        UploadFileReader newReader = new UploadFileReader();
                        List<YugiohCard> list = newReader.readExcelFile((UPLOAD_DIRECTORY + File.separator + name),loggedInUser);
                    }
                }

                //get an updated list of cards for the user
                propsAndValues.put("user", loggedInUser);
                userList = newYugiohCardDao.findByPropertyEqual(propsAndValues);

                req.setAttribute("cards", userList);

            } catch (Exception ex) {
                req.setAttribute("message", "File Upload Failed due to " + ex);
                req.getRequestDispatcher("/fileupload.jsp").forward(req, resp);
            }
        }else{
            req.setAttribute("message",
                    "Sorry this Servlet only handles file upload requests");
            req.getRequestDispatcher("/fileupload.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}