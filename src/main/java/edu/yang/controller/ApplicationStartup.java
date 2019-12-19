package edu.yang.controller;

import edu.yang.service.UploadFileReader;
import org.apache.commons.fileupload.UploadContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.Map;


/**
 *  Starts up project indieproject
 *
 *@author  nyang
 */
@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/project4-startup" },
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet {

    /**
     *  Create UploadFileReader object
     *  Set attributes and push UploadFileReader file into ServletContext
     *
     *@exception  ServletException  if there is a Servlet failure
     */
    public void init() throws ServletException {

        /**
         * work-in-progress
         */
    }
}