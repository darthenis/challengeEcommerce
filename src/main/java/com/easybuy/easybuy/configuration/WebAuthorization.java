package com.easybuy.easybuy.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebAuthorization {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers( "/").permitAll();


        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");


        http.logout().logoutUrl("/api/logout").deleteCookies();

        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.exceptionHandling().accessDeniedHandler((req, res, ex) ->{

            if(req.isUserInRole("ADMIN")){

                res.sendRedirect("/admin/manager.html");

            } else if(req.isUserInRole("ADMIN")) {

                res.sendRedirect("/web/accounts.html");

            } else {res.sendError(HttpServletResponse.SC_FORBIDDEN);

            }

        });

        http.exceptionHandling().authenticationEntryPoint((req, res, ex) -> {

            if(req.getRequestURI().contains("web") || req.getRequestURI().contains("admin")){

                res.sendRedirect("/web/index.html");

            } else {

                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);

            }


        });

        http.formLogin().successHandler((req, res, auth) -> this.clearAuthenticationAttributes(req));

        http.formLogin().failureHandler((req, res, exc) -> {

            if(exc.getMessage().equals("User is disabled")){

                res.sendError(HttpServletResponse.SC_FORBIDDEN);

            }else{

                res.sendError(HttpServletResponse.SC_UNAUTHORIZED);

            }
        });

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        //http.addFilterAfter(new IndexPageFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }

}
