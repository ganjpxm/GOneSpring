package org.ganjp.gone.demo.servlet.v30;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;

public class AsyncThread extends Thread {
	private AsyncContext context;
	public AsyncThread(AsyncContext context) {
		this.context = context;
	}
	
	@Override
	public void run() {
		try {
			ServletResponse response = context.getResponse();
			PrintWriter out = response.getWriter();
			out.println("async starts: " + new Date());
			out.flush();
			Thread.sleep(7000);
			out.println("async ends: " + new Date());
			context.complete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
}
