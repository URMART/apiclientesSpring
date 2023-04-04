package com.api_clientes.app.security.userDetails;

import com.api_clientes.app.models.Clientes;
import com.api_clientes.app.models.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {


    @Autowired
    private ClienteService clienteService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Clientes clientes = clienteService.findByEmail(email)
                 .orElseThrow(()-> new UsernameNotFoundException("el usuario con email no existe"));
        return clientes;
    }


}
