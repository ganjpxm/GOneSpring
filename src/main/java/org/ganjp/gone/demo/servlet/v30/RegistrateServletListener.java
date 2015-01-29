package org.ganjp.gone.demo.servlet.v30;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener()
public class RegistrateServletListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("start up");
		ServletContext context = sce.getServletContext();
		ServletRegistration sr = context.addServlet("RegistratedServlet", "org.ganjp.gone.demo.servlet.v30.RegistratedServlet");
		sr.setInitParameter("servletInitName", "servletInitValue");
		sr.addMapping("/RegistratedServlet");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
}
