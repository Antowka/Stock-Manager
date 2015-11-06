package ru.antowka.stock.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import javax.sql.DataSource;

/**
 * Created by Anton Nikanorov on 21.10.15.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring()
           .antMatchers("/static/js/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .passwordEncoder(new Md5PasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery(getUserQuery())
                .authoritiesByUsernameQuery(getAuthoritiesQuery());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Refactor login form
            .formLogin()
            .loginProcessingUrl("/auth_validate")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/portfolio")
            .loginPage("/")
            .failureUrl("/?error")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/?logout")
            .logoutUrl("/logout")
            .permitAll()
            .and()
            .authorizeRequests()
            .antMatchers("/static/**").permitAll()
            .antMatchers("/").anonymous()
            .anyRequest().authenticated();
    }

    private String getUserQuery() {
        return "SELECT username, password, enabled "
                + "FROM users "
                + "WHERE username = ?";
    }

    private String getAuthoritiesQuery() {
        return "SELECT username, role_name as authority "
                + "FROM users, user_role, role "
                + "WHERE users.user_id = user_role.user_id "
                + "AND role.role_id = users.user_id "
                + "AND users.username = ? ";
    }
}