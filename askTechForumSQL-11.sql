SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `askTechForum` ;
CREATE SCHEMA IF NOT EXISTS `askTechForum` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `askTechForum` ;

-- -----------------------------------------------------
-- Table `askTechForum`.`USUARIO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`usuario` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `dt_nasc` DATE NULL,
  `admin` TINYINT(1) NOT NULL default 0,
  `email` VARCHAR(150) NOT NULL,
  `localizacao` VARCHAR(45) NULL,
  `senha` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `askTechForum`.`PERGUNTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`pergunta` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`pergunta` (
  `idPergunta` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(150) NOT NULL,
  `data` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `descricao` VARCHAR(1000) NOT NULL,
  `idUsuario` INT NOT NULL,
  `tag` VARCHAR(100),
  PRIMARY KEY (`idPergunta`),
  CONSTRAINT `fk_usuario_pergunta`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `askTechForum`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `ser_idx` ON `askTechForum`.`pergunta` (`idUsuario` ASC);


-- -----------------------------------------------------
-- Table `askTechForum`.`RESPOSTA`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `askTechForum`.`resposta` ;

CREATE TABLE IF NOT EXISTS `askTechForum`.`resposta` (
  `idResposta` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(1000) NOT NULL,
  `idUsuario` INT NOT NULL,
  `idPergunta` INT NOT NULL,
  `data` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `votos` INT default 0,
  PRIMARY KEY (`idResposta`),
  CONSTRAINT `fk_usuario_resposta`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `askTechForum`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pergunta_resposta`
    FOREIGN KEY (`idPergunta`)
    REFERENCES `askTechForum`.`pergunta` (`idPergunta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_usuario_idx` ON `askTechForum`.`resposta` (`idUsuario` ASC);

CREATE INDEX `fk_pergunta_idx` ON `askTechForum`.`resposta` (`idPergunta` ASC);


-- -----------------------------------------------------
-- Table `askTechForum`.`VOTO`
-- -----------------------------------------------------
CREATE TABLE `askTechForum`.`voto` (
  `idVoto` INT NOT NULL AUTO_INCREMENT,
  `idUsuario` INT NOT NULL,
  `idResposta` INT NOT NULL,
  PRIMARY KEY (`idVoto`),
  INDEX `fk_usuario_idx` (`idUsuario` ASC),
  INDEX `fk_resposta_idx` (`idResposta` ASC),
  CONSTRAINT `fk_resposta_voto`
    FOREIGN KEY (`idResposta`)
    REFERENCES `askTechForum`.`resposta` (`idResposta`)
	ON DELETE CASCADE
	ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_voto`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `askTechForum`.`usuario` (`idUsuario`)
	ON DELETE CASCADE
	ON UPDATE CASCADE);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- ajustes asktechforum.

-- Criando o usuário admin.
-- insert into usuario (idUsuario,nome,dt_nasc,admin,email,localizacao,senha)
-- values (1,'Admin',sysdate(),1,'admin@admin.com','BR','admin');


-- Criando a pergunta 0;
-- insert into pergunta(idPergunta,titulo,data,hora,descricao,idUsuario,tag) values
-- (1,'pergunta default',sysdate(),curtime(),'Pergunta Padrao do sistema',1,'Geral');
