package com.dio.santander.Bankline.api.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//anotações necessarias
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Override
    //fazendo autenficação de usarios em memoria com spring security
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("elias")
                .password("{noop}testedesenha")
                .roles("ADMIN")
                .and()
                .withUser("jeane")
                .password("senha123")
                .roles("USER");
    }

    /* @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**");

    }*/
}
