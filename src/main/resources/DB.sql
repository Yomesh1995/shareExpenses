
CREATE DATABASE shareExpenses;

CREATE TABLE `user_master`(  
  `user_id` INT(6) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `mobile_nbr` VARCHAR(15) NOT NULL,
  `email_id` VARCHAR(30) NOT NULL,
  `reg_date` DATETIME NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`user_name`)
) ENGINE=INNODB CHARSET=utf8;

ALTER TABLE `user_master` ADD COLUMN `forget_password` BOOLEAN DEFAULT 0 NULL AFTER `reg_date`;

CREATE TABLE `login_details`(  
  `user_id` INT(6) NOT NULL,
  `last_login` DATETIME NOT NULL,
  `prev_passowrd` VARCHAR(10) NOT NULL,
  `new_password` VARCHAR(10),
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB CHARSET=utf8;

CREATE TABLE `group_master`(  
  `id` INT(6) NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(50) NOT NULL,
  `create_date` DATETIME NOT NULL,
  `user_count` INT(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8;

CREATE TABLE `transaction_master`(  
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `txn_date` DATETIME NOT NULL,
  `group_id` INT(6),
  `from_user_id` INT(6) NOT NULL COMMENT 'Transaction Generator',
  `txn_amount` DOUBLE(20,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8;

CREATE TABLE `group_txn_detail`(  
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `txn_id` INT(10) NOT NULL,
  `to_user_id` INT(6) NOT NULL,
  `status` ENUM('Pending','Settled') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8;

CREATE TABLE `transaction_user_detail`( 
	`id` INT(10) NOT NULL AUTO_INCREMENT, 
	`txn_id` INT(10) NOT NULL, 
	`to_user_id` INT(6) NOT NULL, 
	`amount` DOUBLE(20,2) NOT NULL, 
	PRIMARY KEY (`id`) 
) ENGINE=INNODB CHARSET=utf8;

CREATE TABLE `group_users_detail` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `group_id` INT(10) NOT NULL,
  `user_id` INT(6) NOT NULL,
  `credit_amt` DOUBLE(20,2) NOT NULL,
  `debit_amt` DOUBLE(20,2) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `id` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

ALTER TABLE `login_details` DROP COLUMN `new_password`;
 
 