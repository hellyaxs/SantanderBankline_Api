package com.dio.santander.Bankline.api.Config;

import com.dio.santander.Bankline.api.Config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//anotações necessarias
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebConfig extends WebSecurityConfigurerAdapter {

    private final SecurityDatabaseService securityDatabaseService;
    private final JwtFilter jwtFilter;

    @Autowired
    public WebConfig(SecurityDatabaseService securityDatabaseService, JwtFilter jwtFilter) {
        this.securityDatabaseService = securityDatabaseService;
        this.jwtFilter = jwtFilter;
    }


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
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/cadastrar").permitAll()
                .antMatchers("/users/**").hasAnyRole("USER") // localhost:8080/user precisa de autorização USER
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated().and()
                .oauth2Login().defaultSuccessUrl("/jwt", true)
                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                . and()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // this will ignore only h2-console csrf, spring security 4+
        // http.csrf().ignoringAntMatchers("/h2/**");
        http.csrf().disable(); // desabilita o csrf assim qualquer clinte(navegador,app,...) pode acessar
        //this will allow frames with same origin which is much more safe
        http.headers().frameOptions().sameOrigin(); // previne contra ataques de clickjacking

    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration registration = CommonOAuth2Provider.GITHUB
//                .getBuilder("github")
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .build();
//
//        return new InMemoryClientRegistrationRepository(registration);
//    }


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
