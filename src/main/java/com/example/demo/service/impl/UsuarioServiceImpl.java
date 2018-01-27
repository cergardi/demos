package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.service.interfaz.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
		 
    @Autowired
    private UsuarioRepository usuarioRepository;
 
    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findOne(id);
    }
 
    @Override
    public Usuario findByName(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }
 
    @Override
    public void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
 
    @Override
    public void updateUsuario(Usuario usuario){
        saveUsuario(usuario);
    }
 
    @Override
    public void deleteUsuarioById(Long id){
        usuarioRepository.delete(id);
    }
 
    @Override
    public void deleteAllUsuarios(){
        usuarioRepository.deleteAll();
    }
 
    @Override
    public List<Usuario> findAllUsuarios(){
        return usuarioRepository.findAll();
    }
 
    @Override
    public boolean isUsuarioExist(Usuario usuario) {
        return findByName(usuario.getNombre()) != null;
    }
}
