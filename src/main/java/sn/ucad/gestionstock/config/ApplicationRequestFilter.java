package sn.ucad.gestionstock.config;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.ucad.gestionstock.services.auth.ApplicationUserDetailService;
import sn.ucad.gestionstock.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class ApplicationRequestFilter extends OncePerRequestFilter {


    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    ApplicationUserDetailService  userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            final  String authhHeader  = request.getHeader("Authorization");

            String username = null;
            String jwtToken = null;

           // String idEntreprise = null;
            // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
            if (authhHeader != null &&  authhHeader.startsWith("Bearer "))
            {
                        jwtToken = authhHeader.substring(7);

                        try {
                            username = jwtUtil.getUsernameFromToken(jwtToken);
                            // idEntreprise = jwtUtil.extractIdEntreprise(jwtToken);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Unable to get JWT Token");
                        }
            }
            else {
                logger.warn("JWT Token does is NULL or He is not begin Bearer String");
            }

            //Once we get the token validate it.
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailService.loadUserByUsername(username);
                // if token is valid configure Spring Security to manually set authentication
                if (jwtUtil.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }

            }
       // MDC.put("idEntreprise",idEntreprise);
        filterChain.doFilter(request, response);

        }
}
