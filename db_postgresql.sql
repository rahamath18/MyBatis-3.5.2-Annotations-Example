CREATE TABLE company.public.user (
  id bigserial NOT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL
);

create or replace procedure getuserbyid(
   user_id int
)
language plpgsql    
as $$
begin
    SELECT * FROM company.public.user WHERE id = user_id;
end;$$

CALL getUserById(2);