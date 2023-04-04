package com.api_clientes.app.models.Dao;

import com.api_clientes.app.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
    Rol findByNombre(String nombre);


}
