package com.dio.santander.Bankline.api.Controllers;

import com.dio.santander.Bankline.api.Config.SecurityDatabaseService;
import com.dio.santander.Bankline.api.Config.jwt.Jwtutils;
import com.dio.santander.Bankline.api.DTO.Login;
import com.dio.santander.Bankline.api.DTO.LoginAcess;
import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.Services.CorrentistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class LoginController {

    @Autowired
    private Jwtutils jwtutils;
    @Autowired
    private SecurityDatabaseService securityDatabaseService;
    @Autowired
    private CorrentistaService correntistaService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login){
        var user = securityDatabaseService.loadUserByUsername(login.getLogin(),login.getSenha());
        return ResponseEntity.ok(jwtutils.generateToken(user));

    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Corretista> loginPost(@Valid @RequestBody LoginAcess loginAcess){
        var corretista = Corretista.builder()
                .cfp(loginAcess.getCfp())
                .username(loginAcess.getLogin())
                .password(loginAcess.getSenha())
                .nome(loginAcess.getNome())
                .roles(new ArrayList<>(){{add("USER");}})
                .build();
        correntistaService.save(corretista);

        return ResponseEntity.ok(corretista);
    }

    @GetMapping("/cadastrar")
    public String loginPost(){
        return "cadastro teste passou";
    }

    @PostMapping("/logout")
    public String logout(){
        return "cadastro teste passou";
    }

    @GetMapping("/jwt")
    public String jwt(@AuthenticationPrincipal Jwt user){
        String email = user.getClaim("email");
        String claim = user.getClaims().toString();
        String token = user.getTokenValue();
    return "email: "+email+"\n claim: "+claim+"\n token: "+token;
    }
}
