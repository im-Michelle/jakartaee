package org.michelle.servlet.webapp.form;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/register")
public class FormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String[] programmingLanguages = req.getParameterValues("programmingLanguages");
        String[] roles = req.getParameterValues("roles");
        String language = req.getParameter("language");
        boolean enable = req.getParameter("enable") != null && req.getParameter("enable").equals("on");
        String secret = req.getParameter("secret");

        List<String> errors = new ArrayList<>();

        if (username == null || username.isBlank()) {
            errors.add("Username is required");
        }
        if (password == null || password.isBlank()) {
            errors.add("Password is required");
        }
        if (email == null || !email.contains("@")) {
            errors.add("Email is required and must contain @");
        }
        if (country == null || country.equals("") || country.equals(" ")) {
            errors.add("Country is required");
        }
        if (programmingLanguages == null || programmingLanguages.length == 0) {
            errors.add("At least one programming language must be selected");
        }
        if (roles == null || roles.length == 0) {
            errors.add("At least one role must be selected");
        }
        if (language == null) {
            errors.add("Language is required");
        }
        if (errors.isEmpty()) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("   <head>");
                out.println("       <meta charset=\"UTF-8\">");
                out.println("       <title>Form Result</title>");
                out.println("   </head>");
                out.println("   <body>");
                out.println("       <h1>Form Result!</h1>");
                out.println("       <ul>");


                out.println("           <li>Username: " + username + "</li>");
                out.println("           <li>Password: " + password + "</li>");
                out.println("           <li>Email: " + email + "</li>");
                out.println("           <li>Country: " + country + "</li>");
                out.println("           <li>Languages: <ul>");
                Arrays.asList(programmingLanguages).forEach(prolan -> {
                    out.println("               <li>" + prolan + "</li>");
                });
                out.println("           </ul></li>");
                out.println("           <li>Roles: <ul>");
                Arrays.asList(roles).forEach(rol -> out.println("               <li>" + rol + "</li>"));
                out.println("           </ul></li>");
                out.print("           <li>Language: " + language + "</li>");
                out.print("           <li>Enable: " + enable + "</li>");
                out.print("           <li>Secret: " + secret + "</li>");
                out.println("       </ul>");
                out.println("   </body>");
                out.println("</html>");
            }
        } else {
//                errors.forEach(error -> out.println("           <li>" + error + "</li>"));
//                out.println("           <a href=\"/webapp-form/index.html\">Go back to form</a>");
            req.setAttribute("errors", errors);
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
