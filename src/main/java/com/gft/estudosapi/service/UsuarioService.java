package com.gft.estudosapi.service;

import com.gft.estudosapi.exceptionHandler.CustomExceptionHandler;
import com.gft.estudosapi.model.Usuario;
import com.gft.estudosapi.repository.Usuarios;
//import com.gft.estudosapi.utils.JwtUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    Usuarios usuarios;

    @Autowired
    @Lazy
    AuthenticationManager authManager;

    // @Autowired
    // private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = Optional.ofNullable(usuarios.findByEmail(email))
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

        return new User(usuario.getEmail(), usuario.getSenha(),
                usuario.isAdmin() ? authorityListAdmin : authorityListUser);
    }

    // public AuthenticationResponse autenticar(Usuario usuario) {
    // authManager.authenticate(new
    // UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
    //
    // final UserDetails userDetails = loadUserByUsername(usuario.getEmail());
    //
    // final String jwt = jwtUtil.generateToken(userDetails);
    //
    // return new AuthenticationResponse(jwt);
    // }

    public Usuario cadastrar(Usuario usuario) {

        if (usuarios.findByEmail(usuario.getEmail()) != null) {
            throw new DuplicateKeyException("E-mail já existente");
        }

        return usuarios.save(
                new Usuario(null, usuario.getEmail(), passwordEncoder.encode(usuario.getSenha()), usuario.isAdmin()));
    }

    @ExceptionHandler({ DuplicateKeyException.class })
    public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException ex) {
        String mensagemUsuario = ex.getMessage();
        String mensagemDev = ex.toString();

        List<CustomExceptionHandler.Erro> erros = Arrays
                .asList(new CustomExceptionHandler.Erro(mensagemUsuario, mensagemDev));

        return ResponseEntity.badRequest().body(erros);
    }

}
