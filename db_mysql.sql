CREATE TABLE identity (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE PROCEDURE getIdentityById
(IN identity_id int)
BEGIN
  SELECT * FROM identity
  WHERE id = identity_id;
end;


CALL getIdentityById(2);
