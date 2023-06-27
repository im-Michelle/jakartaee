package org.michelle.servlet.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/parameters/url")
public class ServletParameter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String greeting = req.getParameter("greeting");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("   <head>");
        out.println("       <meta charset=\"UTF-8\">");
        out.println("       <title>Task 1: Servlet and parameters</title>");
        out.println("   </head>");
        out.println("   <body>");
        out.println("       <h1>Task 1: Servlet and parameters!</h1>");
        if (greeting != null && name != null && lastname != null) {
            out.println("       <h2>"+ greeting + " my name is: " + name + " " +  lastname + "</h2>");
        } else {
            out.println("       <h2>no parameters</h2>");
        }
        try {
            String date = new SimpleDateFormat("EEEE dd 'de' MMMM, yyyy").format(new Date());
            out.println("       <h3>Today is: " + date + "</h3>");
        } catch (Exception e) {
            out.println("       <h3>date: invalid</h3>");
        }
        out.println("   </body>");
        out.println("</html>");
        out.close();
    }
}
