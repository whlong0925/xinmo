/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.6.25-log : Database - xinmo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xinmo` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xinmo`;

/*Table structure for table `tb_function` */

DROP TABLE IF EXISTS `tb_function`;

CREATE TABLE `tb_function` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `path` varchar(100) DEFAULT NULL COMMENT '对应的URL',
  `action` varchar(50) DEFAULT NULL COMMENT '对应的动作',
  `status` smallint(6) DEFAULT NULL COMMENT '开关,0闭1开',
  `parentId` smallint(6) DEFAULT '0' COMMENT '父ID',
  `functionType` tinyint(4) NOT NULL COMMENT '类型,是模块、菜单、菜单内的操作. 0:模块 1:功能2:子功能',
  `sequence` smallint(6) DEFAULT '0' COMMENT '排序字段',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `tb_function` */

insert  into `tb_function`(`id`,`name`,`description`,`path`,`action`,`status`,`parentId`,`functionType`,`sequence`,`createTime`) values (1,'模块1','模块1',NULL,NULL,0,0,0,NULL,'2016-03-28 16:17:37'),(2,'功能1','功能1','/user/list',NULL,0,3,1,1,'2016-03-31 17:49:32'),(3,'模块2','模块2',NULL,NULL,0,0,0,NULL,'2016-03-28 16:17:38'),(4,'功能2','功能2','/role/list',NULL,0,1,1,2,'2016-03-31 17:49:48');

/*Table structure for table `tb_role` */

DROP TABLE IF EXISTS `tb_role`;

CREATE TABLE `tb_role` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role` */

insert  into `tb_role`(`id`,`name`,`description`,`createTime`) values (1,'admin','admin','2016-03-29 17:01:34'),(2,'operator','operator','2016-03-29 17:01:42');

/*Table structure for table `tb_role_function` */

DROP TABLE IF EXISTS `tb_role_function`;

CREATE TABLE `tb_role_function` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `roleId` smallint(6) DEFAULT '0' COMMENT '��ɫ��ID',
  `functionId` smallint(6) DEFAULT '0' COMMENT '���ܱ�ID',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role_function` */

insert  into `tb_role_function`(`id`,`roleId`,`functionId`,`createTime`) values (1,1,2,'2016-03-29 17:02:23'),(2,1,4,'2016-03-29 17:02:26');

/*Table structure for table `tb_role_user` */

DROP TABLE IF EXISTS `tb_role_user`;

CREATE TABLE `tb_role_user` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `roleId` smallint(6) DEFAULT NULL,
  `userId` bigint(20) DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role_user` */

insert  into `tb_role_user`(`id`,`roleId`,`userId`,`createTime`) values (1,1,1,'2016-03-29 17:01:50'),(2,2,1,'2016-03-29 17:01:58');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `usercode` varchar(30) DEFAULT NULL COMMENT '登陆名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `status` smallint(6) DEFAULT '0' COMMENT '用户状态 0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`usercode`,`password`,`status`) values (1,'admin','admin','21232f297a57a5a743894a0e4a801fc3',0),(2,'aaa','aaa','47bce5c74f589f4867dbd57e9ca9f808',1);

/* Procedure structure for procedure `sp_functions` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_functions` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_functions`(start_with INT)
proc:
  BEGIN
  
    DECLARE v_id INT;
    DECLARE v_name VARCHAR(30);
    DECLARE v_status SMALLINT;
    DECLARE v_parent_id INT;
    DECLARE v_sequence INT; 
  
    DECLARE done, error BOOLEAN DEFAULT FALSE;
    
    DECLARE c CURSOR FOR
      SELECT id, name, STATUS, parentId, sequence
      FROM tb_function 
      WHERE parentId = start_with AND STATUS=0;
  
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error = TRUE;  
    SET @@max_sp_recursion_depth=99;
  OPEN c;  
    IF error THEN  
      SELECT 'OPEN failed'; 
      LEAVE proc; 
    END IF;  
    REPEAT
      FETCH c INTO v_id, v_name, v_status, v_parent_id, v_sequence;
      
      IF error THEN  
        SELECT CONCAT('FETCH failed ??',v_name,CAST(v_id AS CHAR(10))) error; 
        LEAVE proc;
      END IF;
      
      IF NOT done THEN  
        INSERT INTO Temporary_Table VALUES  
        (v_id, v_name, v_status, v_parent_id, v_sequence);
        
        IF error THEN  
          SELECT 'INSERT in sp_functions() failed' error; 
        END IF;
        CALL sp_functions(v_id);
      END IF;
      
      IF error THEN  
        SELECT 'Recursive CALL sp_functions() failed' error; 
      END IF;  
      UNTIL done
    END REPEAT;  
  CLOSE c;
  
  IF error THEN
    SELECT 'CLOSE failed' error;
  END IF;
END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_modules` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_modules` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modules`(IN p_type INT,IN p_role_id INT)
proc:
  BEGIN   
    DECLARE v_id INT;
    DECLARE v_name VARCHAR(30);
    DECLARE v_status INT;
    DECLARE v_parent_id INT;
    DECLARE v_sequence INT;
    DECLARE temporary_table_exists   BOOLEAN;
    DECLARE done, error BOOLEAN DEFAULT FALSE;
    
    DECLARE c CURSOR FOR
      SELECT id, NAME, STATUS, parentId, sequence    
      FROM tb_function
      WHERE      functionType = 0 AND STATUS=0;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error = TRUE;
    DROP TEMPORARY TABLE IF EXISTS Temporary_Table;
    CREATE TEMPORARY TABLE Temporary_Table
    (   id SMALLINT(6),
        NAME VARCHAR(30),
        STATUS SMALLINT,
        parentId SMALLINT(6),
        sequence SMALLINT(6)
    );
    IF error THEN
      SELECT 'CREATE TEMPORARY failed';
      LEAVE proc; 
    END IF;
    SET temporary_table_exists=TRUE;    
    
    IF error THEN
      SELECT 'First SELECT failed'; 
      LEAVE proc; 
    END IF;   
    
  OPEN c;  
    IF error THEN  
      SELECT 'OPEN failed';
      LEAVE proc; 
    END IF;  
    REPEAT
      FETCH c INTO v_id, v_name, v_status, v_parent_id, v_sequence;
      
      IF error THEN  
        SELECT CONCAT('FETCH failed ??',v_name,CAST(v_id AS CHAR(10))) error; 
        LEAVE proc;
      END IF;
      IF NOT done THEN
      INSERT INTO Temporary_Table VALUES  
      (v_id, v_name, v_status, v_parent_id, v_sequence);       
      CALL sp_functions(v_id);
      END IF;
      IF error THEN  
        SELECT 'Recursive CALL hierarchy2() failed' error; 
      END IF;  
      UNTIL done
    END REPEAT;  
  CLOSE c;
  
  IF p_type = 1 THEN
    SELECT FALSE isChecked, 
    id, NAME,STATUS,
    parentId, sequence
    FROM Temporary_Table WHERE STATUS=0;
  ELSE
    SELECT CASE WHEN d.functionId IS NULL THEN FALSE ELSE TRUE END isChecked, 
    id, NAME, STATUS,
    parentId, sequence
    FROM Temporary_Table a LEFT JOIN (SELECT c.functionId FROM tb_role b, tb_role_function c WHERE b.id=c.roleId AND b.id=p_role_id) d
    ON a.id=d.functionId WHERE a.status=0;
  END IF;  
    IF error THEN
      LEAVE proc; 
      END IF;
    IF temporary_table_exists
    THEN
      DROP TEMPORARY TABLE Temporary_Table;
    END IF;
  END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
