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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `tb_function` */

insert  into `tb_function`(`id`,`name`,`description`,`path`,`action`,`status`,`parentId`,`functionType`,`sequence`,`createTime`) values (1,'系统管理','系统管理',NULL,NULL,0,0,0,1,'2016-04-20 15:42:40'),(2,'用户管理','用户管理','/user/list',NULL,0,1,1,1,'2016-04-20 15:43:35'),(3,'角色管理','角色管理','/role/list',NULL,0,1,1,2,'2016-04-20 15:44:10'),(4,'模块管理','模块管理','/module/list',NULL,0,1,1,3,'2016-04-20 15:44:38'),(5,'添加用户','添加用户','/user/add',NULL,0,2,2,1,'2016-04-20 16:06:03'),(6,'添加显示','查看用户','/user/show',NULL,0,2,2,2,'2016-04-20 16:12:59'),(7,'删除用户','删除用户','/user/*/delete',NULL,0,2,2,3,'2016-04-21 10:42:38'),(8,'更新用户','更新用户','/user/update',NULL,0,2,2,4,'2016-04-20 16:11:00'),(9,'编辑用户','编辑用户','/user/*/edit',NULL,0,2,2,5,'2016-04-21 10:42:43'),(10,'编辑角色','编辑角色','/role/*/edit',NULL,0,3,2,2,'2016-04-21 10:42:49'),(11,'更新角色','更新角色','/role/update',NULL,0,3,2,3,'2016-04-20 16:03:43'),(12,'删除角色','删除角色','/role/*/delete',NULL,0,3,2,4,'2016-04-21 10:42:54'),(13,'添加角色','添加角色','/role/add',NULL,0,3,2,1,'2016-04-20 16:15:27'),(14,'添加显示','添加显示','/role/show',NULL,0,3,2,5,'2016-04-20 16:16:19'),(15,'添加模块显示','添加模块显示','/module/show',NULL,0,4,2,1,'2016-04-20 16:17:55'),(16,'添加模块or功能','添加模块or功能','/module/add',NULL,0,4,2,2,'2016-04-20 16:22:33'),(17,'编辑模块','编辑模块','/module/*/edit',NULL,0,4,2,2,'2016-04-21 10:42:59'),(18,'更新模块或功能','更新模块或功能','/module/update',NULL,0,4,2,2,'2016-04-20 16:19:54'),(19,'删除模块或功能','删除模块或功能','/module/*/delete',NULL,0,4,2,4,'2016-04-21 10:43:05'),(20,'显示添加功能or子功能','显示添加功能or子功能','/function/*/show',NULL,0,4,2,6,'2016-04-21 10:43:10'),(21,'编辑功能or子功能','编辑功能or子功能','/function/*/edit',NULL,0,4,2,7,'2016-04-21 10:43:14'),(22,'显示用户角色','显示用户角色','/user/*/role',NULL,0,2,2,6,'2016-04-21 10:43:19'),(23,'添加用户角色','添加用户角色','/user/role/add',NULL,0,2,2,7,'2016-04-20 17:35:04');

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

insert  into `tb_role`(`id`,`name`,`description`,`createTime`) values (1,'管理员','管理员','2016-04-20 16:35:56'),(2,'操作员','操作员','2016-04-20 16:57:28');

/*Table structure for table `tb_role_function` */

DROP TABLE IF EXISTS `tb_role_function`;

CREATE TABLE `tb_role_function` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `roleId` smallint(6) DEFAULT '0' COMMENT '��ɫ��ID',
  `functionId` smallint(6) DEFAULT '0' COMMENT '���ܱ�ID',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role_function` */

insert  into `tb_role_function`(`id`,`roleId`,`functionId`,`createTime`) values (1,1,1,'2016-04-20 17:59:26'),(2,1,2,'2016-04-20 17:36:13'),(3,1,5,'2016-04-20 17:36:13'),(4,1,6,'2016-04-20 17:36:13'),(5,1,7,'2016-04-20 17:36:13'),(6,1,8,'2016-04-20 17:36:13'),(7,1,9,'2016-04-20 17:36:13'),(8,2,22,'2016-04-21 10:22:12'),(9,1,23,'2016-04-20 17:59:30'),(10,1,3,'2016-04-20 17:36:13'),(11,1,10,'2016-04-20 17:36:13'),(12,1,11,'2016-04-20 17:36:13'),(13,1,12,'2016-04-20 17:36:13'),(14,1,13,'2016-04-20 17:36:13'),(15,1,14,'2016-04-20 17:36:13'),(16,1,4,'2016-04-20 17:36:13'),(17,1,15,'2016-04-20 17:36:13'),(18,1,16,'2016-04-20 17:36:13'),(19,1,17,'2016-04-20 17:36:13'),(20,1,18,'2016-04-20 17:36:13'),(21,1,19,'2016-04-20 17:36:13'),(22,1,20,'2016-04-20 17:36:13'),(23,1,21,'2016-04-20 17:36:13');

/*Table structure for table `tb_role_user` */

DROP TABLE IF EXISTS `tb_role_user`;

CREATE TABLE `tb_role_user` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `roleId` smallint(6) DEFAULT NULL,
  `userId` bigint(20) DEFAULT '0',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_role_user` */

insert  into `tb_role_user`(`id`,`roleId`,`userId`,`createTime`) values (1,1,1,'2016-04-20 17:31:49');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `status` smallint(6) DEFAULT '0' COMMENT '用户状态 0正常 1删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`password`,`status`) values (1,'admin','21232f297a57a5a743894a0e4a801fc3',0),(2,'op','21232f297a57a5a743894a0e4a801fc3',0);

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
