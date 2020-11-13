DELIMITER |
create trigger before_update_perso before update
on perso for each row 
begin
	if NEW.PVVal = 0 then
		set NEW.IsDeath = 1;
	end if;
end |
DELIMITER ;