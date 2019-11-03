package com.tigra.ats.config;


import com.tigra.ats.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private String[] resources = new String [] {
            "/include/**", "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
    };

    private String[] jobOperationUrls = new String[] {
            "/save-job-props", "/create-job", "/delete-job/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /*.httpBasic().and()
                .csrf().disable()*/
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login", "/").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers(jobOperationUrls).hasRole("SZAKMAIVEZETO")
                .antMatchers(resources).permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=True")
                .usernameParameter("username")
                .passwordParameter("password")
                    .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?Logout");
    }
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //password encryption

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


}
