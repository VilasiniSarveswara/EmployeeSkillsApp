package com.perficient.perficientapiserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.perficient.perficientapiserver.dao.JdbcEmployeeDAO;
import com.perficient.perficientapiserver.model.Address;
import com.perficient.perficientapiserver.model.Employee;
import com.perficient.perficientapiserver.model.Field;
import com.perficient.perficientapiserver.model.Skill;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    private JdbcEmployeeDAO employeeDAO;

    @Autowired
    private ObjectMapper om;

    public EmployeeController(JdbcEmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    @PostConstruct
    public void setUp() {
        om.registerModule(new JavaTimeModule());
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
        //From the JsonNode extract the employee object, address object and skill object
        Employee employee = om.readValue(object.toString(),Employee.class);
        JsonNode addressNode = object.path("address");
        JsonNode skillsNode = object.path("skills");
        //Each employee can have an array/list of skills.
        //Each skill has a 'Field' object
        List<Skill> skills = new ArrayList<>();
        int skillsNodeSize = skillsNode.size();
        for(int i=0;i < skillsNode.size();i++){

            Field field = om.readValue(skillsNode.path(i).path("field").toString(),Field.class);
            Skill skill = om.readValue(skillsNode.path(i).toString(), Skill.class);
            skill.setField(field);
            skills.add(skill);
        }
        Address address = om.readValue(addressNode.toString(),Address.class);
        //Set the list of skills to the employee
        employee.setSkills(skills);
        employeeDAO.createEmployee(employee,address);
    }

    @ApiOperation("Update a Perficient employee by ID")
    @RequestMapping(path="/employees/{id}", method = RequestMethod.PUT)
    public void updateEmployeeById(@RequestBody JsonNode object, @PathVariable UUID id) throws JsonProcessingException {
        Employee employeeToBeUpdated = om.readValue(object.toString(),Employee.class);
        JsonNode addressNode = object.path("address");
        Address address = om.readValue(addressNode.toString(),Address.class);
        employeeToBeUpdated.setAddress(address);

        JsonNode skillsNode = object.path("skills");
        List<Skill> skills = new ArrayList<>();
        for(int i= 0; i< skillsNode.size();i++){
            Field field = om.readValue(skillsNode.path(i).path("field").toString(),Field.class);
            Skill skill = om.readValue(skillsNode.path(i).toString(),Skill.class);
            skill.setField(field);
            skills.add(skill);
        }
        employeeToBeUpdated.setSkills(skills);
        employeeDAO.updateEmployee(id,employeeToBeUpdated);
    }

    @ApiOperation("Delete a Perficient Employee by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/employees/{id}", method = RequestMethod.DELETE)
    public void removeEmployeeById(@PathVariable UUID id){
    employeeDAO.deleteEmployee(id);
    }
}
