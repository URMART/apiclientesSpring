package com.api_clientes.app.models.Service;

import com.api_clientes.app.models.Dao.RolRepository;
import com.api_clientes.app.models.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImp implements  RolService{

    @Autowired
    RolRepository rolRepository;

    @Override
    public Rol findByNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
}
