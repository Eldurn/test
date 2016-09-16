package com.test.examples;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletResponse;

public class Work implements ServletContextListener {
	  private static final BlockingQueue<AsyncContext> queue = new LinkedBlockingQueue<AsyncContext>();

	  private volatile Thread thread;

	  public static void add(AsyncContext c) {
	    //queue.add(c);
	  }

	  @Override
	  public void contextInitialized(ServletContextEvent servletContextEvent) {
	    thread = new Thread(new Runnable() {
	      @Override
	      public void run() {
	        while (true) {
	          try {
	            Thread.sleep(2000);
	            AsyncContext context;
	            while ((context = (AsyncContext) queue.poll()) != null) {
	              try {
	                ServletResponse response = context.getResponse();
	                response.setContentType("text/plain");
	                PrintWriter out = response.getWriter();
	                out.printf("Thread %s completed the task.", Thread.currentThread().getName());
	                
	                StringBuffer jb = new StringBuffer();
	                jb.append(" Request:");
	                String line = null;
	                try {
	                  BufferedReader reader = context.getRequest().getReader();
	                  while ((line = reader.readLine()) != null)
	                    jb.append(line);
	                } catch (Exception e) { /*report an error*/ }

	                out.println(jb.toString());
	                
	                out.flush();
	              } catch (Exception e) {
	                throw new RuntimeException(e.getMessage(), e);
	              } finally {
	                context.complete();
	              }
	            }
	          } catch (InterruptedException e) {
	            return;
	          }
	        }
	      }
	    });
	    thread.start();
	  }
	  
	  

	  @Override
	  public void contextDestroyed(ServletContextEvent servletContextEvent) {
	    thread.interrupt();
	  }
	}