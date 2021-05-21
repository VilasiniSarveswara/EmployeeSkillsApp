package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Field;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JdbcFieldDAO implements FieldDAO{
    private JdbcTemplate jdbcTemplate;

    public JdbcFieldDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Field> getFields() {
        List<Field> fields = new ArrayList<>();
        String sqlGetFields = "SELECT * FROM field";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetFields);
        while(results.next()){
            Field field = new Field();
            field.setId((UUID)results.getObject("field_id"));
            field.setName(results.getString("name"));
            field.setType(results.getString("type"));
            fields.add(field);
        }
        return fields;
    }

    @Override
    public void createField(Field field) {
        String sqlCreateField = "INSERT INTO field (name,type) VALUES (?,?)";
        jdbcTemplate.update(sqlCreateField,field.getName(),field.getType());
    }
}
