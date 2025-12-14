package com.example.Nucleus.security;

import com.example.Nucleus.exception.UserNotFoundException;
import com.example.Nucleus.model.User;
import com.example.Nucleus.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            final String requestToken = request.getHeader("Authorization");
            if(requestToken == null || !requestToken.startsWith("Bearer")){
                doFilter(request, response, filterChain);
                return;
            }

            String token = requestToken.split("Bearer ")[1];
            String username = jwtUtils.getUsernameFromAccessToken(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
                User user = userRepository.findByEmail(username)
                        .orElseThrow(()-> new UserNotFoundException("User not found."));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            doFilter(request, response, filterChain);

        }catch (Exception ex){
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}
