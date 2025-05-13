package com.mvc.MVCDemo.configs;
import com.mvc.MVCDemo.Auth.AudittorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "getAuditConfig")
public class ModelMapperConfig {
    @Bean
    public ModelMapper geetModelMapper(){
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditConfig(){
        return new AudittorAwareImpl();

    }
}

