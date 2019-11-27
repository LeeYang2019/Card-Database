package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.service.*;
import edu.yang.entity.YugiohCard;
import edu.yang.persistence.ProjectDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
 * A
 */
@WebServlet(
        urlPatterns = {"/uploadFile"}
)

public class FileUpload extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "../temp";

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User loggedInUser = (User)session.getAttribute("user");
/*
        logger.info("files in temp:");
        File file = new File("temp");
        for(String fileNames : file.list()) {
            logger.info(fileNames);
        }
*/
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(req)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName(); //name of the file
                        logger.info("name of the uploaded file: " + name);
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                        logger.info("filepath: " + UPLOAD_DIRECTORY + File.separator + name);
                        FileReader newReader = new FileReader();
                        List<YugiohCard> list = newReader.excelRead((UPLOAD_DIRECTORY + File.separator + name),loggedInUser);
                    }
                }


                //File uploaded successfully
                req.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                req.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            req.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");

        }
/*
        logger.info("files in temp:");
        File fileNew = new File("temp");
        for(String fileNames : fileNew.list()) {
            logger.info(fileNames);
        }
*/
        req.getRequestDispatcher("/fileupload.jsp").forward(req, resp);
    }

}