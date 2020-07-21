UPDATE claim
	SET status_id=:#status_id, firstname=:#firstname, middlename=:#middlename, phone=:#phone
	WHERE  id=:#id::bigint;
