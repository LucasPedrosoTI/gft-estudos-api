package com.gft.estudosapi.resource;

import com.gft.estudosapi.model.Usuario;
import com.gft.estudosapi.service.UsuarioService;
import io.swagger.annotations.Api;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Cadastro")
@RestController
public class AuthResource {

    @Autowired
    UsuarioService usuarioService;

    // @PostMapping("/autenticar")
    // public AuthenticationResponse autenticar(@RequestBody @Valid Usuario usuario)
    // {
    // return usuarioService.autenticar(usuario);
    // }
    //
    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestBody @Valid Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }

    @GetMapping("/")
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }

}
