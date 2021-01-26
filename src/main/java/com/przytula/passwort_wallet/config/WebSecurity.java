package com.przytula.passwort_wallet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailService userDetailService;

    @Autowired
    public WebSecurity(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/sign-up/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successForwardUrl("wallet")
                .defaultSuccessUrl("/wallet")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/logout")
                .addLogoutHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    for (Cookie cookie : httpServletRequest.getCookies()) {
                        String cookieName = cookie.getName();
                        Cookie cookieToDelete = new Cookie(cookieName, null);
                        cookieToDelete.setMaxAge(0);
                        httpServletResponse.addCookie(cookieToDelete);
                    }
                })
                .and()
                .httpBasic();
    }


    @Bean
    public PasswordEncoder encoder(){
        String encodeId = "bcrypt";
        Map<String, PasswordEncoder> encoderMap =  new HashMap<>();
        encoderMap.put(encodeId, new BCryptPasswordEncoder());
        encoderMap.put("hmac", new HmacPasswordEncoder());
        return  new DelegatingPasswordEncoder(encodeId , encoderMap);
    }

}
