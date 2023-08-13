package com.bank.sistemabancario.persistance.repository;

import com.bank.sistemabancario.persistance.entity.Cliente;
import com.bank.sistemabancario.persistance.entity.Cuenta;
import com.bank.sistemabancario.web.controller.CuentaController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {


    Cuenta findByNumeroCuentaIgnoreCase(String numeroCuenta);

    List<Cuenta> findByCliente(Cliente cliente);
}
