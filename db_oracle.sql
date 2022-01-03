create table person (
	ID number(10,0) generated as identity,
	email varchar(255) DEFAULT NULL,
	first_name varchar(255) DEFAULT NULL,
	last_name varchar(255) DEFAULT NULL,
	primary key (ID)
);


create or REPLACE PROCEDURE getperson
(in_id IN person.ID%TYPE,
 out_id OUT person.ID%TYPE,
 out_email OUT person.EMAIL%TYPE,
 out_first_name OUT person.FIRST_NAME%TYPE,
 out_last_name OUT person.LAST_NAME%TYPE
 )
AS
BEGIN
  SELECT id, email, first_name, last_name 
  INTO out_id, out_email, out_first_name, out_last_name
  FROM person
  WHERE id = in_id;  
END;


CALL COMPANY.GETPERSON(:IN_ID, ?, ?, ?, ?);
