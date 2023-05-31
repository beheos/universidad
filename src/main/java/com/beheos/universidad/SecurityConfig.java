package com.beheos.universidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
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
	            .loginPage("/login")
	            .defaultSuccessUrl("/alumnos/", true)
	            .permitAll()
	            .and()
	            .logout()
	            .permitAll();
	    }
	 
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }
	 
	
	
	/*De esta manera es para que se logen de manera de codigo duro
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin123").roles("ADMIN")
                .and()
                .withUser("user").password("user123").roles("USER");
    }*/

    

   /* @Override
     protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        		.antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/home").authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/alumnos/")
                .and().exceptionHandling().accessDeniedPage("/403");
    }*/
      
      /*este es el login por default*/
     /* @Override
      protected void configure(HttpSecurity http) throws Exception {
          http.authorizeRequests()
                  .antMatchers("/admin").hasRole("ADMIN")
                  .antMatchers("/").hasAnyRole("ADMIN", "USER")
                  .and().formLogin()
                  .and().exceptionHandling().accessDeniedPage("/403");
      }*/
    

}
