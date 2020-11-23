package com.gft.estudosapi.resource;

import javax.validation.Valid;

import com.gft.estudosapi.model.Usuario;
import com.gft.estudosapi.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
