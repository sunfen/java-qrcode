package cn.sf.qrcode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter  {
	
	@Value("${qrcode.file.upload-path}")
	private String FILE_Path;
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/public-resources/");
		
		registry.addResourceHandler("/file/**").addResourceLocations("file:" + FILE_Path + "/");
        super.addResourceHandlers(registry);
    }
	

}
