create database dbagenda;
CREATE TABLE `dbagenda`.`contatos` (
  `idcon` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Telefone` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,

  PRIMARY KEY (`idcon`));
