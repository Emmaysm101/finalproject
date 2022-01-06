package org.perscholas.config;


import org.perscholas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UsersService usersService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Bean
    public UserDetailsService userDetailsService() {
    	return new UserDetailsService();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**/*").permitAll();
        http.authorizeRequests().antMatchers(
                "/registration**",
                "/",
                "/itemDetail/**",
                "/*.js",
                "/*.jsp",
                "/*.css",
                "/images/**")
                .permitAll()
                .antMatchers("/adminHome").hasAnyAuthority("Admin")
                .antMatchers("/list_item").hasAnyAuthority("Admin")
                .antMatchers("/list_order").hasAnyAuthority("Admin")
                .antMatchers("/list_user").hasAnyAuthority("Admin")
                .antMatchers("/new_item").hasAnyAuthority("Admin")
                .antMatchers("/new_order").hasAnyAuthority("Admin")
                .antMatchers("/new_user").hasAnyAuthority("Admin")
                .antMatchers("/update_item").hasAnyAuthority("Admin")
                .antMatchers("/update_user").hasAnyAuthority("Admin")
                .antMatchers("/update_order").hasAnyAuthority("Admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
                .and();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(usersService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
