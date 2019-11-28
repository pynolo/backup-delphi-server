DROP TABLE IF EXISTS `tasktrigger_task`;
CREATE TABLE `tasktrigger_task` (
  `id` int(11) NOT NULL auto_increment,
  `task_name` varchar(256) NOT NULL,
  `modified_date` date NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0;