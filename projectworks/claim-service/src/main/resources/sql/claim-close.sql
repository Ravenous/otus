UPDATE 
        claim c
    SET
        status_id = 3,
		    time_end = now()
	WHERE c.id = :#id::bigint 