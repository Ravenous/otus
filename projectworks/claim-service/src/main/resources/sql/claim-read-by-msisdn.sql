SELECT
c.id, 
topic, 
description, 
status_id as "statusId", 
cs.name as "statusName", 
critical_id as "criticalId", 
cc.name as "criticalName", 
time_open as "timeOpen", 
time_limit as "timeLimit", 
time_begin as "timeBegin", 
time_end as "timeEnd", 
employee_id as "employeeId"  
FROM claim c
LEFT JOIN claim_status cs ON (cs.id=c.status_id)
LEFT JOIN claim_critical cc ON (cc.id=c.critical_id)
LEFT JOIN employee e ON (e.id=c.employee_id)
WHERE e.phone=:#msisdn AND (status_id=1 OR status_id=2)
ORDER BY c.status_id