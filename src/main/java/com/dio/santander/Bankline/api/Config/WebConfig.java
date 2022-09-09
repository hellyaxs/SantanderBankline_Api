package com.dio.santander.Bankline.api.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

//anotações necessarias
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityDatabaseService securityDatabaseService;

    @Autowired
    public void globalUserDetals(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(securityDatabaseService).passwordEncoder(new BCryptPasswordEncoder());
       //NoOpPasswordEncoder.getInstance();-> sem usar criptografia
    }
    //mapeando rotas para diferentes roles
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers("/users/**").hasAnyRole("USER") // localhost:8080/user precisa de autorização USER
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and().httpBasic();

        // this will ignore only h2-console csrf, spring security 4+
        http.csrf().ignoringAntMatchers("/h2/**");
        http.csrf().ignoringAntMatchers("/users/**");
        //this will allow frames with same origin which is much more safe
        http.headers().frameOptions().sameOrigin();
    }

   /* @Override
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
    }*/

    /* @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**");

    }*/
}
