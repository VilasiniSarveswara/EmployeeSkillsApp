package com.perficient.perficientapiserver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.perficient.perficientapiserver.dao.SkillDAO;
import com.perficient.perficientapiserver.model.Field;
import com.perficient.perficientapiserver.model.Skill;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@RestController()
@CrossOrigin
public class SkillsController {
    @Autowired
    private SkillDAO dao;

    @Autowired
    private ObjectMapper objectmapper;

    public SkillsController(SkillDAO dao) {
        this.dao = dao;
    }
    @PostConstruct
    public void setUp() { objectmapper.registerModule(new JavaTimeModule());
    }

    @ApiOperation("Get all technical skills from a Perficient employee.")
    @RequestMapping(path="/employees/{employeeId}/skills",method = RequestMethod.GET)
    public List<Skill> findAllSkillsByEmployee(@PathVariable UUID employeeId){
        List<Skill> skills = dao.findAllSkillsByEmployee(employeeId);
        return skills;
    }

    @ApiOperation("Find a technical skill, from a Perficient employee, by ID.")
    @RequestMapping(path="/employees/{employeeId}/skills/{skillId}",method = RequestMethod.GET)
    public Skill findAllSkillsByEmployee(@PathVariable UUID employeeId, @PathVariable UUID skillId){
        Skill skill = dao.findSkillFromEmployeeById(employeeId,skillId);
        return skill;
    }

    @ApiOperation("Delete a technical skill, from a Perficient employee, by ID.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/employees/{employeeId}/skills/{skillId}",method = RequestMethod.DELETE)
    public void removeSkillFromEmployeeById(@PathVariable UUID employeeId, @PathVariable UUID skillId){
        dao.removeSkillFromEmployeeById(employeeId,skillId);
    }

    @ApiOperation("Add a technical skill to a Perficient employee.")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/employees/{employeeId}/skills",method = RequestMethod.POST)
    public void addSkillToEmployee(@RequestBody JsonNode object, @PathVariable UUID employeeId) throws JsonProcessingException {

        JsonNode fieldNode = object.path("field");
        Field field = objectmapper.readValue(fieldNode.toString(), Field.class);
        Skill skill = objectmapper.readValue(object.toString(),Skill.class);
        skill.setField(field);
        dao.addSkillToEmployee(employeeId,skill);
    }

    @ApiOperation("Update a technical skill, from a Perficient employee, by ID.")
    @RequestMapping(path="/employees/{employeeId}/skills/{skillId}",method = RequestMethod.PUT)
    public void updateSkillFromEmployeeById(@RequestBody JsonNode object, @PathVariable UUID employeeId,@PathVariable UUID skillId) throws JsonProcessingException {

        JsonNode fieldNode = object.path("field");
        Field field = objectmapper.readValue(fieldNode.toString(), Field.class);
        Skill skill = objectmapper.readValue(object.toString(),Skill.class);
        skill.setField(field);
        dao.updateSkillFromEmployeeById(employeeId,skillId,skill);
    }

}
