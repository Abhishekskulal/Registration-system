package com.registration.dao;

import java.util.List;

import com.registration.model.Registration;

public interface RegistrationDAO {
	public boolean register(Registration reg);
	public Registration get(int id);
	public boolean update(int id,String phoneNumber);
	public boolean delete(int id);
	public List<Registration> getAll();
}
