package bw5team1.epicenergyservices.security;

import bw5team1.epicenergyservices.entities.Utente;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component

public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) throw new UnauthorizedException("Please insert the token in the Authorization Header");

        String accessToken = authHeader.substring(7);

        jwtTools.verifyToken(accessToken);

        String id = jwtTools.extractIdFromToken(accessToken);
        Utente currentUtente = this.utenteService.findById(UUID.fromString(id));

        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUtente, null, currentUtente.getAuthorities());

        filterChain.doFilter(request, response);

    }

}

@Override
protected boolean shouldNotFilter(HttpServletRequest request){
    return new AntPathMatcher().match("/auth/**", request.getServletPath());
}
