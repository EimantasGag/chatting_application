package com.eimantasgag.learning_springboot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
	private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //pages that doesnt required to be logged in
        http.authorizeRequests().antMatchers("/login*", "/register*", "/scripts/register*").permitAll();

        //all other pages required to be logged
        http.authorizeRequests().antMatchers("/**").hasRole("USER");

        http.authorizeRequests().and() 
            .rememberMe().tokenRepository(this.persistentTokenRepository()) 
            .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

            
        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/perform_login")
            .failureUrl("/login?error=true");
            
        http.logout()
            .logoutUrl("/perform_logout")
            .deleteCookies("JSESSIONID");
            
        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(this.dataSource);
        return db;
    }
    
}
