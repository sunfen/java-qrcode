ALTER TABLE `codes`	ADD COLUMN `weixin_times` DECIMAL(8,2) NULL DEFAULT '0.00' AFTER `user_id`;
ALTER TABLE `codes`	ADD COLUMN `alipay_times` DECIMAL(8,2) NULL DEFAULT '0.00' AFTER `user_id`;
