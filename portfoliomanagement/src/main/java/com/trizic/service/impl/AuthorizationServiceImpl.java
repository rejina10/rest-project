package com.trizic.service.impl;

import com.trizic.modal.Advisor;
import com.trizic.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationServiceImpl implements AuthenticationProvider {

    @Autowired
    private AdvisorService advisorService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        Advisor user = advisorService.findByUserNameAndPassword(name, password);
        if (user == null ) {
            throw new BadCredentialsException("Username not found.");
        }


        List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);

    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}