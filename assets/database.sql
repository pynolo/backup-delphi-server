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
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0;

DROP TABLE IF EXISTS `delphi_user`;
CREATE TABLE `delphi_user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(256) NOT NULL,
  `role` varchar(8) NOT NULL,
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `ix_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=0;

DROP TABLE IF EXISTS `delphi_user_task`;
CREATE TABLE `delphi_user_task` (
  `id` int(11) NOT NULL auto_increment,
  `id_user` int(11) NOT NULL,
  `id_task` int(11) NOT NULL,
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `ix_user_task` (`id_user`, `id_task`)
) ENGINE=InnoDB AUTO_INCREMENT=0;


