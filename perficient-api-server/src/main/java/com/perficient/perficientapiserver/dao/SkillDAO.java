package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Skill;

import java.util.List;
import java.util.UUID;

public interface SkillDAO {
    List<Skill> findAllSkillsByEmployee(UUID employeeId);
    void addSkillToEmployee(UUID employeeId,Skill skill);
    Skill findSkillFromEmployeeById(UUID employeeId, UUID skillId);
    void updateSkillFromEmployeeById(UUID employeeId, UUID skillId, Skill skill);
    void removeSkillFromEmployeeById(UUID employeeId, UUID skillID);
}
