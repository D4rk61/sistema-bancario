package com.bank.sistemabancario.domain.service;

import com.bank.sistemabancario.persistance.entity.Cliente;
import com.bank.sistemabancario.persistance.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private final IClienteRepository clienteRepository;


    public ClienteService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }


    public Cliente findById(Long id) {

        if(existsById(id)) {
            return clienteRepository.findById(id).get();
        }
        throw new RuntimeException("El cliente no existe");
    }

    public Cliente findByName(String name) {
        // realiza una condicional para cuando el nombre no exista
        // si no existe, lanza una excepcion
        // si existe, devuelve el cliente

        try {

            if(clienteRepository.findByNombreIgnoreCase(name) == null) {
                throw new RuntimeException("El cliente no existe");

            } else {

                return clienteRepository.findByNombreIgnoreCase(name);
            }

        } catch (Exception e) {
            throw new RuntimeException("El cliente no existe");
        }

    }

    public List<Cliente> orderByName() {
        return clienteRepository.findAllByOrderByNombreAsc();
    }


    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public void deleteById(Long id) {
        if(existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("El cliente no existe");
        }
    }


    // cambia el retorno de tipo void a boolean
    public boolean existsById(Long id) {
        return clienteRepository.existsById(id);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        Cliente existingCliente = findById(id);
        existingCliente.setNombre(cliente.getNombre());
        existingCliente.setApellido(cliente.getApellido());
        existingCliente.setCorreo(cliente.getCorreo());
        existingCliente.setTelefono(cliente.getTelefono());
        existingCliente.setFechaNacimiento(cliente.getFechaNacimiento());
        existingCliente.setDireccion(cliente.getDireccion());
        return clienteRepository.save(existingCliente);
    }


    public Cliente findByCorreoAndDni(String correo, String dni) {
        return clienteRepository.findByCorreoAndDni(correo, dni);
    }

    public Cliente findByCorreo(String correo) {
        return clienteRepository.findByCorreo(correo);
    }

    public boolean validarCredenciales(String correo, String dni) {
        Cliente cliente = clienteRepository.findByCorreoAndDni(correo, dni);
        return cliente != null;
    }

}
