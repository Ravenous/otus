SELECT 
id, 
lastname as "lastName", 
firstname as "firstName", 
middlename as "middleName", 
phone
FROM employee
WHERE id=:#id::bigint