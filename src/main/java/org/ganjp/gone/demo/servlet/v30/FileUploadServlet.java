package org.ganjp.gone.demo.servlet.v30;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/** 
 * Servlet implementation class FileUploadServlet 
 */  
@WebServlet(urlPatterns = "/upload")  
@MultipartConfig(location = "/ganjp/tmp", maxFileSize = 8388608, fileSizeThreshold = 819200)  
public class FileUploadServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    private String fileNameExtractorRegex = "filename=\".+\"";  
         
    /** 
     * @see HttpServlet#HttpServlet() 
     */  
    public FileUploadServlet() {  
        super();  
    }  
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        throw new UnsupportedOperationException();  
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        List<String> fileNames = new LinkedList<String>();  
        request.setCharacterEncoding("UTF-8");  
        Collection<Part> parts = request.getParts();  
        for (Iterator<Part> iterator = parts.iterator(); iterator.hasNext();) {  
            Part part = iterator.next();
            String fileName = getFileName(part);  
            if(fileName!=null){  
                fileNames.add(fileName);  
                part.write(fileName);  
            }  
        }  
        request.setAttribute("fileNames", fileNames); 
        request.getRequestDispatcher("index.html").forward(request, response);  
    }  
    
    private String getFileName(Part part){ 
        String cotentDesc = part.getHeader("content-disposition");  
        String fileName = null;  
        Pattern pattern = Pattern.compile(fileNameExtractorRegex);  
        Matcher matcher = pattern.matcher(cotentDesc);  
        if(matcher.find()){  
            fileName = matcher.group();  
            fileName = fileName.substring(10, fileName.length()-1);  
        }  
        return fileName;  
    }  
}  
