package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Address;
import com.perficient.perficientapiserver.model.Employee;
import com.perficient.perficientapiserver.model.Field;
import com.perficient.perficientapiserver.model.Skill;
import org.apache.tomcat.jni.Local;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class JdbcEmployeeDAOTest {
    private static SingleConnectionDataSource dataSource;
    private JdbcEmployeeDAO dao;
    private JdbcTemplate jdbcTemplate;
    private SqlRowSet results;
    private Address address;
    private Address testAddress;
    private Employee employee;
    private Employee testEmployee;

    @BeforeClass
    public static void setUpDataSource(){
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/perficientdb");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        dataSource.setAutoCommit(false);
    }
    @Before
    public void setup(){
        jdbcTemplate = new JdbcTemplate(dataSource);

        //Creating an Employee employee instance

        //Create an address record in the Address table
        address = new Address("1024","A12","Dearborn","MI","48321","US");
        testAddress = new Address("1025","A15","Ann Arbor","MI","48390","US");
        //First enter the address in the Address table in db

        String sqlCreateAddress = "INSERT INTO address(street,suite,city,region,postal,country) VALUES (?,?,?,?,?,?);";
        jdbcTemplate.update(sqlCreateAddress,address.getStreet(),address.getSuite(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountry());

        String sqlCreateTestAddress = "INSERT INTO address(street,suite,city,region,postal,country) VALUES (?,?,?,?,?,?);";
        jdbcTemplate.update(sqlCreateTestAddress,testAddress.getStreet(),testAddress.getSuite(),testAddress.getCity(),testAddress.getRegion(),testAddress.getPostal(),testAddress.getCountry());


        //Second, get the address id to have an Address instance
        String sqlGetAddressID = "SELECT a.address_id FROM address a WHERE a.street = ?  AND a.city= ? AND a.region = ? AND a.postal = ? AND a.country = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetAddressID,address.getStreet(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountry());
        UUID addressID = null;
        while(results.next()) {
            addressID = mapRowToAddress(results);
            address.setId(addressID);
        }

        String sqlGetTestAddressID = "SELECT a.address_id FROM address a WHERE a.street = ?  AND a.city= ? AND a.region = ? AND a.postal = ? AND a.country = ?;";
         results = jdbcTemplate.queryForRowSet(sqlGetTestAddressID,testAddress.getStreet(),testAddress.getCity(),testAddress.getRegion(),testAddress.getPostal(),testAddress.getCountry());
        UUID testAddressID = null;
        while(results.next()) {
            testAddressID = mapRowToAddress(results);
            testAddress.setId(testAddressID);
        }

        //Create a field instance
        UUID fieldId = null;
        String sqlGetFieldId = "SELECT field_id FROM field WHERE name='Vue'";
        results = jdbcTemplate.queryForRowSet(sqlGetFieldId);
        while(results.next()){
            fieldId = (UUID)results.getObject("field_id");
        }
        Field field = new Field(fieldId,"Vue","Front End Development");

        //Create Test Field
        UUID testFieldId = null;
        String sqlGetTestFieldId = "SELECT field_id FROM field WHERE name='Java'";
        results = jdbcTemplate.queryForRowSet(sqlGetTestFieldId);
        while(results.next()){
            testFieldId = (UUID)results.getObject("field_id");
        }
        Field testField = new Field(testFieldId,"Java","Software Development");

        //Create a skill instance
        Skill skill = new Skill(field,10,"Pro in Vue");
        String sqlAddSkill = "INSERT INTO skill (field_id,experience,summary) VALUES (?,?,?);";
        jdbcTemplate.update(sqlAddSkill,skill.getField().getId(),skill.getExperience(),skill.getSummary());
        String sqlGetSkillId = "SELECT skill_id FROM skill WHERE skill.field_id = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetSkillId, fieldId);
        UUID skillId = null;
        while(results.next()){
            skillId = (UUID)results.getObject("skill_id");
            skill.setId(skillId);
        }
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        //Get a manager's id
        String sqlGetBossId = "SELECT employee_id FROM employee WHERE firstname='Peter' AND lastname = 'Parker';";
        results = jdbcTemplate.queryForRowSet(sqlGetBossId);
        UUID assignedTo= null;
        while(results.next()){
            assignedTo = (UUID)results.getObject("employee_id");
        }

        //Create TEST skill instance
        Skill testSkill = new Skill(field,20,"Pro in Java");
        String sqlAddTestSkill = "INSERT INTO skill (field_id,experience,summary) VALUES (?,?,?);";
        jdbcTemplate.update(sqlAddTestSkill,testSkill.getField().getId(),testSkill.getExperience(),testSkill.getSummary());
        String sqlGetTestSkillId = "SELECT skill_id FROM skill WHERE skill.field_id = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetTestSkillId, testFieldId);
        UUID testSkillId = null;
        while(results.next()){
            testSkillId = (UUID)results.getObject("skill_id");
            testSkill.setId(testSkillId);
        }
        List<Skill> testSkills = new ArrayList<>();
        testSkills.add(testSkill);

        LocalDate birthdate = LocalDate.parse("1980-11-10");
        LocalDate hireddate = LocalDate.parse("2000-11-10");

        //Test Dates
        LocalDate testBirthdate = LocalDate.parse("1981-11-10");
        LocalDate testHireddate = LocalDate.parse("2001-11-10");
        employee = new Employee("Elon","Musk",address,"elon@tesla.com","emusk@perficient.com",birthdate,hireddate,"Technical Consultant","Adobe",skills,assignedTo);
        employee.setAddress(address);

        //TEST employee
        testEmployee = new Employee("Jeff","Bezos",address,"jb@gmail.com","jeffb@perficient.com",testBirthdate,testHireddate,"Technical Consultant","Adobe",skills,assignedTo);
        testEmployee.setAddress(testAddress);

        dao = new JdbcEmployeeDAO(dataSource);
    }
    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }
    @AfterClass
    public static void closeDataSource(){
        dataSource.destroy();
    }

    @org.junit.Test
    public void getEmployeeList() {
    }

    @org.junit.Test
    public void createEmployee() {
        //Arrange-employee, address and dao instances have been created
        //Act

        dao.createEmployee(employee,address);
        int expected = dao.getEmployeeList().size();

        dao.createEmployee(testEmployee,testAddress);
        int actual = dao.getEmployeeList().size();
        //Assert
        Assert.assertEquals(expected + 1,actual);

    }

    @org.junit.Test
    public void getEmployeeByID() {
        //Arrange
        dao.createEmployee(employee,address);
        String sqlCreateNewEmployee = "INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) VALUES (?,?,?,?,?,?,?,CAST(? AS roles),CAST(? AS businessunits),?);";
        jdbcTemplate.update(sqlCreateNewEmployee,employee.getFirstName(),employee.getLastName(),address.getId(),employee.getContactEmail(),employee.getCompanyEmail(),employee.getBirthDate(),employee.getHiredDate(),employee.getRole(),employee.getBusinessUnit(),employee.getAssignedTo());

        //Get the newly created employee id.
        String sqlGetEmployeeID = "SELECT employee_id FROM employee WHERE firstname = ? AND lastname = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetEmployeeID,employee.getFirstName(),employee.getLastName());
        UUID employeeId = null;
        while(results.next()){
            employeeId = (UUID)results.getObject("employee_id");
        }
        employee.setId(employeeId);

        //Act
        Employee testEmployee = dao.getEmployeeByID(employeeId);

        //Assert
        Assert.assertEquals(employeeId, testEmployee.getId());


    }

    @org.junit.Test
    public void updateEmployee() {
        //Arrange
        dao.createEmployee(employee,address);
        //Get the newly created employee id.
        String sqlGetEmployeeID = "SELECT employee_id FROM employee WHERE firstname = ? AND lastname = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetEmployeeID,employee.getFirstName(),employee.getLastName());
        UUID employeeId = null;
        while(results.next()){
            employeeId = (UUID)results.getObject("employee_id");
        }
        employee.setId(employeeId);
        String expectedLastname = "TestLastname";
        employee.setLastName(expectedLastname);

        //Act
        dao.updateEmployee(employeeId,employee);
        String sqlGetLastname = "SELECT lastname FROM employee WHERE employee_id = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetLastname,employeeId);
        String actualLastname = "";
        while (results.next()){
            actualLastname = results.getString("lastname");
        }

        //Assert
        Assert.assertEquals(expectedLastname,actualLastname);

    }

    @org.junit.Test
    public void deleteEmployee() {
        //Arrange
        dao.createEmployee(employee,address);
        //Get the newly created employee id.
        String sqlGetEmployeeID = "SELECT employee_id FROM employee WHERE firstname = ? AND lastname = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetEmployeeID,employee.getFirstName(),employee.getLastName());
        UUID employeeId = null;
        while(results.next()){
            employeeId = (UUID)results.getObject("employee_id");
        }

        int expectedSize = dao.getEmployeeList().size();
        //Act
        dao.deleteEmployee(employeeId);
        int actualSize = dao.getEmployeeList().size();
        //Assert
        Assert.assertEquals(expectedSize - 1,actualSize);
    }

    //Helper method
    private UUID mapRowToAddress(SqlRowSet results){
        UUID address_id = (UUID)results.getObject("address_id");
        return address_id;
    }
}