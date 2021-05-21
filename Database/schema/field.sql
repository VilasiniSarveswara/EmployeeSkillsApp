BEGIN TRANSACTION;
create table Field(
	field_id uuid DEFAULT uuid_generate_v4(),
	name varchar(30) NOT NULL,
	type varchar(30) NOT NULL,
	CONSTRAINT pk_Field_field_id PRIMARY KEY(field_id)
);
COMMIT TRANSACTION;

BEGIN TRANSACTION;
INSERT INTO Field (name,type) VALUES ('Java', 'Software Development'),('JavaScript', 'Software Development'),
('C#', 'Software Development'),('C', 'Software Development'),('C++', 'Software Development'),('HTML', 'Software Development'),('CSS', 'Software Development'),
('Spring', 'Software Development'),('Vue', 'Front End Development'),('React', 'Front End Development'),('Angular', 'Front End Development'),('Spring Boot', 'Software Development'),('Selenium', 'Software Testing'),('TestNG', 'Software Testing'),('Quality Center', 'Software Testing'),
('PostgreSQL', 'Database'),('MySQL', 'Database'),('MongoDB', 'Database'),('AWS', 'Cloud Computing'),('MS Azure', 'Cloud Computing'),('Google Cloud', 'Cloud Computing');
COMMIT TRANSACTION;