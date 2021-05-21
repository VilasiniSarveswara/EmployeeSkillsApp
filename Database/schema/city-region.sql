BEGIN TRANSACTION;

DROP TABLE IF EXISTS city;
CREATE TABLE city (
    id serial NOT NULL,
    name varchar(64) NOT NULL,
    countrycode character(3) NOT NULL,
    region varchar(3) NOT NULL,   
    CONSTRAINT pk_city_id PRIMARY KEY (id),
    CONSTRAINT fk_countrycode FOREIGN KEY (countrycode) REFERENCES country(code)
);
COMMIT TRANSACTION;

INSERT INTO city (name,countrycode,region) VALUES ('London','GB','ENG');
INSERT INTO city (name,countrycode,region) VALUES ('Birmingham','GB','ENG');
INSERT INTO city (name,countrycode,region) VALUES ('Glasgow','GB','ENG');
INSERT INTO city (name,countrycode,region) VALUES ('Edinburgh','GB','ENG');
INSERT INTO city (name,countrycode,region) VALUES ('Sheffield','GB','ENG');
INSERT INTO city (name,countrycode,region) VALUES ('Manchester','GB','ENG');
INSERT INTO city (name,countrycode,region) VALUES ('Sydney','AU','NSW');
INSERT INTO city (name,countrycode,region) VALUES ('Melbourne','AU','VIC');
INSERT INTO city (name,countrycode,region) VALUES ('Brisbane','AU','QLD');
INSERT INTO city (name,countrycode,region) VALUES ('Chennai','IN','TN');
INSERT INTO city (name,countrycode,region) VALUES ('Bangalore','IN','KA');
INSERT INTO city (name,countrycode,region) VALUES ('Mysore','IN','KA');
INSERT INTO city (name,countrycode,region) VALUES ('Hyderabad','IN','AP');
INSERT INTO city (name,countrycode,region) VALUES ('Detroit','US','MI');
INSERT INTO city (name,countrycode,region) VALUES ('Ann Arbor','US','MI');
INSERT INTO city (name,countrycode,region) VALUES ('Plymouth','US','MI');
INSERT INTO city (name,countrycode,region) VALUES ('Dearborn','US','MI');
INSERT INTO city (name,countrycode,region) VALUES ('Plano','US','TX');
INSERT INTO city (name,countrycode,region) VALUES ('Frisco','US','TX');
INSERT INTO city (name,countrycode,region) VALUES ('Irving','US','TX');
COMMIT TRANSACTION;


DROP TABLE IF EXISTS city;
