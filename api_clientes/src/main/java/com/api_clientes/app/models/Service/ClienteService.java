package com.api_clientes.app.models.Service;


import com.api_clientes.app.models.Clientes;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    public List<Clientes> findAll();
    public Clientes save(Clientes cliente);

    public Optional<Clientes> findById(Long Id);
    public Optional<Clientes> findByEmail(String email);

    public void DeleteById(Long Id);



}
