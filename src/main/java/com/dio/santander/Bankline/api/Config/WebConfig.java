package com.dio.santander.Bankline.api.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//anotações necessarias
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    //mapeando rotas para diferentes roles
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers("/users/**").hasAnyRole("USER") // localhost:8080/user precisa de autorização USER
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().formLogin();
    }

    @Override
    //fazendo autenficação de usarios em memoria com spring security
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("elias")
                .password("{noop}testedesenha")
                .roles("ADMIN","USER")
                .and()
                .withUser("jeane")
                .password("senha123")
                .roles("ADMIN");
    }

    /* @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**");

    }*/
}
