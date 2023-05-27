package com.beheos.universidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery("SELECT username, password, enabled FROM usuarios WHERE username = ?")
            .authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username = ?")
            .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/alumnos/*").hasAnyRole("ADMIN", "USER")
            .antMatchers("/admin").hasRole("ADMIN")
            .anyRequest().permitAll()
            .and()
            .formLogin()
            .loginPage("/login/entrar")
            .defaultSuccessUrl("/alumnos/lista", true)
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }
}
