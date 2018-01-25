package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Usuario;
 
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
    Usuario findByNombre(String nombre);
 
}