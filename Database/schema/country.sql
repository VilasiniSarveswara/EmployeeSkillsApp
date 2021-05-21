CREATE TABLE country (
    code character(2) NOT NULL,
    name varchar(64) NOT NULL,    
    code2 character(3) NOT NULL,
    CONSTRAINT pk_country_code PRIMARY KEY (code)
);

INSERT INTO country (code, name, code2) VALUES ('US', 'United States','USA');
INSERT INTO country (code, name, code2) VALUES ('AU', 'Australia','AUS');
INSERT INTO country (code, name, code2) VALUES ('IN', 'India','IND');
INSERT INTO country (code, name, code2) VALUES ('BR', 'Brazil','BRA');
INSERT INTO country (code, name, code2) VALUES ('GB', 'United Kingdom','GBR');


