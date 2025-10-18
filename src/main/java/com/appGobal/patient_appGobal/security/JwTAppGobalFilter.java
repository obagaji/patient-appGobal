package com.appGobal.patient_appGobal.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwTAppGobalFilter extends OncePerRequestFilter {

    @Autowired
    PatientJwtUtil patientJwtUtil;
    @Autowired
    PatientsDetail patientsDetail;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
       final String authHeader;
       final String authToken;
       final String username;
        authHeader = request.getHeader("Authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer "))
        {
            filterChain.doFilter(request,response);
            return;
        }
        authToken = authHeader.substring(7);
        if (patientJwtUtil.validateToken(authToken,patientsDetail))
        {
            username = patientJwtUtil.extractUsername(authToken);
            if (username != null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UsernamePasswordAuthenticationToken userToken =
                        new UsernamePasswordAuthenticationToken(username,null,patientsDetail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
            filterChain.doFilter(request,response);
        }
        else {
            throw new ServletException(String.valueOf(HttpStatus.FORBIDDEN));
        }


    }
}
