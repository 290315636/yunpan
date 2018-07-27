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

/*Data for the table `tb_file` */

insert  into `tb_file`(`id`,`name`,`thumbnail`,`size`,`path`,`ctime`,`utime`,`type`,`md5`) values ('1342f095f9442bcbff862c47cdbe4c3','500457601.jpg','#',3027272,'c:\\vfs\\yunpan\\201807\\500457601.jpg','2018-07-26 11:20:05','2018-07-26 11:20:03','image/jpeg',''),('16524f095f9442bcbff862c47cdbe4c3','350c3222487b492aa75ffb0f92ac6fba.jpg','#',3027272,'c:\\vfs\\yunpan\\201807\\500457601.jpg','2018-07-26 11:20:27','2018-07-26 11:20:29','image/jpeg','5b954e8881f4fe752fec2d24986e473f'),('7ee64917d63647e9bda583a1ea7b1d0e','Image 1.png','#',123,'d:/','2018-07-26 11:19:58','2018-07-26 11:20:00','png',NULL),('8kk64917d63647e9bda583a1ea7b1d0e','Image 2.png','#',8188,'d:/','2018-07-26 11:19:53','2018-07-26 11:19:56','png',NULL),('9bb64917d63647e9bda583a1ea7b1d0e','Image 3.png','#',3712,'d:/','2018-07-26 11:21:33','2018-07-26 11:21:36','png',NULL);

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

/*Data for the table `tb_file_share` */

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

/*Data for the table `tb_file_statistic` */

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

/*Data for the table `tb_file_test` */

