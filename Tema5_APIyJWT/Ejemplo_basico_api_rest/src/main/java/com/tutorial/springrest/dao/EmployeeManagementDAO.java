package com.tutorial.springrest.dao;

import java.util.List;

import com.tutorial.springrest.model.Employee;

public interface EmployeeManagementDAO {

    public void save(Employee employee);

    public int delete(int id);

    public Employee get(int id);

    public List<Employee> list();
}