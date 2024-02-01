package com.example.geoCoding.auth;
import com.example.geoCoding.service.CompanyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private CompanyService companyService;

@Autowired
private JwtService jwtService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader=request.getHeader("Authorization");
        final String jwt;
        final String companyName;
        System.out.println("do something");

        if(authHeader==null||!authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }

        jwt=authHeader.substring(7);
        System.out.println("Authorized");
        companyName=jwtService.extractUserName(jwt);

        if(companyName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails = companyService.loadByCompanyName(companyName);
            if (jwtService.isTokenValid(jwt, companyName)) {

                log.info(jwt);

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails
                        , null, userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println(SecurityContextHolder.getContext());
                System.out.println(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
