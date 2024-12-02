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

@WebServlet("/web-servlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = DBUtil.getConnection();
             PrintWriter out = response.getWriter()) {

            RegistrationDAO rdao = new RegistrationDAOImpl(connection);
            boolean result = rdao.delete(id);

            if (result) {
                out.println("Record deleted successfully.");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.println("Failed to delete record. Record not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
	}

}
