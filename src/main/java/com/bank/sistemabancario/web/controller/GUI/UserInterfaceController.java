package com.bank.sistemabancario.web.controller.GUI;

import com.bank.sistemabancario.domain.service.ClienteService;
import com.bank.sistemabancario.domain.service.CuentaService;
import com.bank.sistemabancario.persistance.entity.Cliente;
import com.bank.sistemabancario.persistance.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserInterfaceController {

    @Autowired
    private final ClienteService clienteService;
    @Autowired
    private final CuentaService cuentaService;

    public UserInterfaceController(ClienteService clienteService, CuentaService cuentaService) {
        this.clienteService = clienteService;
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public String login(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginForm loginForm, Model model) {
        // Aquí verifica las credenciales en la base de datos
        if (clienteService.validarCredenciales(loginForm.getCorreo(), loginForm.getDni())) {
            // Obtiene el cliente y las cuentas usando los valores de loginForm
            Cliente cliente = clienteService.findByCorreoAndDni(loginForm.getCorreo(), loginForm.getDni());
            List<Cuenta> cuentas = cuentaService.findByCliente(cliente);

            model.addAttribute("cliente", cliente);
            model.addAttribute("cuentas", cuentas);

            return "home"; // Redirige a la página de inicio si las credenciales son correctas
        } else {
            // Aquí maneja el caso de credenciales incorrectas, por ejemplo, mostrar un mensaje de error
            return "login"; // Vuelve a la página de inicio de sesión con un mensaje de error
        }
    }


    @GetMapping("/home")
    public String home(Model model, @RequestParam("correo") String correo, @RequestParam("dni") String dni) {
        Cliente cliente = clienteService.findByCorreoAndDni(correo, dni);
        List<Cuenta> cuentas = cuentaService.findByCliente(cliente);

        model.addAttribute("cliente", cliente);
        model.addAttribute("cuentas", cuentas);
        return "home";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute Cliente cliente) {
        // Asegurémonos de que el campo id no se establezca manualmente
        cliente.setId(null);

        Cliente nuevoCliente = clienteService.save(cliente); // Guarda el cliente en la base de datos
        // Puedes realizar otras acciones aquí, como guardar cuentas asociadas al cliente, etc.

        return "redirect:/home?correo=" + nuevoCliente.getCorreo() + "&dni=" + nuevoCliente.getDni();
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Cliente cliente = new Cliente(); // Crea un objeto cliente vacío o con valores iniciales
        model.addAttribute("cliente", cliente);
        return "create"; // Nombre de la vista Thymeleaf
    }
}
