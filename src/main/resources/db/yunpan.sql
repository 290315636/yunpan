/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.6.39 : Database - tikie-yunpan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tikie-yunpan` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tikie-yunpan`;

/*Table structure for table `tb_file` */

DROP TABLE IF EXISTS `tb_file`;

CREATE TABLE `tb_file` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `thumbnail` varchar(64) DEFAULT NULL COMMENT '缩略图',
  `size` bigint(20) DEFAULT '0' COMMENT '大小/byte',
  `path` varchar(128) DEFAULT NULL COMMENT '物理存放地址basePath+type+年月',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '最后上传时间',
  `type` varchar(32) DEFAULT NULL COMMENT '文件后缀名',
  `md5` varchar(64) DEFAULT NULL COMMENT '文件唯一值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件实体（后期可以考虑按照文件类型分表）';

/*Table structure for table `tb_file_share` */

DROP TABLE IF EXISTS `tb_file_share`;

CREATE TABLE `tb_file_share` (
  `id` varchar(32) NOT NULL,
  `tree_ids` varchar(32) DEFAULT NULL COMMENT '文件树',
  `code` varchar(32) DEFAULT NULL COMMENT '分享码',
  `ctime` datetime DEFAULT NULL COMMENT '分享日期',
  `edate` date DEFAULT NULL COMMENT '失效日期',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `vcode` varchar(32) DEFAULT '' COMMENT '访问码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_unique_code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件树分享';

/*Table structure for table `tb_file_statistic` */

DROP TABLE IF EXISTS `tb_file_statistic`;

CREATE TABLE `tb_file_statistic` (
  `id` varchar(32) NOT NULL,
  `file_id` varchar(32) DEFAULT NULL COMMENT '文件id',
  `relate_count` int(11) DEFAULT '0' COMMENT '引用次数(多次上传)',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `share_count` int(11) DEFAULT '0' COMMENT '分享次数,不统计包分享',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件统计';

/*Table structure for table `tb_file_test` */

DROP TABLE IF EXISTS `tb_file_test`;

CREATE TABLE `tb_file_test` (
  `id` varchar(32) NOT NULL,
  `msg` varchar(255) NOT NULL,
  `ctime` datetime DEFAULT NULL,
  `utime` datetime DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_file_tree` */

DROP TABLE IF EXISTS `tb_file_tree`;

CREATE TABLE `tb_file_tree` (
  `id` varchar(32) DEFAULT NULL,
  `is_file` tinyint(1) DEFAULT '1' COMMENT '文件为叶子节点',
  `name` varchar(64) DEFAULT NULL COMMENT '自定义名称',
  `thumbnail` varchar(64) DEFAULT '#' COMMENT '文件缩略图',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '上传时间/修改日期',
  `file_id` varchar(32) DEFAULT '#' COMMENT '对应的文件',
  `pid` varchar(32) DEFAULT '1' COMMENT '父级文件夹,1:云盘；9：回收站',
  `reback` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '#' COMMENT '还原到原来的文件夹下',
  `reback_type` varchar(32) DEFAULT '#' COMMENT '还原到原来的文件类型',
  `size` varchar(32) DEFAULT '0' COMMENT '文件夹则为所有文件的和/byte',
  `type` varchar(32) DEFAULT '#' COMMENT '文件类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件树';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
