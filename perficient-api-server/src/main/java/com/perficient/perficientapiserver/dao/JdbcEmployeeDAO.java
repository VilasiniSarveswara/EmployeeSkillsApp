package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Address;
import com.perficient.perficientapiserver.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JdbcEmployeeDAO implements  EmployeeDAO{
    private JdbcTemplate jdbcTemplate;

    public JdbcEmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        String sqlGetAllEmployees = "SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllEmployees);
         while(results.next()){
             Employee theEmployee = mapRowToEmployee(results);
             employeeList.add(theEmployee);
         }
        return employeeList;
    }

    @Override
    public void createEmployee(Employee employee,Address address) {

    String sqlCreateAddress = "INSERT INTO address(street,suite,city,region,postal,address_country_code) VALUES (?,?,?,?,?,?);";
    jdbcTemplate.update(sqlCreateAddress,address.getStreet(),address.getSuite(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountryCode());

    String sqlGetAddressID = "SELECT a.address_id FROM address a WHERE a.street = ? AND a.suite =? AND a.city= ? AND a.region = ? AND a.postal = ? AND a.address_country_code = ?;";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAddressID,address.getStreet(),address.getSuite(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountryCode());
    UUID addressID;
    while(results.next()){
        addressID = mapRowToAddress(results) ;
        address.setAddress_id(addressID);
    }
    employee.setAddress(address);

    String sqlCreateNewEmployee = "INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) VALUES (?,?,?,?,?,?,?,?,?,?);";
    jdbcTemplate.update(sqlCreateNewEmployee,employee.getFirstName(),employee.getLastName(),address.getAddress_id(),employee.getContactEmail(),employee.getCompanyEmail(),employee.getBirthDate(),employee.getHiredData(),employee.getRole(),employee.getBusinessUnit(),employee.getAssignedTo());

    }

    @Override
    public Employee getEmployeeByID(UUID employeeID) {
        Employee theEmployee = new Employee();
        String sqlGetEmployeeByID = "SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeeByID,employeeID);
        while(results.next()){
            theEmployee = mapRowToEmployee(results);
        }
        return theEmployee;
    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployee(UUID employeeID) {
    String sqlDeleteEmployeeSkill = "DELETE FROM employee_skill WHERE employee_id = ?";
    jdbcTemplate.update(sqlDeleteEmployeeSkill,employeeID);

    String sqlDeleteEmployee = "DELETE FROM employee WHERE employee_id = ?";
    jdbcTemplate.update(sqlDeleteEmployee,employeeID);

    }

    //Helper Methods
    Employee mapRowToEmployee(SqlRowSet results){
        Employee theEmployee = new Employee();
        Address theAddress = new Address();
        theEmployee.setEmployee_id((UUID)results.getObject("employee_id"));
        theEmployee.setFirstName(results.getString("firstName"));
        theEmployee.setLastName(results.getString("lastName"));
        //Get the address and store it in Address instance & then set that address to Employee address field
        theAddress.setStreet(results.getString("street"));
        theAddress.setSuite(results.getString("suite"));
        theAddress.setCity(results.getString("city"));
        theAddress.setRegion(results.getString("region"));
        theAddress.setPostal(results.getString("postal"));
        theAddress.setCountryCode(results.getString("address_country_code"));
        theEmployee.setAddress(theAddress);

        theEmployee.setContactEmail(results.getString("contactemail"));
        theEmployee.setCompanyEmail(results.getString("companyemail"));
        theEmployee.setBirthDate(results.getDate("birthdate").toLocalDate());
        theEmployee.setHiredData(results.getDate("hireddate").toLocalDate());
        //theEmployee.setRole(results.getString("role"));
        //theEmployee.setBusinessUnit(results.getString("businessunit"));
        //theEmployee.setAssignedTo((UUID)results.getObject("assignedto"));
        return theEmployee;

    }

    public UUID mapRowToAddress(SqlRowSet results){
        UUID address_id = (UUID)results.getObject("address_id");
        return address_id;
    }
}
