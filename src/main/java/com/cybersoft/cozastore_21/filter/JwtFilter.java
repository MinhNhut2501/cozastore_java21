package com.cybersoft.cozastore_21.filter;

import com.cybersoft.cozastore_21.util.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//tat ca request deu vao filter nay
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //lay gia tri cua header co key la Authorization
        try {
            String header = request.getHeader("Authorization");
            if(header.startsWith("Bearer ")){
                // cat bo chu Bearer va lay ra token
                String token = header.substring(7);
                // giai ma token
                Claims claims = jwtHelper.decodeToken(token);
                if(claims != null){
                    //tao chung thuc cho security
                    SecurityContext context = SecurityContextHolder.getContext();
                    UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("","",new ArrayList<>());
                    context.setAuthentication(user);
                }
            }
        }catch (Exception e){

        }

        filterChain.doFilter(request,response);
    }
}
