package br.com.edu.ufcg.cccfarma.api.config;

import br.com.edu.ufcg.cccfarma.api.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .headers().frameOptions().disable().and()
            .httpBasic()
                .and()
            .authorizeRequests()
                .antMatchers("/admin/**/").hasRole("ADMIN")
                .antMatchers("/public/**/", "/h2-console/**/").permitAll()
                .antMatchers("/protected/**/").hasRole("USER")
                .antMatchers("/logout").hasAnyRole()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .and()
            .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
            .csrf().disable();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    

    //    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser("gustavo").password("{noop}alves").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}admin").roles("USER", "ADMIN");
//    }
}
