package com.dio.santander.Bankline.api.Config;

import com.dio.santander.Bankline.api.Entities.Corretista;
import com.dio.santander.Bankline.api.repository.CorretistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SecurityDatabaseService implements UserDetailsService {

    private final CorretistaRepository corretistaRepository;
    @Autowired
    public SecurityDatabaseService(CorretistaRepository corretistaRepository) {
        this.corretistaRepository = corretistaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Corretista> entityCorrentista = corretistaRepository.findByUsername(username);
        Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();

        if(entityCorrentista.isPresent()){
            entityCorrentista.get().getRoles().forEach(role -> {
                authoritySet.add(new SimpleGrantedAuthority("ROLE_" + role));
            });
            var BCryptPasswordEncoder = new BCryptPasswordEncoder();

            return new User(entityCorrentista.get().getUsername(),entityCorrentista.get().getPassword(), authoritySet);
        }else{
            throw new UsernameNotFoundException(username);
        }

    }
    public UserDetails loadUserByUsername(String username,String password) throws UsernameNotFoundException {
        Optional<Corretista> entityCorrentista = corretistaRepository.findByUsername(username);
        Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();

        if(entityCorrentista.isPresent()){
            entityCorrentista.get().getRoles().forEach(role -> {
                authoritySet.add(new SimpleGrantedAuthority("ROLE_" + role));
            });
            var BCryptPasswordEncoder = new BCryptPasswordEncoder();
            if(!BCryptPasswordEncoder.matches(password,entityCorrentista.get().getPassword())){
                throw new UsernameNotFoundException(username);
            }

            return new User(entityCorrentista.get().getUsername(),entityCorrentista.get().getPassword(), authoritySet);
        }else{
            throw new UsernameNotFoundException(username);
        }



    }

    private Corretista fillUserDetails(String username){
        Optional<Corretista> entityCorrentista = corretistaRepository.findByUsername(username);
        Set<GrantedAuthority> authoritySet = new HashSet<GrantedAuthority>();
        entityCorrentista.ifPresent(corretista -> corretista.getRoles().forEach(role -> {
            authoritySet.add(new SimpleGrantedAuthority("ROLE_" + role));
        }));

        return entityCorrentista.orElse(null);
    }

}
