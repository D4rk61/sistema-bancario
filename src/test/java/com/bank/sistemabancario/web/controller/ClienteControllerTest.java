package com.bank.sistemabancario.web.controller;

import com.bank.sistemabancario.domain.service.ClienteService;
import com.bank.sistemabancario.persistance.entity.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Cliente Controller Test")
class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    @DisplayName("Should return bad request when the id does not exist")
    void deleteClienteWhenIdDoesNotExistThenReturnBadRequest() {
        Long id = 1L;
        doThrow(new Exception()).when(clienteService).deleteById(id);

        ResponseEntity<Cliente> response = clienteController.deleteCliente(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(clienteService, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should throw an exception when the provided id does not exist")
    void deleteClienteWhenIdDoesNotExistThenThrowException() {
        Long id = 1L;
        doThrow(new RuntimeException("Cliente not found")).when(clienteService).deleteById(id);

        ResponseEntity<Cliente> response = clienteController.deleteCliente(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
        verify(clienteService, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should return bad request when the id is null")
    void deleteClienteWhenIdIsNullThenReturnBadRequest() {
        Long id = null;

        ResponseEntity<Cliente> response = clienteController.deleteCliente(id);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(clienteService, never()).deleteById(anyLong());
    }
}