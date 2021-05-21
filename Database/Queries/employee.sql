--Fetch a particular employee

SELECT e.employee_id,e.firstName,e.lastName,a.street,a.city,a.region,a.postal,a.address_country_code FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = '6289f28a-4919-4b1c-b493-390edd04ae1a';
--SELECT e.employee_id,e.firstName,e.lastName,a.street,a.city,a.region,a.postal,a.address_country_code FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;

--Fetch all employees

SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id;
SELECT e.employee_id,e.firstName,e.lastName,a.street,a.suite,a.city,a.region,a.postal,a.address_country_code,e.contactemail,e.companyemail,e.birthdate,e.hireddate,e.role,e.businessunit,e.assignedto FROM employee e JOIN address a ON e.address = a.address_id WHERE e.employee_id = ?;


--Create Employee
INSERT INTO address(street,suite,city,region,postal,address_country_code) VALUES ('3692 Silver Trail Dr','Apt 227','Dearborn','MI','48104','US');
INSERT INTO address(street,suite,city,region,postal,address_country_code) VALUES (?,?,?,?,?,?);
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) VALUES (?,?,?,?,?,?,?,?,?,?);

SELECT a.address_id FROM employee e JOIN address a ON e.address = a.address_id WHERE firstName = 'Eve';

SELECT * FROM employee_skill WHERE employee_id='6289f28a-4919-4b1c-b493-390edd04ae1a';
DELETE FROM employee_skill WHERE employee_id = '6289f28a-4919-4b1c-b493-390edd04ae1a';