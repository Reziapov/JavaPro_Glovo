package com.hillel.reziapov.homework.javapro_spring_glovo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(withDefaults())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.POST, "/orders").hasRole("ADMIN")
                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    UserDetailsManager users (DataSource dataSource) {
        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        return users;
    }

    @Bean
    public JdbcUserDetailsManager addUsersDataBase(DataSource dataSource) {

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource) {
            @Override
            public void createUser(UserDetails user) {
                String sql = "INSERT INTO public.users (username, password) VALUES (?, ?)";
                getJdbcTemplate().update(sql, user.getUsername(), user.getPassword());
                if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
                    user.getAuthorities().forEach(authority -> {
                        String authoritySql = "INSERT INTO public.authorities (username, authority) VALUES (?, ?)";
                        getJdbcTemplate().update(authoritySql, user.getUsername(), authority.getAuthority());
                    });
                }
            }

            @Override
            protected String getAuthoritiesByUsernameQuery() {
                return "SELECT username, authority FROM authorities WHERE username = ?";
            }
        };

        return users;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    }

















