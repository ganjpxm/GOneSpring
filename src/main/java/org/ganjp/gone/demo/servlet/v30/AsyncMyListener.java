package org.ganjp.gone.demo.servlet.v30;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class AsyncMyListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("onComplete invoked!");
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		// TODO Auto-generated method stub

	}

}
