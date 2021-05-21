BEGIN TRANSACTION;
DROP TABLE IF EXISTS employee_skill;
CREATE TABLE employee_skill(
employee_skill_id uuid DEFAULT uuid_generate_v4 (),
employee_id uuid ,
skill_id uuid,
CONSTRAINT fk_es_employee_id FOREIGN KEY(employee_id) REFERENCES employee(employee_id),
CONSTRAINT fk_skill_skill_id FOREIGN KEY(skill_id) REFERENCES skill(skill_id)
);
COMMIT TRANSACTION;

BEGIN TRANSACTION;
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('9e796557-f219-416d-9ef1-74f5526ff1ce','924af6ca-3f22-4162-8e78-c2cab5a39e68');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('9e796557-f219-416d-9ef1-74f5526ff1ce','8cd0bd0d-f333-4b85-aba0-9fac6d995ea2');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('77266b3b-178e-44d8-9402-a7ac0f5594d5','924af6ca-3f22-4162-8e78-c2cab5a39e68');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('77266b3b-178e-44d8-9402-a7ac0f5594d5','8cd0bd0d-f333-4b85-aba0-9fac6d995ea2');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('fd07a465-08a5-4dd4-b309-ca44a717fdca', '8cd0bd0d-f333-4b85-aba0-9fac6d995ea2');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('fd07a465-08a5-4dd4-b309-ca44a717fdca', 'b8fc72c5-1a44-4a9c-848d-5071cc8501b0');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('fd07a465-08a5-4dd4-b309-ca44a717fdca', '3dfa370b-0545-4db1-905b-25f699ba9def');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('fd07a465-08a5-4dd4-b309-ca44a717fdca', '2fa3b01b-d4ef-4a26-a74a-87df5af91c38');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('a1df1180-9b07-41a3-b135-a0ac5c5cccbc', '8cd0bd0d-f333-4b85-aba0-9fac6d995ea2');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('a1df1180-9b07-41a3-b135-a0ac5c5cccbc', 'b8fc72c5-1a44-4a9c-848d-5071cc8501b0');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('a1df1180-9b07-41a3-b135-a0ac5c5cccbc', '3dfa370b-0545-4db1-905b-25f699ba9def');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('a1df1180-9b07-41a3-b135-a0ac5c5cccbc', '2fa3b01b-d4ef-4a26-a74a-87df5af91c38');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('a1df1180-9b07-41a3-b135-a0ac5c5cccbc', '746be39b-a323-42f6-926e-f9856d1622d7');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('a1df1180-9b07-41a3-b135-a0ac5c5cccbc', '924af6ca-3f22-4162-8e78-c2cab5a39e68');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('f68dc451-d3f4-4d00-a0a0-f8554b302b73', '924af6ca-3f22-4162-8e78-c2cab5a39e68');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('22c4f58a-242d-472e-9ae2-aaeb986c150f', '924af6ca-3f22-4162-8e78-c2cab5a39e68');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('9a99f5ca-ff79-49d0-99f9-392a85700552', '3dfa370b-0545-4db1-905b-25f699ba9def');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('9a99f5ca-ff79-49d0-99f9-392a85700552', '2fa3b01b-d4ef-4a26-a74a-87df5af91c38');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('6289f28a-4919-4b1c-b493-390edd04ae1a', '3dfa370b-0545-4db1-905b-25f699ba9def');
INSERT INTO employee_skill (employee_id,skill_id) VALUES ('6289f28a-4919-4b1c-b493-390edd04ae1a', '2fa3b01b-d4ef-4a26-a74a-87df5af91c38');
COMMIT TRANSACTION;



