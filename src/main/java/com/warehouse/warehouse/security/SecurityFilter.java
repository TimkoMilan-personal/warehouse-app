package com.warehouse.warehouse.security;

import com.warehouse.warehouse.security.dto.JWToken;
import com.warehouse.warehouse.security.dto.RoleId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityFilter.class);

    private final UserAccessServiceImpl userAccessService;

    public SecurityFilter(UserAccessServiceImpl userAccessService) {
        this.userAccessService = userAccessService;
    }

    @SuppressWarnings("unchecked")
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String authorization = httpServletRequest.getHeader("Authorization");
        if (authorization != null) {
            JWToken jwToken = JWToken.from(JWTUtils.extractJwtToken(authorization));
            Optional<Jws<Claims>> verifiedClaims = userAccessService.validate(jwToken);
            if (verifiedClaims.isPresent()) {
                SecurityContext securityContext = SecurityContextHolder.getContext();
                String userIdFromJWT = verifiedClaims.get().getBody().getSubject();
                List<String> rolesFromJWT = (List<String>) verifiedClaims.get().getBody().get("roles");
                Set<RoleId> setOfRoles = rolesFromJWT.stream().map(RoleId::new).collect(Collectors.toSet());
                securityContext.setAuthentication(new AuthenticationImpl(userIdFromJWT, setOfRoles));
                chain.doFilter(request, response);
                return;
            } else {
                LOG.error("User is not authorized!");
            }
        } else {
            LOG.error("not authorized: header \"Authorization\" is missing !");
        }
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
    }

}
