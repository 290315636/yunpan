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

insert  into `tb_file`(`id`,`name`,`thumbnail`,`size`,`path`,`ctime`,`utime`,`type`,`md5`) values ('134fed4590b74336b96a8c14f8841f96','31292651_421410868305729_334832978926501888_n.jpg','#',35588,'c:\\vfs\\yunpan\\201808\\','2018-08-08 09:54:32','2018-08-08 09:54:32','image/jpeg','568daf0dd7ce17dfd5343b2414a2b1d0'),('2ac21f4e7a5d47088e3aaa74b4101a23','2f738bd4b31c8701288c01fb237f9e2f0708fff1.jpg','#',162885,'c:\\vfs\\yunpan\\201808\\','2018-08-06 14:42:39','2018-08-06 14:42:39','image/jpeg','1ba335365585381f8b9053dbb26857c0'),('4889359a862c4c2e916f0f46e0aef6d8','favicon _1_.ico','#',4286,'c:\\vfs\\yunpan\\201808\\','2018-08-08 10:02:20','2018-08-08 10:02:20','image/x-icon','2720e1620f3ac4bb06c9cddd2cbdb1fb'),('5f8c8d80714349aeb4c25b95fda35acd','2.png','#',65101,'c:\\vfs\\yunpan\\201808\\','2018-08-06 14:41:04','2018-08-06 14:41:04','image/png','27fe3ae3c1a46e3b0526b2eb94b864d9'),('7ca369789c18462e873a2a3e0bd1f2e2','favicon.ico','#',1150,'c:\\vfs\\yunpan\\201808\\','2018-08-08 10:00:10','2018-08-08 10:00:10','image/x-icon','3cc54d7908f64de9b6c716ac4527b3bb'),('7f9089ff1d494d5888d11533b2c6ca88','5e68dd8646bc4bd72b0ecab3c360aa80.jpg','#',21510,'c:\\vfs\\yunpan\\201808\\','2018-08-06 14:42:51','2018-08-06 14:42:51','image/jpeg','c97ecf893292c5d26f5967007558c484'),('898597f044c145e8a7da1dfa4233460c','2da62bea53251f87b08ea37d57455f27.jpg','#',103129,'c:\\vfs\\yunpan\\201808\\','2018-08-06 14:41:04','2018-08-06 14:41:04','image/jpeg','149d41d328d5ae28ae4bbcace21a3d08'),('9c891becb4cd4ea996f87ecc5022df85','1002211-20170329144612170-956394445.png','#',2513390,'c:\\vfs\\yunpan\\201808\\','2018-08-08 10:02:58','2018-08-08 10:02:58','image/png','1353156c84038334312ce13eb09d078b'),('e58deb44c6934035832f9204c6f74d2c','54f825ac68e66024b5e6d2ee896e3e0c.jpg','#',86101,'c:\\vfs\\yunpan\\201808\\','2018-08-08 09:56:19','2018-08-08 09:56:19','image/jpeg','6d39c7d035f9d49fce2cf46fa560acda'),('ff46c25cdbfe43088aa122bf03e2e5b7','favicon _2_.ico','#',9662,'c:\\vfs\\yunpan\\201808\\','2018-08-07 16:00:29','2018-08-07 16:00:29','image/x-icon','a7caca7373226d866bbc6dc19049af7b');

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

insert  into `tb_file_share`(`id`,`tree_ids`,`code`,`ctime`,`edate`,`download_count`,`vcode`) values ('38bbe70ee0a64f14bc0d3cc2e689e830','32','pI4d16440f8nyaU2b8D6m9Dt','2018-07-31 16:58:16',NULL,0,''),('3c05427d126246d98ea05008a05cc2a9','12','4Y2CSQ45jR908Y769T46z263','2018-07-31 15:27:17',NULL,0,''),('465e1a6bdb184f7394eeee68d42ab6d7','32','V5003L282TLaa42ehB516h2b','2018-07-31 16:00:42',NULL,0,''),('47e75699ffe344c78f67693cbc05de8c','111','b7375MB2j138e56TIVG2y7oa','2018-07-31 15:53:29',NULL,0,''),('49130c14f023461ba0d05977a5b474b5','13,22','67n0CLs216p63G48uDkTm36Y','2018-07-31 15:21:26',NULL,0,''),('5f553c9f4a8b4a13b5525c610e633c56','1212','fff','2018-07-31 18:30:02',NULL,0,'');

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
  `is_file` tinyint(1) DEFAULT '1' COMMENT '文件为叶子节点',
  `name` varchar(64) DEFAULT NULL COMMENT '自定义名称',
  `thumbnail` varchar(64) DEFAULT '#' COMMENT '文件缩略图',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '上传时间/修改日期',
  `file_id` varchar(32) DEFAULT '#' COMMENT '对应的文件',
  `pid` varchar(32) DEFAULT '1' COMMENT '父级文件夹,1:云盘；9：回收站',
  `reback` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '#' COMMENT '还原到原来的文件夹下',
  `size` varchar(32) DEFAULT '0' COMMENT '文件夹则为所有文件的和/byte',
  `type` varchar(32) DEFAULT '#' COMMENT '文件类型'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件树';

