package com.bank.sistemabancario.web.controller;

import com.bank.sistemabancario.domain.service.CuentaService;
import com.bank.sistemabancario.persistance.entity.Cliente;
import com.bank.sistemabancario.persistance.entity.Cuenta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-cuentas")
public class CuentaController {

    @Autowired
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    @Transactional
    public ResponseEntity<List<Cuenta>> findAll() {
        return ResponseEntity.ok(cuentaService.findAll());
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<Cuenta> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.findById(id));
    }

    @GetMapping("/name/{name}")
    @Transactional
    public ResponseEntity<Cuenta> findByNumeroDeCuenta(@PathVariable String name) {
        return ResponseEntity.ok(cuentaService.findByNumeroCuenta(name));
    }

    @PostMapping
    public ResponseEntity<Cuenta> save(@RequestBody Cuenta cuenta) {
        try {

            return ResponseEntity.ok(cuentaService.save(cuenta));
        } catch (Exception e) {

            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateById(@PathVariable Long id, @RequestBody Cuenta cuenta) {
        try {

            Cuenta updatedCUenta = cuentaService.updateCuenta(id, cuenta);
            return ResponseEntity.ok(updatedCUenta);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cuenta> deleteById(@PathVariable Long id) {
        try {
            cuentaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
