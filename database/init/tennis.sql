DROP TABLE IF EXISTS `tennis`;

CREATE TABLE `tennis`
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `region` varchar(20) DEFAULT NULL,
    `gubun` varchar(10) DEFAULT NULL,
    `svc_id` varchar(30) NOT NULL,
    `max_class_nm` varchar(20) DEFAULT NULL,
    `min_class_nm` varchar(20) DEFAULT NULL,
    `svc_stat_nm` varchar(10) DEFAULT NULL,
    `svc_nm` varchar(100) DEFAULT NULL,
    `pay_at_nm` varchar(10) DEFAULT NULL,
    `place_nm` varchar(20) DEFAULT NULL,
    `use_tgt_info` varchar(50) DEFAULT NULL,
    `svc_url` varchar(200) DEFAULT NULL,
    `x` varchar(20) DEFAULT NULL,
    `y` varchar(20) DEFAULT NULL,
    `svc_opn_bgn_dt` varchar(30) DEFAULT NULL,
    `svc_opn_end_dt` varchar(30) DEFAULT NULL,
    `rcpt_bgn_dt` varchar(30) DEFAULT NULL,
    `rcpt_end_dt` varchar(30) DEFAULT NULL,
    `area_nm` varchar(20) DEFAULT NULL,
    `img_url` varchar(200) DEFAULT NULL,
    `dtl_cont` text,
    `tel_no` varchar(50) DEFAULT NULL,
    `v_min` varchar(5) DEFAULT NULL,
    `v_max` varchar(5) DEFAULT NULL,
    `rev_std_day_nm` varchar(10) DEFAULT NULL,
    `rev_std_day` varchar(5) DEFAULT NULL,
    `created_at` timestamp,
    `updated_at` timestamp,
        PRIMARY KEY (`id`)
);
