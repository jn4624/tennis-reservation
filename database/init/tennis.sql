DROP TABLE IF EXISTS `tennis`;

CREATE TABLE `tennis`
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `region` varchar(20) DEFAULT NULL,
    `service_id` varchar(30) NOT NULL,
    `service_status` varchar(10) DEFAULT NULL,
    `service_name` varchar(100) DEFAULT NULL,
    `pay_at` varchar(10) DEFAULT NULL,
    `use_target` varchar(50) DEFAULT NULL,
    `service_url` varchar(200) DEFAULT NULL,
    `longitude` double DEFAULT NULL,
    `latitude` double DEFAULT NULL,
    `receipt_begin_date_Time` timestamp DEFAULT NULL,
    `receipt_end_date_Time` timestamp DEFAULT NULL,
    `area_name` varchar(20) DEFAULT NULL,
    `image_url` varchar(200) DEFAULT NULL,
    `detail_content` text,
    `tel_no` varchar(50) DEFAULT NULL,
    `service_begin_time` varchar(5) DEFAULT NULL,
    `service_end_time` varchar(5) DEFAULT NULL,
    `cancel_standard` varchar(10) DEFAULT NULL,
    `cancel_standard_day` varchar(5) DEFAULT NULL,
    `created_at` timestamp,
    `updated_at` timestamp,
    PRIMARY KEY (`id`)
);
