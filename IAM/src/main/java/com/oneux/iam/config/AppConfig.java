package com.oneux.iam.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class AppConfig {

	@Value("${app.messages.folder}")
	String folder;
    @Bean
    MessageSource messageSource() throws Exception {

		ReloadableResourceBundleMessageSource messageSource = null;
		try {
		
			File path = new File(folder);
			log.info("Message source properties are loaded from " + path.getCanonicalPath());
			String folderPath = "file:" + path.getCanonicalPath();  
			messageSource = new ReloadableResourceBundleMessageSource();
			System.out.println(folderPath);
			messageSource.setBasenames(folderPath + "/messages");
			messageSource.setDefaultEncoding("UTF-8");

		} catch (Exception e) {
			log.error("Unable to load message properties", e);
			throw e;
		}
		
		return messageSource;
	}

    @Bean
    static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}