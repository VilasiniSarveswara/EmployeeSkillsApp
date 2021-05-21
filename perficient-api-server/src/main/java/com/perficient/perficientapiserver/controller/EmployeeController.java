package com.perficient.perficientapiserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.perficientapiserver.dao.JdbcEmployeeDAO;
import com.perficient.perficientapiserver.model.Address;
import com.perficient.perficientapiserver.model.Employee;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    @Autowired
    private JdbcEmployeeDAO employeeDAO;

    public EmployeeController(JdbcEmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @ApiOperation("Get all Perficient employees")
    @RequestMapping(path="/employees", method= RequestMethod.GET)
    public List<Employee> findAllEmployees(){
        List<Employee> employeeList = employeeDAO.getEmployeeList();
        return employeeList;
    }

    @ApiOperation("Find a Perficient employee by ID ")
    @RequestMapping(path="/employees/{id}", method = RequestMethod.GET)
    public Employee findEmployeeById(@PathVariable UUID id){
        Employee theEmployee = employeeDAO.getEmployeeByID(id);
        return theEmployee;
    }

    @ApiOperation("Create a Perficient employee")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/employees", method = RequestMethod.POST)
    public void createFirstName(@RequestBody JsonNode object) throws JsonProcessingException {
        JsonNode addressNode = object.path("address");
        JsonNode employeeNode = object.path("employee");
        Address address = new ObjectMapper().readValue(addressNode.toString(),Address.class);
        //Employee employee = new ObjectMapper().readValue(employeeNode.toString(),Employee.class);
        Employee employee = new Employee();
        String firstName = employeeNode.path("firstName").toString();
        employee.setFirstName(firstName);
        String lastName = employeeNode.path("lastName").toString();
        employee.setLastName(lastName);
        String contactEmail = employeeNode.path("contactEmail").toString();
        employee.setContactEmail(contactEmail);
        String companyEmail = employeeNode.path("companyEmail").toString();
        employee.setCompanyEmail(companyEmail);
        String bdate = employeeNode.path("birthDate").asText();
        LocalDate birthDate = LocalDate.parse(bdate);
        employee.setBirthDate(birthDate);

        String hdate = employeeNode.path("hiredDate").asText();
        LocalDate hiredDate = LocalDate.parse(hdate);
        employee.setHiredData(hiredDate);
        String role = employeeNode.path("role").toString();
        employee.setRole(role);
        String businessUnit = employeeNode.path("businessUnit").toString();
        String assignedTo = employeeNode.path("assignedTo").toString();
        employeeDAO.createEmployee(employee,address);
    }


    @ApiOperation("Update a Perficient employee by ID")
    @RequestMapping(path="/employees/newEmployee/{id}", method = RequestMethod.PUT)
    public void updateEmployeeById(@RequestBody Employee newEmployee, @PathVariable UUID id){
        System.out.println(newEmployee.getFirstName());
        UUID newEmployee_id = id;
        System.out.println(newEmployee);
        System.out.println(newEmployee_id);
    }

    @ApiOperation("Delete a Perficient Employee by ID")
    @RequestMapping(path="/employees/{id}", method = RequestMethod.DELETE)
    public void removeEmployeeById(@PathVariable UUID id){
    employeeDAO.deleteEmployee(id);
    }
}
