CREATE TABLE IF NOT EXISTS `user` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`firstname` varchar(255) NOT NULL,
`lastname` varchar(255) NOT NULL,
`phone` varchar(255) NOT NULL,
`email` varchar(255) NOT NULL,
PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
