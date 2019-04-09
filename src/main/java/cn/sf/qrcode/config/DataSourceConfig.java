package cn.sf.qrcode.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@EnableJpaRepositories(basePackages="cn.sf.qrcode")
@EnableTransactionManagement
public class DataSourceConfig {

}
