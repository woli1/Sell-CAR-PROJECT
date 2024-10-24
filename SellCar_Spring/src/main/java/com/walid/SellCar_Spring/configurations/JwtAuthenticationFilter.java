package com.walid.SellCar_Spring.configurations;


import com.walid.SellCar_Spring.services.jwt.UserService;
import com.walid.SellCar_Spring.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final UserService userService;
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

         if(StringUtils.isEmpty(authHeader)||!StringUtils.startsWith(authHeader,"Bearer")){
             filterChain.doFilter(request, response);
             return;
         }
         jwt = authHeader.substring(7);
         userEmail=jwtUtil.extractUsername(jwt);
         if(StringUtils.isNoneEmpty(userEmail)&& SecurityContextHolder.getContext().getAuthentication()==null){
             UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
             if(jwtUtil.isTokenValid(jwt,userDetails)){
                 SecurityContext context=SecurityContextHolder.createEmptyContext();
                 UsernamePasswordAuthenticationToken authentication =new UsernamePasswordAuthenticationToken(
                         userDetails,null,userDetails.getAuthorities()
                 );
                 authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 context.setAuthentication(authentication);
                 SecurityContextHolder.setContext(context);

             }

         }
         filterChain.doFilter(request, response);


    }





}
