package edu.yang.controller;

import edu.yang.entity.User;
import edu.yang.service.*;
import edu.yang.entity.YugiohCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
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
 * A simple servlet for uploading a file ther user provides
 * @author Yang
 */
@WebServlet(
        urlPatterns = {"/uploadFile"}
)

public class FileUpload extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final String UPLOAD_DIRECTORY = "../temp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User loggedInUser = (User)session.getAttribute("user");

        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(req)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                        UploadFileReader newReader = new UploadFileReader();
                        List<YugiohCard> list = newReader.readExcelFile((UPLOAD_DIRECTORY + File.separator + name),loggedInUser);
                        //how should i handle this list or do I not return a list?
                    }
                }
                req.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                req.setAttribute("message", "File Upload Failed due to " + ex);
            }
        }else{
            req.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }
        req.getRequestDispatcher("/fileupload.jsp").forward(req, resp);
    }
}