insert  into `tb_file_test`(`id`,`msg`,`ctime`,`utime`,`note`) values ('00cba10da06a4fd5be16c5a61bcb215e','hello tikie 2 db','2018-07-17 17:20:44','2018-07-17 17:20:44','模拟更新数据库'),('043b307d95f24833a883428816d7722c','hello tikie 2 db','2018-07-17 17:20:36','2018-07-17 17:20:36','模拟更新数据库'),('097bfaf21801495fab7f09a6919dcca6','hello tikie 2 db','2018-07-17 17:20:42','2018-07-17 17:20:42','模拟更新数据库'),('0a584df9a4d3486ab2c4c653cc4323f2','hello tikie 2 db','2018-07-17 17:21:30','2018-07-17 17:21:30','模拟更新数据库'),('0cf3357c93e64f7ba8c0aa77de01df9b','hello tikie 2 db','2018-07-17 17:20:00','2018-07-17 17:20:00','模拟更新数据库'),('0eacd01185364f5bbda25a60ffce0db9','hello tikie 2 db','2018-07-17 17:19:36','2018-07-17 17:19:36','模拟更新数据库'),('105c35b7b30a4feebeaf9c904b4bb3fc','hello tikie 2 db','2018-07-17 17:21:38','2018-07-17 17:21:38','模拟更新数据库'),('135fb95585ec4dbeb6c2fa3c50f9d27a','hello tikie 2 db','2018-07-17 17:21:02','2018-07-17 17:21:02','模拟更新数据库'),('14fa4436113643bf80ed6b4d14152396','hello tikie 2 db','2018-07-17 17:21:04','2018-07-17 17:21:04','模拟更新数据库'),('165c589c46844df2a9951c58e2f8a1bd','hello tikie 2 db','2018-07-17 17:21:14','2018-07-17 17:21:14','模拟更新数据库'),('17edd381133b412180773abf89f61f83','hello tikie','2018-07-13 16:06:04','2018-07-13 16:06:04',NULL),('1a2b0588faf94118b9c4d30f50154019','hello tikie 2 db','2018-07-17 17:20:46','2018-07-17 17:20:46','模拟更新数据库'),('1cf167ce6ff44565aff20b8e3de4039a','hello tikie 2 db','2018-07-17 17:20:04','2018-07-17 17:20:04','模拟更新数据库'),('1f7a6ce7588e48d295545ce14c07c40f','hello tikie 2 db','2018-07-17 17:21:12','2018-07-17 17:21:12','模拟更新数据库'),('1ff3284a818d4a8bb6d22438327f9cda','hello tikie 2 db','2018-07-17 17:19:20','2018-07-17 17:19:20','模拟更新数据库'),('2223570651d14904976907bfd9123411','hello tikie 2 db','2018-07-17 17:21:18','2018-07-17 17:21:18','模拟更新数据库'),('234ab7f1201b4020a8f5280d157f706b','hello tikie 2 db','2018-07-17 17:19:42','2018-07-17 17:19:42','模拟更新数据库'),('256a3b75d980471b93fd373d02b98ab3','hello tikie 2 db','2018-07-17 17:20:14','2018-07-17 17:20:14','模拟更新数据库'),('26f81ac6b3974368a259b2f75f169ce5','hello tikie 2 db','2018-07-17 17:20:06','2018-07-17 17:20:06','模拟更新数据库'),('27c4f425c509472cae7649302fe491ea','hello tikie 2 db','2018-07-17 17:19:44','2018-07-17 17:19:44','模拟更新数据库'),('2c8302edde1a4381bab59ee99ce23fe7','hello tikie 2 db','2018-07-17 17:21:00','2018-07-17 17:21:00','模拟更新数据库'),('2dc440d4980c4134a2b1be3a5c7a5dfa','hello tikie 2 db','2018-07-17 17:21:28','2018-07-17 17:21:28','模拟更新数据库'),('3309f1b966d74eb6bf4e0d2bb931a672','hello tikie 2 db','2018-07-17 17:21:16','2018-07-17 17:21:16','模拟更新数据库'),('363bc62f3a2d44fda5a155e4af06f76d','hello tikie 2 db','2018-07-17 17:33:42','2018-07-17 17:33:42','模拟更新数据库'),('3c73d07e561e4496a03d4d3ef30c1fc9','hello tikie 2 db','2018-07-17 17:20:34','2018-07-17 17:20:34','模拟更新数据库'),('3ec6ad22fc9e45b59891515fda75080c','hello tikie 2 db','2018-07-17 17:21:26','2018-07-17 17:21:26','模拟更新数据库'),('3f2460016d33435eb25558f8e6d14126','hello tikie 2 db','2018-07-17 17:20:28','2018-07-17 17:20:28','模拟更新数据库'),('40c7c521af5d488a81cd5827f7be7cd0','hello tikie 2 db','2018-07-17 17:19:56','2018-07-17 17:19:56','模拟更新数据库'),('418bba3f3d464582bc81b319170bf9c6','hello tikie 2 db','2018-07-17 17:20:56','2018-07-17 17:20:56','模拟更新数据库'),('4239cfaa494740b79d765836495506d3','hello','2018-07-13 16:00:10','2018-07-13 16:00:10',NULL),('4596359fcf614be0b25f903f22be30e5','hello tikie 2 db','2018-07-17 17:33:40','2018-07-17 17:33:40','模拟更新数据库'),('4d04bcbb49d649b8976c619746692c45','hello tikie 2 db','2018-07-17 17:20:02','2018-07-17 17:20:02','模拟更新数据库'),('52d015eef5f24cf49d3aec99a1f5b054','hello tikie','2018-07-17 17:13:32','2018-07-17 17:13:32',NULL),('556ac136f0db4532b17b3d21980d7bd7','hello tikie 2 db','2018-07-17 17:21:40','2018-07-17 17:21:40','模拟更新数据库'),('569606b66e7540079c2d8b0101f73a39','hello tikie 2 db','2018-07-17 17:19:30','2018-07-17 17:19:30','模拟更新数据库'),('56fedfa0597347719a6dc96308c3e75d','hello tikie 2 db','2018-07-17 17:20:50','2018-07-17 17:20:50','模拟更新数据库'),('5b876407a8dc4ba9915b2f8354f2d79f','hello tikie 2 db','2018-07-17 17:20:20','2018-07-17 17:20:20','模拟更新数据库'),('5c358258c3fa4922b7b2c7f0f6a6cf99','hello tikie 2 db','2018-07-17 17:33:34','2018-07-17 17:33:34','模拟更新数据库'),('6018ac9ae0064a7fb9b0c999ee593f03','hello tikie 2 db','2018-07-17 17:19:52','2018-07-17 17:19:52','模拟更新数据库'),('678ae22cfef646c4aee1d80bffbff49b','hello tikie 2 db','2018-07-17 17:21:06','2018-07-17 17:21:06','模拟更新数据库'),('699becfae4c14b0888fc4a0a3e686933','hello tikie 2 db','2018-07-17 17:20:58','2018-07-17 17:20:58','模拟更新数据库'),('6ad425f371cd42bebae3a07a132a1dd5','hello tikie 2 db','2018-07-17 17:21:20','2018-07-17 17:21:20','模拟更新数据库'),('6b5908014b8e43a1a5a2340f33897608','hello tikie 2 db','2018-07-17 17:19:28','2018-07-17 17:19:28','模拟更新数据库'),('6d0a66f978794db1a5e2c1d6e473d740','hello tikie 2 db','2018-07-17 17:20:48','2018-07-17 17:20:48','模拟更新数据库'),('71c6939f0cca42fe9ec40b29c00f850c','hello tikie','2018-07-13 16:52:17','2018-07-13 16:52:17',NULL),('72307d749d5a4e1184efdc297179b62d','hello tikie 2 db','2018-07-17 17:20:18','2018-07-17 17:20:18','模拟更新数据库'),('72ef92fcc4444905af9ba4bd2deb9923','hello tikie 2 db','2018-07-17 17:20:10','2018-07-17 17:20:10','模拟更新数据库'),('73242c8da0e343aa93a707ed01e826e4','hello tikie 2 db','2018-07-17 17:21:34','2018-07-17 17:21:34','模拟更新数据库'),('739b1735a9544095bba2f1169232e841','hello tikie 2 db','2018-07-17 17:19:38','2018-07-17 17:19:38','模拟更新数据库'),('73c1f7ef1cff47c6ba6723c8dfc42312','hello tikie 2 db','2018-07-17 17:19:32','2018-07-17 17:19:32','模拟更新数据库'),('770d2c1b583c4b6bb400b917f5b3c52b','hello tikie','2018-07-13 16:01:52','2018-07-13 16:01:52',NULL),('810c0d8a67dd43eba78794689122441f','hello tikie 2 db','2018-07-17 17:19:58','2018-07-17 17:19:58','模拟更新数据库'),('858ec6609e3f44d9a8072f9f3231a04b','hello tikie 2 db','2018-07-17 17:20:12','2018-07-17 17:20:12','模拟更新数据库'),('8d5d07b9f18b4790801b5098812469c8','hello tikie 2 db','2018-07-17 17:20:52','2018-07-17 17:20:52','模拟更新数据库'),('8e1d6de7c2a74366879e82a1af77fede','hello tikie 2 db','2018-07-17 17:21:32','2018-07-17 17:21:32','模拟更新数据库'),('903559ffd30e43348e097b8da287496f','hello tikie 2 db','2018-07-17 17:19:46','2018-07-17 17:19:46','模拟更新数据库'),('953158590ccb47f8a1a11345ffc2cac0','hello tikie 2 db','2018-07-17 17:20:38','2018-07-17 17:20:38','模拟更新数据库'),('9f9eaa0762fb48b9b3bf4e34d75f9e91','hello tikie 2 db','2018-07-17 17:19:18','2018-07-17 17:19:18','模拟更新数据库'),('9ff82601617b421aa3525a3c69a08380','hello tikie 2 db','2018-07-17 17:21:36','2018-07-17 17:21:36','模拟更新数据库'),('a8be3f670c8f457c9de53ce84698ab01','hello tikie 2 db','2018-07-17 17:20:16','2018-07-17 17:20:16','模拟更新数据库'),('b069a1252eac4561b1a594241968cbdb','hello tikie 2 db','2018-07-17 17:20:54','2018-07-17 17:20:54','模拟更新数据库'),('b8360713f7164ae2a953e9f88820ccde','hello tikie 2 db','2018-07-17 17:21:08','2018-07-17 17:21:08','模拟更新数据库'),('b9ed40b88add40e68685e56fd33bcc2d','hello tikie 2 db','2018-07-17 17:20:32','2018-07-17 17:20:32','模拟更新数据库'),('bd308bfe960943279a7cc97e437e684f','hello tikie 2 db','2018-07-17 17:21:24','2018-07-17 17:21:24','模拟更新数据库'),('be35b19651f0483b96428d4717e0465d','hello tikie 2 db','2018-07-17 17:19:40','2018-07-17 17:19:40','模拟更新数据库'),('c17f9c2ae5a9458abf646f2fff43e4d5','hello tikie 2 db','2018-07-17 17:19:34','2018-07-17 17:19:34','模拟更新数据库'),('cc2d995f89c24c9dbdceae03386a45df','hello tikie 2 db','2018-07-17 17:33:38','2018-07-17 17:33:38','模拟更新数据库'),('cda0da15fe674988b1a7abc416ca332f','hello tikie 2 db','2018-07-17 17:20:08','2018-07-17 17:20:08','模拟更新数据库'),('cf933fea9ab948dfbdcac58eae754d56','hello tikie','2018-07-17 17:18:46','2018-07-17 17:18:46',NULL),('d4f32fc71e29468284f5be2786b44f08','hello tikie 2 db','2018-07-17 17:19:24','2018-07-17 17:19:24','模拟更新数据库'),('d66dd404c30843078945c4b554950fd5','hello tikie 2 db','2018-07-17 17:20:24','2018-07-17 17:20:24','模拟更新数据库'),('d83baa4ecabb470fba2aa32fa1862f2a','hello tikie','2018-07-13 16:08:31','2018-07-13 16:08:31',NULL),('dbd4495982634872a9eff5fc9451ca5d','hello tikie 2 db','2018-07-17 17:20:26','2018-07-17 17:20:26','模拟更新数据库'),('df26b8cd406a405cb74e0a7ec51a3564','hello tikie 2 db','2018-07-17 17:21:10','2018-07-17 17:21:10','模拟更新数据库'),('e093a995fdc14dba8d90eeb5c69c0a9e','hello tikie 2 db','2018-07-17 17:19:54','2018-07-17 17:19:54','模拟更新数据库'),('e8fa8c5fa43842119106d0df0a7e640e','hello tikie 2 db','2018-07-17 17:33:33','2018-07-17 17:33:33','模拟更新数据库'),('ede77541b86f4212b1946f5983552db0','hello tikie 2 db','2018-07-17 17:20:40','2018-07-17 17:20:40','模拟更新数据库'),('eed4a58ba7e347ea88de53b7cfff1fb6','hello tikie 2 db','2018-07-17 17:19:16','2018-07-17 17:19:16','模拟更新数据库'),('f34167b047fd4f4d8f85be4c6a002f04','hello tikie 2 db','2018-07-17 17:33:36','2018-07-17 17:33:36','模拟更新数据库'),('f47239be1a7649a9adce840c9b28990b','hello tikie 2 db','2018-07-17 17:19:48','2018-07-17 17:19:48','模拟更新数据库'),('f526aeef39c7465e995aa4f5dc9246bf','hello tikie 2 db','2018-07-17 17:21:22','2018-07-17 17:21:22','模拟更新数据库'),('f6a1c6d7775f4e54afc21ba76e3365f3','hello tikie 2 db','2018-07-17 17:20:30','2018-07-17 17:20:30','模拟更新数据库'),('f8d6e9b47c0b4b9a8c8abd6e9a416b75','hello tikie 2 db','2018-07-17 17:20:22','2018-07-17 17:20:22','模拟更新数据库'),('fa90a96992b448acac6e5a807a2e934c','hello tikie 2 db','2018-07-17 17:19:22','2018-07-17 17:19:22','模拟更新数据库'),('faa0faa999a843d499fc9ea35d4fcab1','hello tikie 2 db','2018-07-17 17:33:44','2018-07-17 17:33:44','模拟更新数据库'),('fafd58d5612146eb8a52a6c875ab2cfa','hello tikie 2 db','2018-07-17 17:19:26','2018-07-17 17:19:26','模拟更新数据库'),('ff1d14785450493db07ef62018504e39','hello tikie 2 db','2018-07-17 17:19:50','2018-07-17 17:19:50','模拟更新数据库');

