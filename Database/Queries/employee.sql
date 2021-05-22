--Fetch a particular employee

SELECT e.employee_id,e.firstName,e.lastName,a.street,a.city,a.region,a.postal,a.address_country_code FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = '6289f28a-4919-4b1c-b493-390edd04ae1a';
--SELECT e.employee_id,e.firstName,e.lastName,a.street,a.city,a.region,a.postal,a.address_country_code FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;

--Fetch all employees

SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id;
SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;


--Create Employee
INSERT INTO address(street,suite,city,region,postal,address_country_code) VALUES ('3692 Silver Trail Dr','Apt 227','Dearborn','MI','48104','US');
INSERT INTO address(street,suite,city,region,postal,address_country_code) VALUES (?,?,?,?,?,?);
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) VALUES (?,?,CAST(? as UUID),?,?,?,?,CAST(? AS roles),CAST (? AS businessunits),?);



SELECT a.address_id FROM employee e JOIN address a ON e.address = a.address_id WHERE firstName = 'Eve';

SELECT * FROM employee_skill WHERE employee_id='6289f28a-4919-4b1c-b493-390edd04ae1a';
DELETE FROM employee_skill WHERE employee_id = '77266b3b-178e-44d8-9402-a7ac0f5594d5';

DELETE FROM employee WHERE employee_id ='9d8670b8-ca77-43ae-ad18-e69ee61187a9';
DELETE FROM address where address_id ='46ba712e-5f10-474e-a8d6-c2674104db06';

ALTER TABLE employee 
ALTER COLUMN role TYPE roles using role::roles;


ALTER TABLE employee 
ALTER COLUMN businessUnit TYPE businessUnits using businessUnit::businessUnits;

DELETE FROM employee WHERE firstname ='Eve' AND lastname= 'Fischer';
--Query to Select all employees and address information
SELECT e.employee_id,e.firstName,e.lastName,a.address_id,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto 
FROM employee e JOIN address a ON e.address = a.address_id;

--QUERY to UPDATE EMPLOYEE TABLE
UPDATE employee SET employee_id =?,firstName = ?,lastName=?,address=?,contactemail=?,companyemail=?,birthdate=?,hireddate=?,role= CAST(? AS roles),businessunit=CAST(? AS businessunits),assignedto=? WHERE employee_id=?