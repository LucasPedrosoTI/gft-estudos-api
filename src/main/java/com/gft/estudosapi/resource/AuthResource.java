package com.gft.estudosapi.resource;

import com.gft.estudosapi.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthResource {

  @Autowired
  UsuarioService usuarioService;

//    @PostMapping("/autenticar")
//  public AuthenticationResponse autenticar(@RequestBody @Valid Usuario usuario) {
//    return usuarioService.autenticar(usuario);
//    }
//
//    @PostMapping("/cadastrar")
//    public AuthenticationResponse cadastrar(@RequestBody @Valid Usuario usuario) {
//        return usuarioService.cadastrar(usuario);
//    }

}
