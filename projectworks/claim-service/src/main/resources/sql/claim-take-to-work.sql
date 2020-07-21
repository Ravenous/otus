UPDATE 
        claim c
    SET
        status_id = 2,
        employee_id = sel.employee_id,
	    time_begin = now()
    FROM (
        select cl.id, e.id as employee_id from claim cl
        inner join employee e on (e.phone = :#msisdn)
        where cl.status_id = 1
        order by cl.time_limit, cl.critical_id
        limit 1
      ) sel
	WHERE c.id = sel.id