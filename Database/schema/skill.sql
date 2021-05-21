BEGIN TRANSACTION;
create table Skill(
	skill_id uuid DEFAULT uuid_generate_v4(),
	field_id uuid NOT NULL,
	experience integer NOT NULL,
	summary varchar(200),
	CONSTRAINT pk_Skill_skill_id PRIMARY KEY(skill_id),
	CONSTRAINT fk_Skill_field_id FOREIGN KEY(field_id) REFERENCES Field(field_id)
);
COMMIT TRANSACTION;

BEGIN TRANSACTION;
INSERT INTO Skill (field_id,experience,summary) VALUES ('4298540b-2bb6-4efb-be0a-d4d2f0961a69',60,'Worked with multi-threading and generics');
INSERT INTO Skill (field_id,experience,summary) VALUES ('80d4c4d9-e0c4-4cba-8c5f-608deeb8d47d',50,'Expertise in SPAs');
INSERT INTO Skill (field_id,experience) VALUES ('80d4c4d9-e0c4-4cba-8c5f-608deeb8d47d',10);
INSERT INTO Skill (field_id,experience) VALUES ('c67d8527-2764-44aa-83e3-464c04fac7ad',0);
INSERT INTO Skill (field_id,experience,summary) VALUES ('ecdeec30-3d6b-4475-8769-e62f76468ed5',40,'Expertise in HTML');
INSERT INTO Skill (field_id,experience,summary) VALUES ('e6c9e07a-37bd-450f-b86a-05a08885e401',40,'Expertise in Grids & Flexboxes');
INSERT INTO Skill (field_id,experience,summary) VALUES ('7bdb9db2-2ab1-43d9-81de-994fc8669252',30,'Knowledge in Spring Frameworks');
INSERT INTO Skill (field_id,experience,summary) VALUES ('b87ffe6b-f774-4955-8c2b-b7c7351e7eb2',40,'Prowess in Components');
INSERT INTO Skill (field_id,experience,summary) VALUES ('9b0a0de0-99cf-4321-9ab8-17df3034d44a',40,'Ability to interact with databases');
INSERT INTO Skill (field_id,experience,summary) VALUES ('999aed0c-e9cf-45cc-8ee7-9a043b54673c',18,'Expertise in Functional and API Testing');
INSERT INTO Skill (field_id,experience,summary) VALUES ('ac237d9d-49c4-45af-a199-e8e3accb0051',10,'Expertise in TestNG framework & anotations');

COMMIT TRANSACTION;