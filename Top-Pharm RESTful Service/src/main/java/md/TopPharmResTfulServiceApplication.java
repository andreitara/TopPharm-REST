package md;

import md.pharm.restservice.service.util.HibernateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.Filter;

@Configuration
@SpringBootApplication
public class TopPharmResTfulServiceApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        HibernateUtil.buildROSessionFactory();
        HibernateUtil.buildMDSessionFactory();
        HibernateUtil.createDefaultAdminsIfUsersNotExist();
        SpringApplication.run(TopPharmResTfulServiceApplication.class, args);
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
        handlerMapping.setUseSuffixPatternMatch(false);
        handlerMapping.setUseTrailingSlashMatch(false);
        return handlerMapping;
    }
}
