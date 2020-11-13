CREATE VIEW combattant AS
SELECT 
	p.ID_Perso AS ID,
    pre.Prenom, 
    n.Nom, 
    c.Nom_Classe AS Classe, 
    p.AtkVal, 
    p.DefVal, 
    p.PVVal,
    p.CritVal, 
    p.ParadeVal, 
    p.InitVal, 
    p.NbrCombat
FROM perso AS p
INNER JOIN nom AS n ON n.ID_Nom = p.ID_Nom
INNER JOIN prenom AS pre ON pre.ID_Prenom = p.ID_Prenom
INNER JOIN classe AS c ON c.ID_Classe = p.ID_Classe
WHERE p.IsDeath = 0
order by rand();