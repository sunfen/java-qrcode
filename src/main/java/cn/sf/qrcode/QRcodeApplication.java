package cn.sf.qrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages="cn.sf.qrcode.*")
@EnableScheduling
public class QRcodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QRcodeApplication.class, args);
	}
	
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

