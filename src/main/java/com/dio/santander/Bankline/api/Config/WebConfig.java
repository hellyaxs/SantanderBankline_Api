package com.dio.santander.Bankline.api.Config;

import com.dio.santander.Bankline.api.Config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebConfig  {

    private final SecurityDatabaseService securityDatabaseService;
    private final JwtFilter jwtFilter;
    ;

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

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll());

//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // this will ignore only h2-console csrf, spring security 4+
        // http.csrf().ignoringAntMatchers("/h2/**");
        http.csrf(AbstractHttpConfigurer::disable); // desabilita o csrf assim qualquer clinte(navegador,app,...) pode acessar
        //this will allow frames with same origin which is much more safe


    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui.html/**")) // configurando para ignorar a rota do swagger
                .requestMatchers(new AntPathRequestMatcher("/login/**"))
                .requestMatchers(new AntPathRequestMatcher("/cadastrar/**"))
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")); // configurando para ignorar a rota do h2-console
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
