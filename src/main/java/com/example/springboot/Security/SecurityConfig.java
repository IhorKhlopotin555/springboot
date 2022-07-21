package com.example.springboot.Security;

import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.models.Customer;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.Arrays;
import java.util.stream.Collectors;


@Configuration
@EnableWebMvc
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

private CustomerDAO customerDAO;

@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.userDetailsService(new UserDetailsService() {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Customer customer = customerDAO.findByLogin(username);
        User user = new User(
                username,
                customer.getPassword(),
                customer.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
        );
        return user;
    }
});
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers(HttpMethod.POST, "/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/customers").permitAll()
                .antMatchers("/customers").hasAnyRole("ADMIN", "MANAGER", "USER")
                .and();

        http = http.httpBasic().and();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .cors().configurationSource(corsConfigurationSource()).and();

    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:3000"));
        configuration.addAllowedHeader("*");
        configuration.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.HEAD.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()));
        configuration.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
