package com.tigra.ats.config;


import com.tigra.ats.service.UserDetailsServiceImpl;
import com.tigra.ats.service.logic.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;


    private String[] resources = new String [] {
            "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
    };

    private final String[] jobOperationUrls = new String[] {
            "/save-job-props", "/create-job", "/delete-job/**"
    };

    private final String[] employeeUrls = new String[] {
            "/employee-creator", "/create-employee"
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login", "/").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers(jobOperationUrls).hasRole("SZAKMAIVEZETO")
                .antMatchers(employeeUrls).access("hasRole('HRVEZETO') or hasRole('HRMUNKATARS')")
                .antMatchers(resources).permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=True")
                .failureHandler(failureHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                    .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?Logout");
    }
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
