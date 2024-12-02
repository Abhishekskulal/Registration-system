package com.registration.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.registration.dao.RegistrationDAO;
import com.registration.dao.RegistrationDAOImpl;
import com.registration.dbconnection.DBUtil;
import com.registration.model.Registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/create-servlet")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form parameters
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String dob = req.getParameter("dob");
        String phone = req.getParameter("phone");

        // Validate inputs
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || dob == null || dob.isEmpty() || phone == null || phone.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try (PrintWriter out = resp.getWriter()) {
                out.println("Invalid input. All fields are required.");
            }
            return;
        }

        // Create a Registration object
        Registration reg = new Registration();
        reg.setName(name);
        reg.setEmail(email);
        reg.setDateOfBirth(dob);
        reg.setPhoneNumber(phone);

        // Set up DAO and database connection
        try (Connection connection = DBUtil.getConnection()) {
            RegistrationDAO rdao = new RegistrationDAOImpl(connection);
            boolean result = rdao.register(reg);

            // Send response
            try (PrintWriter out = resp.getWriter()) {
                if (result) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    out.println("Registration Successful!");
                } else {
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.println("Failed to Register. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = resp.getWriter()) {
                out.println("Database connection error: " + e.getMessage());
            }
        }
    }
}
