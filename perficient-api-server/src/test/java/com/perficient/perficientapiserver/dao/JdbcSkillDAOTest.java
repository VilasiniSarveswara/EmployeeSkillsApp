package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Address;
import com.perficient.perficientapiserver.model.Employee;
import com.perficient.perficientapiserver.model.Field;
import com.perficient.perficientapiserver.model.Skill;
import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class JdbcSkillDAOTest {
    private static SingleConnectionDataSource dataSource;
    private JdbcSkillDAO dao;
    private JdbcEmployeeDAO employeeDAO;
    private JdbcTemplate jdbcTemplate;
    private SqlRowSet results;
    private Address address;
    private Address testAddress;
    private Employee employee;
    private Employee testEmployee;
    private Skill testSkill;
    private UUID employeeId;
    private Skill skill;
    private Field testField;
    private Field field;
    private UUID skillId;

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
        //Create an address record in the Address table
        address = new Address("1024","A12","Dearborn","MI","48321","US");
        String sqlCreateAddress = "INSERT INTO address(street,suite,city,region,postal,country) VALUES (?,?,?,?,?,?);";
        jdbcTemplate.update(sqlCreateAddress,address.getStreet(),address.getSuite(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountry());
        //Second, get the address id to have an Address instance
        String sqlGetAddressID = "SELECT a.address_id FROM address a WHERE a.street = ?  AND a.city= ? AND a.region = ? AND a.postal = ? AND a.country = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetAddressID,address.getStreet(),address.getCity(),address.getRegion(),address.getPostal(),address.getCountry());
        UUID addressID = null;
        while(results.next()) {
            addressID = mapRowToAddress(results);
            address.setId(addressID);
        }
        //Create a field instance
        UUID fieldId = null;
        String sqlGetFieldId = "SELECT field_id FROM field WHERE name='Vue'";
        results = jdbcTemplate.queryForRowSet(sqlGetFieldId);
        while(results.next()){
            fieldId = (UUID)results.getObject("field_id");
        }
        field = new Field(fieldId,"Vue","Front End Development");
        //Create a skill instance
        skill = new Skill(field,10,"Pro in Vue");
        String sqlAddSkill = "INSERT INTO skill (field_id,experience,summary) VALUES (?,?,?);";
        jdbcTemplate.update(sqlAddSkill,skill.getField().getId(),skill.getExperience(),skill.getSummary());
        String sqlGetSkillId = "SELECT skill_id FROM skill WHERE skill.field_id = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetSkillId, fieldId);
        skillId = null;
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
        LocalDate birthdate = LocalDate.parse("1980-11-10");
        LocalDate hireddate = LocalDate.parse("2000-11-10");
        employee = new Employee("Elon","Musk",address,"elon@tesla.com","emusk@perficient.com",birthdate,hireddate,"Technical Consultant","Adobe",skills,assignedTo);
        employee.setAddress(address);

        dao = new JdbcSkillDAO(dataSource);
        employeeDAO = new JdbcEmployeeDAO(dataSource);

        //Second field and skill instance
        //Create Test Field
        UUID testFieldId = null;
        String sqlGetTestFieldId = "SELECT field_id FROM field WHERE name='Java'";
        results = jdbcTemplate.queryForRowSet(sqlGetTestFieldId);
        while(results.next()){
            testFieldId = (UUID)results.getObject("field_id");
        }
        testField = new Field(testFieldId,"Java","Software Development");
        //Create a skill instance
        testSkill = new Skill(testField,10,"Pro in Java");
    }
    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }
    @AfterClass
    public static void closeDataSource(){
        dataSource.destroy();
    }


    @Test
    public void findAllSkillsByEmployee() {
        //Arrange -
        int expectedSkillSize = dao.findAllSkillsByEmployee(employeeId).size();
        //Act
        dao.addSkillToEmployee(employeeId,skill);
        dao.addSkillToEmployee(employeeId,testSkill);

        int actualSkillSize = dao.findAllSkillsByEmployee(employeeId).size();
        //Assert
        Assert.assertEquals(expectedSkillSize + 2, actualSkillSize);
    }

    @Test
    public void addSkillToEmployee() {
        //Arrange
        int expectedSkillSize = dao.findAllSkillsByEmployee(employee.getId()).size();
        //Act
        dao.addSkillToEmployee(employeeId,testSkill);
        int actualSkillSize = dao.findAllSkillsByEmployee(employeeId).size();

        //Assert
        Assert.assertEquals(expectedSkillSize + 1, actualSkillSize);

    }

    @Test
    public void findSkillOfEmployeeById() {
        //Arrange - Employee has already been created

        employeeDAO.createEmployee(employee,address);
        //Get the newly created employee id.
        String sqlGetEmployeeID = "SELECT employee_id FROM employee WHERE firstname = ? AND lastname = ?;";
        results = jdbcTemplate.queryForRowSet(sqlGetEmployeeID,employee.getFirstName(),employee.getLastName());
        employeeId = null;
        while(results.next()){
            employeeId = (UUID)results.getObject("employee_id");
        }
        employee.setId(employeeId);

        //Act
        Skill actualSkill = new Skill();
        actualSkill = dao.findSkillOfEmployeeById(employeeId,skillId);
        //Assert
        Assert.assertTrue(employee.getSkills().get(0).getId().equals(actualSkill.getId()));
        Assert.assertEquals(employee.getSkills().get(0).getExperience(),actualSkill.getExperience());
        Assert.assertTrue(employee.getSkills().get(0).getField().getId().equals(actualSkill.getField().getId()));
        Assert.assertTrue(employee.getSkills().get(0).getSummary().equals(actualSkill.getSummary()));
    }

    @Test
    public void updateSkillOfEmployeeById() {
        //Arrange
        String expected = "Changed Summary";
        skill.setSummary("Changed Summary");

        //Act
        dao.updateSkillOfEmployeeById(employeeId,skillId,skill);
        Skill actualSkill =  new Skill();
        actualSkill= dao.findSkillOfEmployeeById(employeeId,skillId);

    }

    @Test
    public void removeSkillOfEmployeeById() {
        dao.addSkillToEmployee(employeeId,skill);
        int expectedSkillSize = dao.findAllSkillsByEmployee(employeeId).size();
        //Act
        dao.removeSkillOfEmployeeById(employeeId,skillId);
        int actualSkillSize = dao.findAllSkillsByEmployee(employeeId).size();
        //Assert
        Assert.assertEquals(expectedSkillSize -1 , actualSkillSize);
    }

   //Helper method
    private UUID mapRowToAddress(SqlRowSet results){
        UUID address_id = (UUID)results.getObject("address_id");
        return address_id;
    }
}