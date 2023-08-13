package com.bank.sistemabancario.persistance.repository;

import com.bank.sistemabancario.persistance.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNombreIgnoreCase(String nombre);

    // genera un metodo con queryMethods que ordene los clientes por nombre ignore case
    List<Cliente> findAllByOrderByNombreAsc();

    // crea lo siguiente: findByCorreoAndDni
    Cliente findByCorreoAndDni(String correo, String dni);


    // crea lo siguiente: findByCorreo
    Cliente findByCorreo(String correo);
}
