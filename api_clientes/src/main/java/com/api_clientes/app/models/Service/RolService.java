package com.api_clientes.app.models.Service;

import com.api_clientes.app.models.Rol;

public interface RolService {
    Rol findByNombre(String nombre);
}
