package com.shoeshop.config;

import com.shoeshop.service.AdminService;
import com.shoeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(2)
    public static class CustomerConfigurationAdapter extends  WebSecurityConfigurerAdapter{

        @Autowired
        private UserService userService;
        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(userService);
            auth.setPasswordEncoder(passwordEncoder());
            return auth;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .requestMatchers(new AntPathRequestMatcher("/**.html"))
                    .requestMatchers(new AntPathRequestMatcher("/static/**"))
                    .requestMatchers(new AntPathRequestMatcher("/templates/**"))
                    .antMatchers("/error");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

//            http.authorizeRequests()
////                    .antMatchers("/user/*").hasRole("CUSTOMER")
////                    .antMatchers("/user/register").permitAll()
//                    .antMatchers("/user/order").hasRole("CUSTOMER")
//                    .antMatchers("/user/edit-account").hasRole("CUSTOMER")
//                    .and().csrf().disable();
            http.authorizeRequests()
                    .antMatchers("/user/order").hasRole("CUSTOMER")
                    .antMatchers("/user/edit-account").hasRole("CUSTOMER")
                    .antMatchers("/user/orders").hasRole("CUSTOMER")
                    .antMatchers("/api/promotion").hasRole("CUSTOMER")
                    .antMatchers("/user/register").permitAll()
                    .antMatchers("/user/forgot-password").permitAll()
                    .antMatchers("/user/reset-password").permitAll()
                    .antMatchers("/user/login").permitAll()
                    .antMatchers("/product/**").permitAll()
                    .antMatchers("/api/address/*").permitAll()
                    .antMatchers("/api/product/*").permitAll()
                    .antMatchers("/api/cart/**").permitAll()
                    .anyRequest().authenticated()
                    .and().csrf().disable();

        //       Cấu hình cho Login Form and logout
            http.authorizeRequests().and()
                    .formLogin()
                    .loginPage("/user/login")
                    .loginProcessingUrl("/user/login")
                    .failureUrl("/user/login?status=error")
                    .defaultSuccessUrl("/user/login?status=success")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/user/logout")
                    .logoutSuccessUrl("/")
                    .permitAll().and().exceptionHandling().accessDeniedPage("/403");

        }
    }

    @Configuration
    @Order(1)
    public static class SecurityAdminConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private AdminService adminService;


        @Bean
        public DaoAuthenticationProvider authenticationProviderAdmin() {
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(adminService);
            auth.setPasswordEncoder(passwordEncoder());
            return auth;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProviderAdmin());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                    .authorizeRequests()
                    .anyRequest()
                    .hasAnyRole("ADMIN","EMPLOYEE")
                    .and()
                    .formLogin()
                    .loginPage("/admin/login")
                    .loginProcessingUrl("/admin/login")
                    .failureUrl("/admin/login?error")
                    .defaultSuccessUrl("/admin").permitAll()

                    .and()
                    .logout()
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/admin/login").permitAll().

            and().exceptionHandling().accessDeniedPage("/403")
                    .and()
                    .csrf().disable();

        }
    }



}
