package com.cybersoft.cozastore_21.config;

import com.cybersoft.cozastore_21.filter.JwtFilter;
import com.cybersoft.cozastore_21.provider.CustomAuthenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //khai bao chuan ma hoa Bcrypt va luu tru tren IOC
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomAuthenProvider customAuthenProvider;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                //khai bao su dung custom userdetailservice
                .authenticationProvider(customAuthenProvider)
                .build();

    }


    //day la filter dung de custom rule lien quan toi link
    // cau hinh cua security
    // java 8 va 11: antMatchers
    // java 17~ : requestAntMatchers
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable() // tat cau hinh lien quan toi tan cong csrf, 1 link khong cho nhieu nguoi vao 1 luc
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()  //quy dinh lai cac rule lien quan toi chung thuc cho link duoc goi
                .antMatchers("/signin","/signup").permitAll()  //permitAll cho phep vao ko can chung thuc
                .anyRequest().authenticated()  // tat ca cac link con lai deu phai chung thuc
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
