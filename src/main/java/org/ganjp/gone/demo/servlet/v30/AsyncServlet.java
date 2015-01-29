package org.ganjp.gone.demo.servlet.v30;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="SecondServlet", urlPatterns={"/SecondServlet"}, asyncSupported=true)
public class AsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		out.println("Servlet starts: " + new Date());
		out.flush();
		AsyncContext context = req.startAsync();
		context.addListener(new AsyncMyListener());
		Thread thread = new AsyncThread(context);
		thread.start();
		out.println("Servlet end: " + new Date());
		out.flush();
	}
	
	private static final long serialVersionUID = 1L;
}
