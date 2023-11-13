package com.stlapp.stlappback.configuraciones;

import com.stlapp.stlappback.servicios.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Se utiliza el filtro");
        System.out.println(request.getMethod());

        if (!request.getRequestURI().equals("/generate-token") && !(request.getRequestURI().equals("/usuarios/") && request.getMethod().equals("POST") )) {
            System.out.println("Se obtiene Authorization para:");
            String requestTokenHeader = request.getHeader("Authorization");
            System.out.println(request.getRequestURI());

            String username = null;
            String jwtToken = null;

            if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){
                jwtToken = requestTokenHeader.substring(6);

                try {
                    username = this.jwtUtil.extractUsername(jwtToken);
                }catch (ExpiredJwtException expiredJwtException){
                    System.out.println("El token ha expirado");
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }else{
                System.out.println("Token invalido. No empieza con bearer string: "+jwtToken);
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                System.out.println("Usuario != null y getAuthentication == null");
                System.out.println("Usuario: "+username);
                System.out.println("getAuthentication: "+SecurityContextHolder.getContext().getAuthentication());

                if(this.jwtUtil.validateToken(jwtToken, userDetails)){
                    System.out.println("El token si es valido");
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }else{
                    System.out.println("El token no es valido");
                }
            }else{
                System.out.println("Usuario null o getAuthentication != null");
                System.out.println("Usuario: "+username);
                System.out.println("getAuthentication: "+SecurityContextHolder.getContext().getAuthentication());
            }
        }else{
            System.out.println("Generandop token o guardando usuario: "+request.getRequestURI());
        }

        filterChain.doFilter(request,response);
    }
}
