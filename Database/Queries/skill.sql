
--Select all the skills associated with an employee
SELECT es.skill_id,s.field_id,f.name,f.type,s.experience,s.summary 
FROM employee_skill es 
JOIN skill s ON es.skill_id = s.skill_id 
JOIN field f ON s.field_id = f.field_id 
WHERE es.employee_id = 'a1df1180-9b07-41a3-b135-a0ac5c5cccbc';

--Find a technical skill, from a Perficient employee, by ID.
 SELECT es.skill_id,s.field_id,f.name,f.type,s.experience,s.summary 
FROM employee_skill es 
JOIN skill s ON es.skill_id = s.skill_id 
JOIN field f ON s.field_id = f.field_id 
WHERE es.employee_id = 'a1df1180-9b07-41a3-b135-a0ac5c5cccbc' AND es.skill_id= '746be39b-a323-42f6-926e-f9856d1622d7';

--Delete a technical skill, from a Perficient employee, by ID.
DELETE FROM employee_skill WHERE employee_id = 'a1df1180-9b07-41a3-b135-a0ac5c5cccbc' AND skill_id = '746be39b-a323-42f6-926e-f9856d1622d7';

--Add a technical skill to a Perficient employee.
INSERT INTO Skill (field_id,experience,summary) VALUES ('f84db058-e862-4e84-a1d0-e12eafa4b0de',10,'Expertise in Software Testing');
SELECT skill_id FROM skill WHERE field_id ='f84db058-e862-4e84-a1d0-e12eafa4b0de'AND experience = 10 AND summary ='Expertise in Software Testing';
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('a1df1180-9b07-41a3-b135-a0ac5c5cccbc','cc738102-8310-43e1-953a-011e781357d5');

SELECT skill_id FROM skill WHERE field_id = CAST(? AS UUID) AND experience = CAST(? AS integer) AND summary = ?;

DELETE FROM skill WHERE skill_id ='3389e789-d774-4c5c-93a4-8977d0b5b1d6';

--updateSkillFromEmployeeById
UPDATE skill 
SET field_id = 'f84db058-e862-4e84-a1d0-e12eafa4b0de',experience = 24,summary = 'Unit Testing using jUnit' 
WHERE skill_id = '2d9b3c5b-0e6e-40f7-8457-17508def08f8'; 

UPDATE skill SET field_id = ?,experience = ? ,summary = ? WHERE skill_id = ?; 

2f7b50b6-4918-496a-a501-0213c5c43a98
c67d8527-2764-44aa-83e3-464c04fac7ad