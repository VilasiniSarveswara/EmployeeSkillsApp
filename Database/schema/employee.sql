BEGIN TRANSACTION;
CREATE TYPE roles AS ENUM ('Technical Consultant','Project Manager','Director','Chief');
CREATE TYPE businessUnits AS ENUM('Digital Experience Group','Adobe','IBM NBU','API Management');
COMMIT TRANSACTION;

BEGIN TRANSACTION;
DROP TABLE IF EXISTS employee;
CREATE TABLE employee(
	employee_id uuid DEFAULT uuid_generate_v4(),
	firstName varchar(50) NOT NULL,
	lastName varchar(50) NOT NULL,
	address uuid NOT NULL,
	contactEmail varchar(50),
	companyEmail varchar(50) NOT NULL,
	birthDate date NOT NULL,
	hiredDate date NOT NULL,
	role roles NOT NULL,
	businessUnit businessUnits,	
	assignedTo uuid,
	CONSTRAINT employee_employee_id PRIMARY KEY(employee_id),
	CONSTRAINT fk_address_address_id FOREIGN KEY(address) REFERENCES address(address_id)		
);
COMMIT TRANSACTION;

BEGIN TRANSACTION;
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role) 
VALUES ('Mark','Chestnut','3031cb71-5afd-43fa-9ffa-791c2cb2afae','markc@gmail.com','mchestnut@perficient.com','1970-09-23','2000-01-01','Chief');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Peter','Parker','dfad646f-af31-4594-ac58-45ea6da3bd63','peterp@gmail.com','pparker@perficient.com','1973-03-09','2000-03-01','Director','Digital Experience Group','a8911043-2fe8-441e-b9c2-9de4d8dd585c');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Mathew','Long','40f6c76f-265e-4bc9-8db0-5065e2abb415','mathewl@gmail.com','mlong@perficient.com','1972-12-16','2000-03-01','Director','Adobe','a8911043-2fe8-441e-b9c2-9de4d8dd585c');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Dev','Patel','ba54a63e-2058-4fa0-8a9d-39e41f51e48b','devp@gmail.com','dpatel@perficient.com','1975-05-25','2000-05-01','Director','IBM NBU','a8911043-2fe8-441e-b9c2-9de4d8dd585c');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Sid','Shah','e4e78b01-8f64-47e6-9717-69a3b59d756d','sids@gmail.com','sshah@perficient.com','1975-08-02','2000-05-01','Director','API Management','a8911043-2fe8-441e-b9c2-9de4d8dd585c');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Hema','Subramaniam','67d64310-b55f-4e2d-9526-c58e7855c706','hemas@gmail.com','hsubramaniam@perficient.com','1980-01-02','2003-01-01','Project Manager','API Management','f7ab0c09-36da-417e-8f3b-bc79e2236eb2');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Akash','Nair','6b0d146a-c7b4-4a48-b9b1-81d4de7b31fd','akashn@gmail.com','anair@perficient.com','1980-01-02','2003-01-01','Technical Consultant','API Management','3b13b6ee-f1bd-41a3-95e1-03f3e56dd644');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Jothi','Narayan','def482cc-3e7b-4ad0-ab41-68c5198a68d7','jothin@gmail.com','jnarayan@perficient.com','1980-01-02','2003-01-01','Technical Consultant','API Management','3b13b6ee-f1bd-41a3-95e1-03f3e56dd644');

INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Raj','Gowda','ae4d4faf-8295-4bcb-9418-e0240ec469cc','rajg@gmail.com','rgowda@perficient.com','1977-12-05','2002-01-31','Project Manager','IBM NBU','9073c05a-9c97-43df-950b-50d184e8281d');

INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Shri','Kapur','94984739-fe58-4cd6-8c40-7703b0c7dbe0','shrik@gmail.com','skapur@perficient.com','1980-01-03','2002-05-01','Technical Consultant','IBM NBU','918d6818-0841-4503-a75c-21d30dfe0d5b');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Priya','John','74ea6d6e-7238-4c87-9649-651e61eba53e','priyaj@gmail.com','pjohn@perficient.com','1980-02-21','2002-05-01','Technical Consultant','IBM NBU','918d6818-0841-4503-a75c-21d30dfe0d5b');

INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Patrick','Chase','bc268103-5cd7-4bae-9da4-a06a24671797','patrickc@gmail.com','pchase@perficient.com','1975-12-16','2000-09-30','Project Manager','Adobe','66397e19-918f-41a6-ac88-10b94ffb0e0d');

INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Deborah','Lindberg','0b8037e3-6c15-43b9-a4a8-9a4267da9c52','deborahl@gmail.com','dlindeerg@perficient.com','1975-12-31','2000-10-30','Technical Consultant','Adobe','12585524-2dcc-4c27-9cda-dca4b81a6e49');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Bonnie','Brandenberg','9fbc5100-60c1-404f-9718-564831aeaea3','bonnieb@gmail.com','bbrandenberg@perficient.com','1976-07-01','2000-10-30','Technical Consultant','Adobe','12585524-2dcc-4c27-9cda-dca4b81a6e49');

INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Lyndsey','Mitchell','55b04625-de7a-46bb-a0b8-d509cc39b0c5','lyndseym@gmail.com','lmitchell@perficient.com','1976-03-09','2001-03-01','Project Manager','Digital Experience Group','5dc93dee-31c6-4dec-acff-a1c069134be0');

INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Adam','Einstein','e83d1119-66b6-4e7c-9f32-a59a6a7ba07e','adame@gmail.com','aeinstein@perficient.com','1977-01-21','2002-01-01','Technical Consultant','Digital Experience Group','dc6ce13c-c911-431d-a279-04fe22d34184');
INSERT INTO employee (firstName,lastName,address,contactEmail,companyEmail,birthDate,hiredDate,role,businessUnit,assignedTo) 
VALUES ('Eve','Fischer','22f2ef90-8ad0-41ba-bbc7-5e249ed99afd','evef@gmail.com','efischer@perficient.com','1977-05-18','2001-03-01','Technical Consultant','Digital Experience Group','dc6ce13c-c911-431d-a279-04fe22d34184');


COMMIT TRANSACTION;

SELECT * FROM employee WHERE role = 'Chief';

ALTER TABLE employee
ALTER COLUMN role TYPE varchar(20);


ALTER TABLE employee
ALTER COLUMN businessunit TYPE businessUnits;

ALTER TABLE employee 
ALTER COLUMN role TYPE roles(40);

alter table empl drop column _some_column;

SELECT a.address_id FROM address a WHERE a.street = '99 Monroe St' AND a.suite = 'Apt 99' AND a.city= 'Ann Arbor' AND a.region = 'MI' AND a.postal = '48399' AND a.address_country_code = 'US';
SELECT a.address_id FROM address a WHERE a.street = ? AND a.suite =? AND a.city= ? AND a.region = ? AND a.postal = ? AND a.address_country_code = ?;