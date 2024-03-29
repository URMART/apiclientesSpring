package com.api_clientes.app.security.login;


import com.api_clientes.app.security.authCredentials.AuthCredentials;
import com.api_clientes.app.security.tokenconfig.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserDetailsService userDetailsService;
    public String login(AuthCredentials authCredentials) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(authCredentials.getEmail());
        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList());

        String token = TokenUtils
                .createToken(userDetails.getUsername(), userDetails.getUsername(), roles);
        return token;
    }

}
