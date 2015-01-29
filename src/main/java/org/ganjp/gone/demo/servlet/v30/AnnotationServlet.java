package org.ganjp.gone.demo.servlet.v30;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="FirstServlet", urlPatterns={"/FirstServlet", "/MyServlet"})
public class AnnotationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		out.println("Hello World");
		out.close();
	}
	
	private static final long serialVersionUID = 1L;
}
