package com.gft.estudosapi.service;

import com.gft.estudosapi.model.AuthenticationResponse;
import com.gft.estudosapi.model.Usuario;
import com.gft.estudosapi.repository.Usuarios;
import com.gft.estudosapi.utils.JwtUtil;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

  @Autowired
  Usuarios usuarios;

  @Autowired
  AuthenticationManager authManager;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Usuario usuario = Optional.ofNullable(usuarios.findByEmail(email))
        .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

    List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
    List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

    return new User(usuario.getEmail(), usuario.getSenha(), usuario.isAdmin() ? authorityListAdmin : authorityListUser);
  }

  public AuthenticationResponse autenticar(Usuario usuario) {
    authManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));

    final UserDetails userDetails = loadUserByUsername(usuario.getEmail());

    final String jwt = jwtUtil.generateToken(userDetails);

    return new AuthenticationResponse(jwt);
  }

}
