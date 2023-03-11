package com.eimantasgag.learning_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //login page doesnt required to be logged in
        http.authorizeRequests().antMatchers("/login*").permitAll();
        http.authorizeRequests().antMatchers("/register*").permitAll();
        http.authorizeRequests().antMatchers("/scripts/register*").permitAll();

        //all other pages required to be logged
        http.authorizeRequests().antMatchers("/**").hasRole("USER");
            
        http.formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/perform_login")
            .failureUrl("/login?error=true");
            
        http.logout()
            .logoutUrl("/perform_logout")
            .deleteCookies("JSESSIONID");
            
        return http.build();
    }

    

    // @Bean
    // public InMemoryUserDetailsManager userDetailsService() {
    //     UserDetails user1 = User.withUsername("user1")
    //         .password(passwordEncoder().encode("user1Pass"))
    //         .roles("USER")
    //         .build();
    //     UserDetails user2 = User.withUsername("user2")
    //         .password(passwordEncoder().encode("user2Pass"))
    //         .roles("USER")
    //         .build();
    //     UserDetails admin = User.withUsername("admin")
    //         .password(passwordEncoder().encode("adminPass"))
    //         .roles("ADMIN")
    //         .build();
    //     return new InMemoryUserDetailsManager(user1, user2, admin);
    // }
    
}
