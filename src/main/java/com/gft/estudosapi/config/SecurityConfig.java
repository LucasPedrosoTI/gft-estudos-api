package com.gft.estudosapi.config;

import com.gft.estudosapi.filters.JwtRequestFilter;
import com.gft.estudosapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UsuarioService usuarioService;

  @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //@formatter:off
    http.csrf().disable()
            .authorizeRequests().antMatchers("/autenticar").permitAll()
              .antMatchers("/cadastrar").permitAll()
              .anyRequest().authenticated()
              .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    //@formatter:on
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
