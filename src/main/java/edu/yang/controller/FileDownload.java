package edu.yang.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * A simple servlet that pulls the cards file from
 * @author Yang
 */
@WebServlet(
        urlPatterns = {"/fileDownload"}
)

public class FileDownload extends HttpServlet {

    //logger
    private final Logger logger = LogManager.getLogger(this.getClass());
    private static final int BYTES_DOWNLOAD = 1024;

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.setHeader("Content-Disposition","attachment;filename=downloadname.txt");
        ServletContext context = getServletContext();
        InputStream inStream = context.getResourceAsStream("/Cards.xlsx");

        int read= -1;
        byte[] bytes = new byte[BYTES_DOWNLOAD];
        OutputStream opStream = resp.getOutputStream();

        while((read = inStream.read(bytes))!= -1){
            opStream.write(bytes, 0, read);
        }
        opStream.flush();
        opStream.close();
        inStream.close();
    }
}
