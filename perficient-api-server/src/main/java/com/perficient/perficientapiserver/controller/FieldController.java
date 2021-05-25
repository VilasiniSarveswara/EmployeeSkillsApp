package com.perficient.perficientapiserver.controller;

import com.perficient.perficientapiserver.dao.FieldDAO;
import com.perficient.perficientapiserver.model.Field;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FieldController {
    @Autowired
    private FieldDAO dao;

    @ApiOperation("Allows user to get all fields")
    @RequestMapping(path="/fields", method = RequestMethod.GET)
    public List<Field> getFields(){
        List<Field> fields = dao.getFields();
        return fields;
    }

    @ApiOperation("Allows user to add a new field")
    @RequestMapping(path="/fields", method = RequestMethod.POST)
    public void createField(@RequestBody Field field){
        dao.createField(field);
    }
}
