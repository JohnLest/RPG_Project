DELIMITER | 
CREATE PROCEDURE newPerso ()
BEGIN
		declare maxPrenom int;
    declare maxNom int;
    declare randPrenom int;
    declare randNom int;
    declare randClasse tinyint;
    declare atkMin tinyint unsigned;
    declare atkMax tinyint unsigned;
    declare atkVal tinyint unsigned;
    declare defMin tinyint unsigned;
    declare defMax tinyint unsigned;
    declare defVal tinyint unsigned;
    declare pvMin tinyint unsigned;
    declare pvMax tinyint unsigned;
    declare pvVal tinyint unsigned;
    declare critMin tinyint unsigned;
    declare critMax tinyint unsigned;
    declare critVal tinyint unsigned;
    declare parMin tinyint unsigned;
    declare parMax tinyint unsigned;
    declare parVal tinyint unsigned;
    declare initMin tinyint unsigned;
    declare initMax tinyint unsigned;
    declare initVal tinyint unsigned;
    
    select nom.ID_nom into randNom
	from nom
    order by rand()
    LIMIT 1;
 
	select pre.ID_Prenom into randPrenom
	from prenom as pre
    order by rand()
    LIMIT 1;
    
    select 
		c.ID_Classe, c.AtkMin, c.AtkMax, c.DefMin, c.DefMax, c.PVMin, c.PVMax, c.CritMin, c.CritMax, c.ParadeMin, c.ParadeMax, c.InitMin, c.InitMax 
	
    into
		randClasse,  atkMin, atkMax, defMin, defMax, pvMin, pvMax, critMin, critMax, parMin, parMax, initMin, initMax
    from classe as c
    order by rand()
    limit 1;
	
    select round((atkMin) + (RAND() * (atkMax - atkMin))) into atkVal;
    select round((defMin) + (RAND() * (defMax - defMin))) into defVal;
    select round((pvMin) + (RAND() * (pvMax - pvmin))) into pvVal;
    select round((critMin) + (RAND() * (critMax - critMin))) into critVal;
    select round((parMin) + (RAND() * (parMax - parMin))) into parVal;
    select round((initMin) + (RAND() * (initMax - initMin))) into initVal;
	
    if parVal = -1 then 
		set parVal = 0;
	end if;
    
    insert into perso 
		(ID_Nom, ID_Prenom, ID_Classe, AtkVal, DefVal, PVVal, CritVal, ParadeVal, InitVal, IsDeath, NbrCombat)
	values 
		(randNom, randPrenom, randClasse, atkVal, defVal, pvVal, critVal, parVal, initVal, 0, 0);
    select 
		randNom, randPrenom, randClasse, atkVal, defVal, pvVal, critVal, parVal, initVal, 0 as Isdeath, 0 as NbrCombat;

END|