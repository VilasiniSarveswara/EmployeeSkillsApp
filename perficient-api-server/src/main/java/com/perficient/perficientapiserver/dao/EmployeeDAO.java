package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Address;
import com.perficient.perficientapiserver.model.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeDAO {
    List<Employee> getEmployeeList();
    void createEmployee(Employee employee,Address address);
    Employee getEmployeeByID(UUID employeeID);
    void updateEmployee(Employee employee);;
    void deleteEmployee(UUID employeeID);
}