/*Table structure for table `tb_file_tree` */

DROP TABLE IF EXISTS `tb_file_tree`;

CREATE TABLE `tb_file_tree` (
  `id` varchar(32) DEFAULT NULL,
  `is_file` tinyint(1) DEFAULT NULL COMMENT '文件为叶子节点',
  `name` varchar(64) DEFAULT NULL COMMENT '自定义名称',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '上传时间/修改日期',
  `file_id` varchar(32) DEFAULT '#' COMMENT '对应的文件',
  `pid` varchar(32) DEFAULT '#' COMMENT '父级文件夹',
  `reback` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '#' COMMENT '删除后还原id',
  `size` bigint(20) unsigned zerofill DEFAULT '00000000000000000000' COMMENT '文件夹则为所有文件的和/byte',
  `md5` varchar(64) DEFAULT '#' COMMENT '文件唯一值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件树';

/*Data for the table `tb_file_tree` */

insert  into `tb_file_tree`(`id`,`is_file`,`name`,`sort`,`ctime`,`utime`,`file_id`,`pid`,`reback`,`size`,`md5`) values ('1',0,'回收站',0,'2018-07-23 17:23:04','2018-07-23 17:23:07','#','#','#',00000000000000000000,'#'),('文件测试',1,'文件',0,'2018-07-23 17:26:54','2018-07-23 17:26:57','37b90e5c1b5d44aa9e7926d1c86c741d','文件夹','#',00000000000000000012,'#'),('文件夹',0,'文件夹',0,'2018-07-23 17:28:19','2018-07-23 17:28:21','#','#','#',00000000000000000000,'#'),('57d89f4c05d14bb6b07fec3275cc7f30',1,'350c3222487b492aa75ffb0f92ac6fba.jpg',0,'2018-07-25 15:06:55','2018-07-25 15:06:58','16524f095f9442bcbff862c47cdbe4c3','#','#',00000000000003027272,'#'),('e261ba38c3e34aa4ab1a25c2ffb00c0a',1,'Image 2.png',0,'2018-07-25 15:07:15','2018-07-25 15:07:07','8kk64917d63647e9bda583a1ea7b1d0e','#','#',00000000000000008188,'#'),('12d74e7821c14248bf4088d74958ee66',1,'Image 3.png',0,'2018-07-25 15:07:12','2018-07-25 15:07:09','9bb64917d63647e9bda583a1ea7b1d0e','#','#',00000000000000003712,'#'),('ce3ef9753aff436f9c6dd8310dacdfd7',0,'folder',0,'2018-07-27 13:31:30','2018-07-27 13:31:30','#','文件夹','#',00000000000000000000,'#'),('99e7782dba144ab396bced98525a9276',1,'350c3222487b492aa75ffb0f92ac6fba.jpg',0,'2018-07-27 14:07:02','2018-07-27 14:07:02','16524f095f9442bcbff862c47cdbe4c3','文件夹','#',00000000000003027272,'#'),('b3e3599a96e94e8dbc878f9fe06cbc40',1,'apple.jpg',0,'2018-07-27 14:51:10','2018-07-27 14:51:10','1342f095f9442bcbff862c47cdbe4c3','文件夹','1342f095f9442bcbff862c47cdbe4c3',00000000000003027272,'#'),('4b7a8aadc04c400380ed73b601ca9503',1,'Image 1.png',0,'2018-07-27 15:02:20','2018-07-27 15:02:20','7ee64917d63647e9bda583a1ea7b1d0e','文件夹','#',00000000000000023375,'#');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
