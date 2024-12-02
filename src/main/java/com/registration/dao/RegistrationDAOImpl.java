package com.registration.dao;

import com.registration.dbconnection.*;
import com.registration.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
	private Connection con;
	
	public RegistrationDAOImpl(Connection con) {
		this.con = con;
	}

	// Create
	@Override
	public boolean register(Registration reg) {
		String query = "INSERT INTO Registration (Name, Email, DateOfBirth, PhoneNumber) VALUES (?, ?, ?, ?)";
		int res =0;
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, reg.getName());
			ps.setString(2, reg.getEmail());
			ps.setString(3, reg.getDateOfBirth());
			ps.setString(4, reg.getPhoneNumber());
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(res > 0) {
			return true;
		} else{
			return false;
		}
	}

	// Read
	@Override
	public Registration get(int id) {
		String query = "SELECT * FROM Registration WHERE ID = ?";
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Registration reg = new Registration();
				reg.setId(rs.getInt("id"));
				reg.setName(rs.getString("name"));
				reg.setEmail(rs.getString("email"));
				reg.setDateOfBirth(rs.getString("DateOfBirth"));
				reg.setPhoneNumber(rs.getString("PhoneNumber"));
				reg.setRegistrationDate(rs.getDate("RegistrationDate"));
				return reg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	// Update
	@Override
	public boolean update(int id, String phoneNumber) {
		String query = "UPDATE Registration SET PhoneNumber = ? WHERE ID = ?";
		try(Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query)){
			ps.setString(1, phoneNumber);
			ps.setInt(2, id);
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	// Delete
	@Override
	public boolean delete(int id) {
		String query = "DELETE FROM Registration WHERE ID = ?";
		int result = 0;
		try(Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result>0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	//Get All Registration
	public List<Registration> getAll() {
		String query = "SELECT * FROM Registration";
		List<Registration> registrations = new ArrayList<Registration>();
		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Registration reg = new Registration();
				reg.setId(rs.getInt("id"));
				reg.setName(rs.getString("name"));
				reg.setEmail(rs.getString("email"));
				reg.setDateOfBirth(rs.getString("DateOfBirth"));
				reg.setPhoneNumber(rs.getString("PhoneNumber"));
				reg.setRegistrationDate(rs.getDate("RegistrationDate"));
				registrations.add(reg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registrations;

	}


}
