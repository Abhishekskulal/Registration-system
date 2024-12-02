package com.registration.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import com.registration.dao.RegistrationDAO;
import com.registration.dao.RegistrationDAOImpl;
import com.registration.dbconnection.DBUtil;
import com.registration.model.Registration;

@WebServlet("/read-servlet")
public class ReadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String idParam = request.getParameter("id"); 
	        try (Connection connection = DBUtil.getConnection();
	             PrintWriter out = response.getWriter()) {

	            RegistrationDAO rdao = new RegistrationDAOImpl(connection);

	            if (idParam != null) {
	                int id = Integer.parseInt(idParam);
	                Registration reg = rdao.get(id);
	                if (reg != null) {
	                    out.println(reg);
	                } else {
	                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	                    out.println("No record found with ID: " + id);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	}

}