/*Data for the table `tb_file_tree` */

insert  into `tb_file_tree`(`id`,`is_file`,`name`,`thumbnail`,`sort`,`ctime`,`utime`,`file_id`,`pid`,`reback`,`size`,`type`) values ('34c33ef7edb24f5d8f4f8018d7cb42a3',1,'5e68dd8646bc4bd72b0ecab3c360aa80.jpg','fileicon-position fileicon-small-pic',0,'2018-08-06 14:42:51','2018-08-07 16:29:51','7f9089ff1d494d5888d11533b2c6ca88','#','#','0.02Mb','image/jpeg'),('678c60bdb35047459339857d21f2bb4a',0,'fsds1asdas啊啊啊','fileicon-position fileicon-small-foler',0,'2018-08-07 14:28:25','2018-08-07 14:28:25','#','1','0','2513390','folder'),('8b1079640a554156a54a3564789d961c',0,'fssss','fileicon-position fileicon-small-foler',0,'2018-08-07 14:30:40','2018-08-07 14:30:40','#','9','1','0','folder'),('b237b0eece7647cc9e5ed4ac1d5f64c3',1,'favicon _2_.ico','fileicon-position fileicon-small-pic',0,'2018-08-07 16:00:29','2018-08-07 16:00:29','ff46c25cdbfe43088aa122bf03e2e5b7','1','#','0.01Mb','image/x-icon'),('ab633ec9d65546aea3101b23b4f94d23',0,'213','fileicon-position fileicon-small-foler',0,'2018-08-07 17:10:39','2018-08-07 17:10:39','#','1','#','0','folder'),('63d9a9833d80400598e43da897df42b7',0,'阿三打撒','fileicon-position fileicon-small-foler',0,'2018-08-07 18:02:15','2018-08-07 18:02:15','#','1','#','0','folder'),('4e402aa1baf8405aa2576efedc69f0b0',0,'你好','fileicon-position fileicon-small-foler',0,'2018-08-08 09:15:32','2018-08-08 09:15:32','#','678c60bdb35047459339857d21f2bb4a','#','0','folder'),('1108fcf565dd40029102b872ef12e117',0,'123','fileicon-position fileicon-small-foler',0,'2018-08-08 09:49:42','2018-08-08 09:49:42','#','1','#','0','folder'),('e3f23cec67874b6ab08cac05dea865d5',1,'31292651_421410868305729_334832978926501888_n.jpg','fileicon-position fileicon-small-pic',0,'2018-08-08 09:54:32','2018-08-08 09:54:32','134fed4590b74336b96a8c14f8841f96','1','#','0.03Mb','image/jpeg'),('24b9573d4ab2431aa0f5d5436b93b96d',1,'54f825ac68e66024b5e6d2ee896e3e0c.jpg','fileicon-position fileicon-small-pic',0,'2018-08-08 09:56:19','2018-08-08 09:56:19','e58deb44c6934035832f9204c6f74d2c','1','#','0.08Mb','image/jpeg'),('72347a64194e40b3895696dd32440431',1,'favicon.ico','fileicon-position fileicon-small-pic',0,'2018-08-08 10:00:10','2018-08-08 10:00:10','7ca369789c18462e873a2a3e0bd1f2e2','1','#','0.0Mb','image/x-icon'),('f58eda5bcfbe4759a0aabfef44eb98d8',1,'favicon _1_.ico','fileicon-position fileicon-small-pic',0,'2018-08-08 10:02:20','2018-08-09 14:28:06','4889359a862c4c2e916f0f46e0aef6d8','9','1','0.0Mb','image/x-icon'),('a4ecfa04b36045fea98e2abc3b61e5a3',1,'1002211-20170329144612170-956394445.png','fileicon-position fileicon-small-pic',0,'2018-08-08 10:02:58','2018-08-08 10:02:58','9c891becb4cd4ea996f87ecc5022df85','678c60bdb35047459339857d21f2bb4a','#','2.4Mb','image/png'),('82819ef9ffbe490a8be8a9aa687026bf',0,'多少是多少','fileicon-position fileicon-small-foler',0,'2018-08-08 11:31:06','2018-08-09 14:23:37','#','1','1','0','folder'),('54ff672c8b25470c9a24184d5e629081',0,'aasdas','fileicon-position fileicon-small-foler',0,'2018-08-08 14:10:04','2018-08-08 14:10:04','#','4e402aa1baf8405aa2576efedc69f0b0','#','0','folder');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
