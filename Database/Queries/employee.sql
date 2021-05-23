--Fetch a particular employee

SELECT e.employee_id,e.firstName,e.lastName,a.street,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,es.skill_id,s.field_id,f.name,f.type,s.experience,s.summary,e.assignedto 
FROM employee e 
JOIN address a ON e.address = a.address_id 
JOIN employee_skill es ON e.employee_id = es.employee_id
JOIN skill s ON es.skill_id = s.skill_id
JOIN field f ON s.field_id = f.field_id
WHERE e.employee_id = '632ac580-6b7d-4980-91d7-14bcadba51a9';


SELECT e.firstName,e.lastName,a.street,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto
FROM address a
JOIN employee e  ON a.address_id = e.address
WHERE e.employee_id = '632ac580-6b7d-4980-91d7-14bcadba51a9';
---------------------------------------------------
SELECT employee_id FROM employee WHERE firstname = 'Jeremy' AND lastname = 'Young';


----------------------------------------------------

--SELECT e.employee_id,e.firstName,e.lastName,a.street,a.city,a.region,a.postal,a.address_country_code FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;

--SELECT the skills of an employee
SELECT es.employee_id,es.skill_id,s.field_id,f.name,f.type,s.experience,s.summary 
FROM employee_skill es 
JOIN skill s ON es.skill_id = s.skill_id 
JOIN field f ON s.field_id = f.field_id 
WHERE es.employee_id = '9e796557-f219-416d-9ef1-74f5526ff1ce';
-----------------------------------------------------------------------





----------------------------------------------------------------------

--Fetch all employees

SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto 
FROM employee e 
JOIN address a ON e.address = a.address_id
JOIN employee_skill es ON e.employee_id = es.employee_id;
JOIN skill s ON es.skill_id = s.skill_id,
JOIN field f ON s.field_id= f.field_id;

SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;


--Create Employee
INSERT INTO address(street,suite,city,region,postal,address_country_code) VALUES ('3692 Silver Trail Dr','Apt 227','Dearborn','MI','48104','US');
INSERT INTO address(street,suite,city,region,postal,address_country_code) VALUES (?,?,?,?,?,?);
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) VALUES (?,?,CAST(? as UUID),?,?,?,?,CAST(? AS roles),CAST (? AS businessunits),?);



SELECT a.address_id FROM employee e JOIN address a ON e.address = a.address_id WHERE firstName = 'Eve';

SELECT * FROM employee_skill WHERE employee_id='044d0cf7-a1ef-435c-8e76-5e795e82735c';
DELETE FROM employee_skill WHERE employee_id = '044d0cf7-a1ef-435c-8e76-5e795e82735c';

DELETE FROM employee WHERE employee_id ='a5a1dc42-4873-47fa-8e14-beb89ac8e02e';
DELETE FROM address where address_id ='f87ef1c4-c034-4fc9-a995-bbbf5865e6b1';

BEGIN TRANSACTION;
SELECT address FROM employee WHERE employee_id = '044d0cf7-a1ef-435c-8e76-5e795e82735c');
ROLLBACK TRANSACTION;
COMMIT TRANSACTION;

SELECT a.address_id FROM address a WHERE a.street = ? AND a.suite =? AND a.city= ? AND a.region = ? AND a.postal = ? AND a.address_country_code = 'IN';

SELECT a.address_id FROM address a WHERE a.street = ? AND a.city= ? AND a.region = ? AND a.postal = ? AND a.address_country_code = ?;

ALTER TABLE employee 
ALTER COLUMN role TYPE roles using role::roles;


ALTER TABLE employee 
ALTER COLUMN businessUnit TYPE businessUnits using businessUnit::businessUnits;

DELETE FROM employee WHERE firstname ='Eve' AND lastname= 'Fischer';
--Query to Select all employees and address information
SELECT e.employee_id,e.firstName,e.lastName,a.address_id,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto 
FROM employee e JOIN address a ON e.address = a.address_id;

--QUERY to UPDATE EMPLOYEE TABLE
UPDATE employee SET employee_id =?,firstName = ?,lastName=?,address=?,contactemail=?,companyemail=?,birthdate=?,hireddate=?,role= CAST(? AS roles),businessunit=CAST(? AS businessunits),assignedto=? WHERE employee_id=?;

--QUERY to UPDATE ADDRESS TABLE
UPDATE address SET street =?,suite = ?,city = ?,region = ?,postal = ?,country = ? WHERE address_id = CAST(? AS UUID);

--Alter column name
ALTER TABLE address 
RENAME COLUMN address_country_code TO country;