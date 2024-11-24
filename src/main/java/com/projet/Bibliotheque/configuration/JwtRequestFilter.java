package com.projet.Bibliotheque.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.projet.Bibliotheque.service.JwtService;
import com.projet.Bibliotheque.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static String CURRENT_USER = "";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        // Si le header contient un token JWT
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7); // Récupère le token
            try {
                username = jwtUtil.getUsernameFromToken(jwtToken); // Récupère le nom d'utilisateur du token
                CURRENT_USER = username; // Met à jour l'utilisateur courant
            } catch (IllegalArgumentException e) {
                System.out.println("Impossible de récupérer le token JWT");
            } catch (ExpiredJwtException e) {
                System.out.println("Le token JWT a expiré");
            }
        } else {
            System.out.println("Le token JWT ne commence pas par Bearer");
        }

        // Si le nom d'utilisateur est valide et qu'il n'y a pas déjà d'authentification
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtService.loadUserByUsername(username); // Charge les détails de l'utilisateur

            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                // Crée un token d'authentification et l'ajoute au contexte de sécurité
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response); // Passe à la chaîne de filtres suivante
    }
}
