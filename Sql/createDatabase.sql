CREATE DATABASE `masi.analyseprog.rpg`;

CREATE TABLE `rpg.classe` (
  `ID_Classe` tinyint UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom_Classe` varchar(10) NOT NULL,
  `AtkMin` tinyint UNSIGNED NOT NULL,
  `AtkMax` tinyint UNSIGNED NOT NULL,
  `DefMin` tinyint UNSIGNED NOT NULL,
  `DefMax` tinyint UNSIGNED NOT NULL,
  `PVMin` tinyint UNSIGNED NOT NULL,
  `PVMax` tinyint UNSIGNED NOT NULL,
  `CritMin` tinyint UNSIGNED NOT NULL,
  `CritMax` tinyint UNSIGNED NOT NULL,
  `ParadeMin` tinyint UNSIGNED NOT NULL,
  `ParadeMax` tinyint UNSIGNED NOT NULL,
  `InitMin` tinyint UNSIGNED NOT NULL,
  `InitMax` tinyint UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_Classe`)
);

CREATE TABLE IF `rpg.nom` (
  `ID_Nom` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Nom`)
);

CREATE TABLE `rpg.perso` (
  `ID_Perso` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Nom` int UNSIGNED NOT NULL,
  `ID_Prenom` int UNSIGNED NOT NULL,
  `ID_Classe` tinyint UNSIGNED NOT NULL,
  `AtkVal` tinyint UNSIGNED NOT NULL DEFAULT '0',
  `DefVal` tinyint UNSIGNED NOT NULL DEFAULT '0',
  `PVVal` tinyint UNSIGNED NOT NULL DEFAULT '0',
  `CritVal` tinyint UNSIGNED NOT NULL DEFAULT '0',
  `ParadeVal` tinyint UNSIGNED NOT NULL DEFAULT '0',
  `InitVal` tinyint UNSIGNED NOT NULL DEFAULT '0',
  `IsDeath` tinyint(1) NOT NULL DEFAULT '0',
  `NbrCombat` int UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Perso`),
  KEY `ID_Nom` (`ID_Nom`,`ID_Prenom`,`ID_Classe`),
  KEY `ID_Prenom` (`ID_Prenom`),
  KEY `ID_Classe` (`ID_Classe`)
);

CREATE TABLE `rpg.prenom` (
  `ID_Prenom` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `Prenom` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Prenom`)
);

CREATE TABLE `rpg.stat_combat` (
  `ID_StatCombat` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `DateCombat` datetime NOT NULL,
  `ID_EquipeVainqueur` int UNSIGNED NOT NULL,
  `ID_EquipeVaincu` int UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_StatCombat`),
  KEY `ID_EquipeVainqueur` (`ID_EquipeVainqueur`,`ID_EquipeVaincu`),
  KEY `ID_EquipeVaincu` (`ID_EquipeVaincu`)
);

CREATE TABLE `rpg.stat_equipe` (
  `ID_StatEquipe` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `TotalAtk` int NOT NULL,
  `TotalDegats` int NOT NULL,
  `TotalPV` int NOT NULL,
  `TotalSoins` int NOT NULL,
  `ID_Strat` int UNSIGNED NOT NULL,
  `NbrGuerriers` tinyint NOT NULL,
  `NbrMage` tinyint NOT NULL,
  `NbrVoleurs` tinyint NOT NULL,
  `NbrPretre` tinyint NOT NULL,
  `NbrSurvivant` tinyint NOT NULL,
  PRIMARY KEY (`ID_StatEquipe`),
  KEY `ID_Strat` (`ID_Strat`)
);

CREATE TABLE `rpg.stat_perso` (
  `ID_StatPerso` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `ID_Perso` int UNSIGNED NOT NULL,
  `PVPerdu` int NOT NULL,
  `PVGagner` int NOT NULL,
  `IsFirstCombat` tinyint(1) NOT NULL,
  `ID_Stat_Equipe` int UNSIGNED NOT NULL,
  PRIMARY KEY (`ID_StatPerso`),
  KEY `ID_Perso` (`ID_Perso`,`ID_Stat_Equipe`),
  KEY `ID_Stat_Equipe` (`ID_Stat_Equipe`)
);

CREATE TABLE `rpg.strat` (
  `ID_Strat` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nom_Strat` varchar(50) NOT NULL,
  `Commentaire_Strat` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_Strat`)
);