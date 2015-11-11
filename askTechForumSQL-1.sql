SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `askTechForum` ;
CREATE SCHEMA IF NOT EXISTS `askTechForum` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `askTechForum` ;

-- -----------------------------------------------------
-- Table `askTechForum`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`Usuario` (
  `idUsuario` INT NOT NULL,
  `nome` VARCHAR(150) NULL,
  `dt_nasc` DATE NULL,
  `admin` TINYINT(1) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `localizacao` VARCHAR(45) NULL,
  `senha` VARCHAR(8) NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `askTechForum`.`PERGUNTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`PERGUNTA` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`PERGUNTA` (
  `idPergunta` INT NOT NULL,
  `titulo` VARCHAR(150) NOT NULL,
  `descricao` VARCHAR(1000) NOT NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idPergunta`),
  INDEX `ser_idx` (`idUsuario` ASC),
  CONSTRAINT `fk_usuario_pergunta`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `askTechForum`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `askTechForum`.`RESPOSTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`RESPOSTA` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`RESPOSTA` (
  `idResposta` INT NOT NULL,
  `descricao` VARCHAR(1000) NOT NULL,
  `idUsuario` INT NOT NULL,
  `idPergunta` INT NOT NULL,
  PRIMARY KEY (`idResposta`),
  INDEX `fk_usuario_idx` (`idUsuario` ASC),
  INDEX `fk_pergunta_idx` (`idPergunta` ASC),
  CONSTRAINT `fk_usuario_resposta`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `askTechForum`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pergunta_resposta`
    FOREIGN KEY (`idPergunta`)
    REFERENCES `askTechForum`.`PERGUNTA` (`idPergunta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `askTechForum`.`TAG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`TAG` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`TAG` (
  `idTag` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`idTag`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `askTechForum`.`TAG_PERGUNTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`TAG_PERGUNTA` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`TAG_PERGUNTA` (
  `idTag` INT NOT NULL,
  `idPergunta` INT NOT NULL,
  PRIMARY KEY (`idTag`, `idPergunta`),
  INDEX `fk_pergunta_idx` (`idPergunta` ASC),
  CONSTRAINT `fk_pergunta`
    FOREIGN KEY (`idPergunta`)
    REFERENCES `askTechForum`.`PERGUNTA` (`idPergunta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tag`
    FOREIGN KEY (`idTag`)
    REFERENCES `askTechForum`.`TAG` (`idTag`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
