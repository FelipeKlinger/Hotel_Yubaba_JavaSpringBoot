package com.hotel.hotel.service;



import java.util.List;



import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.*;

import org.springframework.stereotype.Service;



import com.hotel.hotel.model.*;

import com.hotel.hotel.repository.UsuarisRepository;



@Service

public class CustomUserDetailsService implements UserDetailsService {

 private final UsuarisRepository usuariRepository;



 public CustomUserDetailsService(UsuarisRepository usuariRepository) {

 this.usuariRepository = usuariRepository;

 }



 @Override

 public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

 Usuari usuari = usuariRepository.findByEmail(email)


 .orElseThrow(() -> new UsernameNotFoundException("Usuari no trobat"));



 return new org.springframework.security.core.userdetails.User(

 usuari.getEmail(),

 usuari.getPassword(),

 usuari.isActiu(),

 true, true, true,

 List.of(new SimpleGrantedAuthority(usuari.getRol()))

 );

 }

}