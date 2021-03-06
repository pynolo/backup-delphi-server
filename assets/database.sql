DROP TABLE IF EXISTS `delphi_task`;
CREATE TABLE `delphi_task` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(8) NOT NULL,
  `name` varchar(256) NOT NULL,
  `executable` varchar(256) NOT NULL,
  `description` varchar(256) NOT NULL,
  `workspace_id` varchar(256) NOT NULL,
  `workspace_name` varchar(256) NOT NULL,
  `environment_id` varchar(256) NOT NULL,
  `environment_name` varchar(256) NOT NULL,
  `available` bit NOT NULL,
  `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `idx_executable` (`executable`)
) ENGINE=InnoDB AUTO_INCREMENT=0;

DROP TABLE IF EXISTS `delphi_execution`;
CREATE TABLE `delphi_execution` (
  `execution_id` varchar(256) NOT NULL,
  `executable` varchar(256) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `start_timestamp` varchar(64) NULL,
  `finish_timestamp` varchar(64) NULL,
  `job_id` varchar(256) NULL,
  `job_version` varchar(256) NULL,
  `execution_status` varchar(256) NULL,
  `error_type` varchar(256) NULL,
  `error_message` TEXT NULL,
  `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`execution_id`),
  UNIQUE KEY `idx_executable` (`executable`)
) ENGINE=InnoDB AUTO_INCREMENT=0;

DROP TABLE IF EXISTS `delphi_user`;
CREATE TABLE `delphi_user` (
  `username` varchar(256) NOT NULL,
  `role` varchar(8) NOT NULL,
  `modified_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0;
insert into delphi_user (username,role) values ("p.tacconi", "admin");
insert into delphi_user (username,role) values ("l.federici", "admin");
insert into delphi_user (username,role) values ("m.casamaggi", "admin");
insert into delphi_user (username,role) values ("f.giovannini", "admin");
insert into delphi_user (username,role) values ("l.brenzini", "user");

DROP TABLE IF EXISTS `delphi_user_task`;
CREATE TABLE `delphi_user_task` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(256) NOT NULL,
  `executable` varchar(256) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `ix_user_task` (`username`, `executable`)
) ENGINE=InnoDB AUTO_INCREMENT=0;

## Indice su Sap Log

CREATE INDEX `gge_sapreplyidx` ON `gge_sapreplymessages4business`(`dtdataacq`, `type`, `jobname`);

