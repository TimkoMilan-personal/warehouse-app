package com.warehouse.warehouse.security;

import com.warehouse.warehouse.security.dto.RoleId;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


import java.util.*;

class AuthenticationImpl implements Authentication {

    private final String name;
    private final Collection<GrantedAuthorityImpl> grantedAuthorities;

    private boolean isAuthenticated;

    public AuthenticationImpl(String name, Set<RoleId> roles) {
        this.name = name;
        List<GrantedAuthorityImpl> authorities = new ArrayList<>();
        roles.forEach(r->
                authorities.add(new GrantedAuthorityImpl(r.getId()))
        );
        this.grantedAuthorities = Collections.unmodifiableCollection(authorities);
        this.isAuthenticated = true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return name;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return name;
    }

}