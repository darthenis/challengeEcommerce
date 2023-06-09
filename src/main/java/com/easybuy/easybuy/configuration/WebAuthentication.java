package com.easybuy.easybuy.configuration;

import com.easybuy.easybuy.models.Client;
import com.easybuy.easybuy.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    ClientService clientService;

    @Bean
    public PasswordEncoder passwordEncoder(){

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputEmail ->{

            Optional<Client> client = clientService.findByEmail(inputEmail);

            if(client != null) {
                if (inputEmail.equals("admin@mindhub.com")) {
                    return new User(client.get().getEmail(), client.get().getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                } else {
                    return new User(client.get().getEmail(), client.get().getPassword(), client.get().isEnabled(), true, true, true, AuthorityUtils.createAuthorityList("CLIENT"));
                }
            }else {
                throw new UsernameNotFoundException("Unknown user: " + inputEmail);
            }




        });

    }
}
