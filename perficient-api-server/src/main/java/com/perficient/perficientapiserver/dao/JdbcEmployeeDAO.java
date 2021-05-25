package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Address;
import com.perficient.perficientapiserver.model.Employee;
import com.perficient.perficientapiserver.model.Skill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JdbcEmployeeDAO implements  EmployeeDAO{
    private JdbcTemplate jdbcTemplate;

    /*public JdbcEmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    public JdbcEmployeeDAO(DataSource datasource){
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }



    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<>();
        String sqlGetAllEmployees = "SELECT e.employee_id,e.firstName,e.lastName,a.address_id,a.street,a.suite,a.city,a.region,a.postal,a.country,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllEmployees);
         while(results.next()){
             Employee theEmployee = mapRowToEmployee(results);
             JdbcSkillDAO skillDAO = new JdbcSkillDAO(jdbcTemplate);
             theEmployee.setSkills(skillDAO.findAllSkillsByEmployee(theEmployee.getId()));
             employeeList.add(theEmployee);
         }

        return employeeList;
    }

    @Override
    public void createEmployee(Employee employee,Address address) {
    //First enter the address in the Address table in db
        //TODO: Figure out how to ensure transaction either completes entirely or rolls back.
    String sqlCreateAddress = "INSERT INTO address(street,suite,city,region,postal,country) VALUES (?,?,?,?,?,?);";
    jdbcTemplate.update(sqlCreateAddress,address.getStreet(),address.getSuite(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountry());

    //Second, get the address id
    String sqlGetAddressID = "SELECT a.address_id FROM address a WHERE a.street = ?  AND a.city= ? AND a.region = ? AND a.postal = ? AND a.country = ?;";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAddressID,address.getStreet(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountry());
    UUID addressID = null;
    while(results.next()){
        addressID = mapRowToAddress(results) ;
        address.setId(addressID);
    }
    employee.setAddress(address);


    //Third, create the employee in the db
    String sqlCreateNewEmployee = "INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) VALUES (?,?,?,?,?,?,?,CAST(? AS roles),CAST(? AS businessunits),?);";
    jdbcTemplate.update(sqlCreateNewEmployee,employee.getFirstName(),employee.getLastName(),address.getId(),employee.getContactEmail(),employee.getCompanyEmail(),employee.getBirthDate(),employee.getHiredDate(),employee.getRole(),employee.getBusinessUnit(),employee.getAssignedTo());

    //Fourth, get the newly created employee id.
        String sqlGetEmployeeID = "SELECT employee_id FROM employee WHERE firstname = ? AND lastname = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetEmployeeID,employee.getFirstName(),employee.getLastName());
        UUID employeeId = null;
        while(results.next()){
            employeeId = (UUID)results.getObject("employee_id");
        }
        employee.setId(employeeId);
    //Fifth,add skills to the employee and update the related table employee_skill
    JdbcSkillDAO skillDAO = new JdbcSkillDAO(jdbcTemplate);
    for(Skill skill : employee.getSkills()){
        skillDAO.addSkillToEmployee(employee.getId(),skill);
    }
    }

    @Override
    public Employee getEmployeeByID(UUID employeeID) {
        Employee theEmployee = new Employee();
        String sqlGetEmployeeAddress = "SELECT e.employee_id,e.firstName,e.lastName,a.address_id,a.street,a.suite,a.city,a.region,a.postal,a.country,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetEmployeeAddress,employeeID);
        while(results.next()){
            theEmployee = mapRowToEmployee(results);
        }
        JdbcSkillDAO skillDAO = new JdbcSkillDAO(jdbcTemplate);
        theEmployee.setSkills(skillDAO.findAllSkillsByEmployee(employeeID));
        return theEmployee;
    }

    @Override
    public void updateEmployee(UUID employeeID,Employee employee) {
        String sqlUpdateAddress = "UPDATE address SET street =?,suite = ?,city = ?,region = ?,postal = ?,country = ? WHERE address_id = CAST(? AS UUID);";
        jdbcTemplate.update(sqlUpdateAddress,employee.getAddress().getStreet(),employee.getAddress().getSuite(),employee.getAddress().getCity(),employee.getAddress().getRegion(),employee.getAddress().getPostal(),employee.getAddress().getCountry(),employee.getAddress().getId());


        String sqlUpdateEmployee = "UPDATE employee SET firstName = ?,lastName=?,address=?,contactemail=?,companyemail=?,birthdate=?,hireddate=?,role= CAST(? AS roles),businessunit=CAST(? AS businessunits),assignedto=? WHERE employee_id=?;";
        jdbcTemplate.update(sqlUpdateEmployee,employee.getFirstName(),employee.getLastName(),employee.getAddress().getId(),employee.getContactEmail(),employee.getCompanyEmail(),employee.getBirthDate(),employee.getHiredDate(),employee.getRole(),employee.getBusinessUnit(),employee.getAssignedTo(),employeeID);

        JdbcSkillDAO skillDAO = new JdbcSkillDAO(jdbcTemplate);
        for(int i = 0; i < employee.getSkills().size();i++){
            skillDAO.updateSkillFromEmployeeById(employeeID,employee.getSkills().get(i).getId(),employee.getSkills().get(i));
        }

    }

    @Override
    public void deleteEmployee(UUID employeeID) {
    String sqlDeleteEmployeeSkill = "DELETE FROM employee_skill WHERE employee_id = ?";
    jdbcTemplate.update(sqlDeleteEmployeeSkill,employeeID);

    String sqlDeleteEmployee = "DELETE FROM employee WHERE employee_id = ?";
    jdbcTemplate.update(sqlDeleteEmployee,employeeID);

    String sqlGetAddressId = "SELECT address FROM employee WHERE employee_id = ?;";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAddressId,employeeID);
    UUID addressId = null;
    while(results.next()){
        addressId = (UUID)results.getObject("address");
    }

    String sqlDeleteEmployeeAddress = "DELETE FROM address where address_id = ?;";
    jdbcTemplate.update(sqlDeleteEmployeeAddress,addressId);

    }

    //Helper Methods
    Employee mapRowToEmployee(SqlRowSet results){
        Employee theEmployee = new Employee();
        Address theAddress = new Address();
        theEmployee.setId((UUID)results.getObject("employee_id"));
        theEmployee.setFirstName(results.getString("firstName"));
        theEmployee.setLastName(results.getString("lastName"));
        //Get the address and store it in Address instance & then set that address to Employee address field
        theAddress.setId((UUID)results.getObject("address_id"));
        theAddress.setStreet(results.getString("street"));
        theAddress.setSuite(results.getString("suite"));
        theAddress.setCity(results.getString("city"));
        theAddress.setRegion(results.getString("region"));
        theAddress.setPostal(results.getString("postal"));
        theAddress.setCountry(results.getString("country"));
        theEmployee.setAddress(theAddress);
        theEmployee.setContactEmail(results.getString("contactemail"));
        theEmployee.setCompanyEmail(results.getString("companyemail"));
        theEmployee.setBirthDate(results.getDate("birthdate").toLocalDate());
        theEmployee.setHiredDate(results.getDate("hireddate").toLocalDate());
        theEmployee.setRole(results.getString("role"));
        theEmployee.setBusinessUnit(results.getString("businessunit"));
        theEmployee.setAssignedTo((UUID)results.getObject("assignedto"));
        return theEmployee;

    }

    private UUID mapRowToAddress(SqlRowSet results){
        UUID address_id = (UUID)results.getObject("address_id");
        return address_id;
    }
}
