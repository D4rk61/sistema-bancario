package com.bank.sistemabancario.domain.service;

import com.bank.sistemabancario.persistance.entity.Cliente;
import com.bank.sistemabancario.persistance.entity.Cuenta;
import com.bank.sistemabancario.persistance.repository.ICuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private final ICuentaRepository cuentaRepository;

    public CuentaService(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }


    public List<Cuenta> findAll() {
        return cuentaRepository.findAll();
    }


    public Cuenta findById(Long id) {
        if(existsById(id)) {
            return cuentaRepository.findById(id).get();
        } else {
            throw new RuntimeException("La cuenta no existe");
        }

    }



    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void deleteById(Long id) {
        if(existsById(id)) {
            cuentaRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("La cuenta no existe");
        }
    }


    public boolean existsById(Long id) {
        return cuentaRepository.existsById(id);
    }


    public Cuenta updateCuenta(Long id, Cuenta cuenta) {
        Cuenta existingCuenta = findById(id);
        existingCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
        existingCuenta.setTipoDeCuenta(cuenta.getTipoDeCuenta());
        existingCuenta.setSaldo(cuenta.getSaldo());
        existingCuenta.setCliente(cuenta.getCliente());
        return cuentaRepository.save(existingCuenta);
    }


    public Cuenta findByNumeroCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuentaIgnoreCase(numeroCuenta);
    }

    public List<Cuenta> findByCliente(Cliente cliente) {
        return cuentaRepository.findByCliente(cliente);
    }
}
