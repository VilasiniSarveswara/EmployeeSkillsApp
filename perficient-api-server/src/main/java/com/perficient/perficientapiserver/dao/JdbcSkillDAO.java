package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Field;
import com.perficient.perficientapiserver.model.Skill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JdbcSkillDAO implements SkillDAO {
    private JdbcTemplate jdbcTemplate;

    /*public JdbcSkillDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/

    public JdbcSkillDAO(DataSource datasource){
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }


    @Override
    public List<Skill> findAllSkillsByEmployee(UUID employeeId) {
        List<Skill> skills = new ArrayList<>();
        String sqlFindAllSkillsByEmployee = "SELECT es.skill_id,s.field_id," +
                "f.name,f.type,s.experience,s.summary FROM employee_skill es JOIN skill s ON es.skill_id = s.skill_id " +
                "JOIN field f ON s.field_id = f.field_id WHERE es.employee_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindAllSkillsByEmployee, employeeId);
        while(results.next()){
            Skill skill = mapRowToSkill(results);
            skills.add(skill);
        }
        return skills;
    }

    @Override
    public void addSkillToEmployee(UUID employeeId,Skill skill) {
        String sqlAddSkillToEmployee = "INSERT INTO Skill (field_id,experience,summary) VALUES (?,?,?);";
        jdbcTemplate.update(sqlAddSkillToEmployee,skill.getField().getId(),skill.getExperience(),skill.getSummary());

        String sqlGetSkillId = "SELECT skill_id FROM skill WHERE field_id = ? AND experience = ? AND summary = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSkillId,skill.getField().getId(),skill.getExperience(),skill.getSummary());
        UUID skillId = null;
        while(results.next()){
            skillId = (UUID)results.getObject("skill_id");
        }

        String sqlInsertEmployeeSkillId = "INSERT INTO employee_skill (employee_id,skill_id) VALUES (?,?);";
        jdbcTemplate.update(sqlInsertEmployeeSkillId,employeeId,skillId);

    }

    @Override
    public Skill findSkillOfEmployeeById(UUID employeeId, UUID skillId) {
        Skill skill = new Skill();
        String sqlFindSkillByEmployeeID =  "SELECT es.skill_id,s.field_id," +
                "f.name,f.type,s.experience,s.summary FROM employee_skill es JOIN skill s ON es.skill_id = s.skill_id " +
                "JOIN field f ON s.field_id = f.field_id WHERE es.employee_id = ? AND es.skill_id= ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlFindSkillByEmployeeID, employeeId,skillId);
        while(results.next()){
           skill = mapRowToSkill(results);
        }
        return skill;   
    }

    @Override
    public void updateSkillOfEmployeeById(UUID employeeId, UUID skillId, Skill skill) {
        String sqlUpdateSkillByID = "UPDATE skill SET field_id = ?,experience = ? ,summary = ? WHERE skill_id = ?;";
        jdbcTemplate.update(sqlUpdateSkillByID,skill.getField().getId(),skill.getExperience(),skill.getSummary(),skillId);

    }

    @Override
    public void removeSkillOfEmployeeById(UUID employeeId, UUID skillID) {
        String sqlRemoveSkillById = "DELETE FROM employee_skill WHERE employee_id = ? AND skill_id = ?;";
        jdbcTemplate.update(sqlRemoveSkillById,employeeId,skillID);

        String sqlRemoveFromSkill = "DELETE FROM skill WHERE skill_id = ?;";
        jdbcTemplate.update(sqlRemoveFromSkill,skillID);
    }
    //Helper methods
    Skill mapRowToSkill(SqlRowSet results){
        Skill skill = new Skill();
        Field field = new Field();
        skill.setId((UUID)results.getObject("skill_id"));
        field.setId((UUID)results.getObject("field_id"));
        field.setName(results.getString("name"));
        field.setType(results.getString("type"));
        skill.setField(field);
        skill.setExperience(results.getInt("experience"));
        skill.setSummary(results.getString("summary"));
        return skill;
    }
}
