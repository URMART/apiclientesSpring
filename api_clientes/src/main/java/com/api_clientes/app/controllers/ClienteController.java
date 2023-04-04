package com.api_clientes.app.controllers;

import com.api_clientes.app.models.Clientes;
import com.api_clientes.app.models.Rol;
import com.api_clientes.app.models.Service.ClienteService;
import com.api_clientes.app.models.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;
    @Autowired
    private RolService rolService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/listar")
    public List<Clientes> obtenerClientes(){
        return clienteService.findAll();
    }
    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> obtenerClientePorId(@PathVariable("id") Long id){

        Optional<Clientes> cliente = clienteService.findById(id);
        if(cliente.isPresent()){
            Map<String, Object> response = new HashMap<>();
            response.put("menssage", "Cliente Encontrado");
            response.put("cliente", cliente);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        Map<String, Object> response = new HashMap<>();
        response.put("menssage", "Cliente No Existe");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> crearClientes(@RequestBody Clientes cliente){

        Rol rol = rolService.findByNombre("ROLE_USER");
        if(rol!= null){
            cliente.setRol(rol);
            cliente.setContraseña(passwordEncoder.encode(cliente.getContraseña()));
            clienteService.save(cliente);
            Map<String, Object> response = new HashMap<>();
            response.put("menssage", "Cliente Creado");
            response.put("cliente", cliente);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PutMapping("{id}")
    public Object actualizarClientePorId(@PathVariable("id") Long id , @RequestBody Clientes clienteBody){

        Optional<Clientes> cliente = clienteService.findById(id);

        if(cliente.isPresent()){
            return cliente
                    .map(clienteGuardado -> {
                        clienteGuardado.setNombre(clienteBody.getNombre());
                        clienteGuardado.setApellido(clienteBody.getApellido());
                        clienteGuardado.setDir(clienteBody.getDir());
                        clienteGuardado.setEdad(clienteBody.getEdad());
                        Clientes clienteActualizado = clienteService.save(clienteGuardado);
                        return new ResponseEntity<>(clienteActualizado,HttpStatus.OK);
                    });


        }

        Map<String, Object> response = new HashMap<>();
        response.put("menssage", "El  Cliente No Se Existe");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("{id}")
    public Object eliminarClientePorId(@PathVariable("id") Long id ){

        Optional<Clientes> cliente = clienteService.findById(id);

        if(cliente.isPresent()){

            clienteService.DeleteById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("menssage", "Eliminado");
            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        Map<String, Object> response = new HashMap<>();
        response.put("menssage", "El  Cliente No Se Existe");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }








}
