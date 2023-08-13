package com.bank.sistemabancario.web.controller.GUI;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

@Getter
@Setter
public class LoginForm {

    private String correo;
    private String dni;
}
