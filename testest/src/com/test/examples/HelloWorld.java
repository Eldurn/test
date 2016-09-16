package com.test.examples;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSourceFactory;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(asyncSupported = true, value = "/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		response.setContentType("text/html");
        PrintWriter printWriter  = response.getWriter();
        printWriter.println("<h1>Hello World!</h1>");
        */
        //Work.add(request.startAsync());
		PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>SimpleServlet</title>");
        out.println("</head>");
        out.println("<body>"); 
        //Context ctx = new InitialContext();
        //DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/SaastoHippoDB");
        
        

        out.print("testaus");
        out.print("<br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Work.add(request.startAsync());
	}

}
