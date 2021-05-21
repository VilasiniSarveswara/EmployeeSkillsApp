DROP TABLE IF EXISTS Address;
CREATE TABLE Address
(
   address_id uuid DEFAULT uuid_generate_v4 (),
   street varchar(50) NOT NULL,
   suite varchar(50),
   city varchar(64) NOT NULL,
   region varchar(3) NOT NULL,
   postal varchar(7) NOT NULL,
   address_country_code character(2) NOT NULL,   
   CONSTRAINT pk_address_id PRIMARY KEY (address_id)
);

INSERT INTO Address (street,suite,city,region,postal,address_country_code) VALUES ('1785 Main St','Apt 456','Dearborn','MI','48370','US');
INSERT INTO Address (street,suite,city,region,postal,address_country_code) VALUES ('3745 White Trail Dr','Apt 721','Ann Arbor','MI','48104','US');
INSERT INTO Address (street,suite,city,region,postal,address_country_code) VALUES ('7110 Santa St','Apt 1024','Detroit','MI','48370','US');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('2735 Bush Av','Plano','TX','75024','US');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('4755 Martin St','Frisco','TX','75025','US');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('1875 Henry Dr','Plymouth','MI','48174','US');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('8199 Ford Av','Bangalore','KA','560089','IN');
INSERT INTO Address (street,suite,city,region,postal,address_country_code) VALUES ('Lawrence Rd','1','Mysore','KA','560001','IN');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('256-Poes Garden','Chennai','TN','600001','IN');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('1178-Long Garden','Chennai','TN','600020','IN');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('5th Main Rd Vijaya Nagar','Chennai','TN','600010','IN');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('15th Main Rd Koramangala','Bengaluru','KA','560040','IN');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('3M Shanthi Colony','Mysore','KA','571119','IN');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('1400 Oakman Blvd','Detroit','MI','48238','US');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('1407 S Harrison Rd','Irving','TX','75041','US');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('733 Broadway','London','ENG','SW579DR','GB');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('86 York Road','Birmingham','ENG','W789QM','GB');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('68 Woerdens Rd','Sydney','NSW','2333','AU');
INSERT INTO Address (street,city,region,postal,address_country_code) VALUES ('74 Alfred Street','Melbourne','VIC','3478','AU');











