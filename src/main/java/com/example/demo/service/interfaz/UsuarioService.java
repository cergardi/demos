package com.example.demo.service.interfaz;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Usuario;


@Service
public interface UsuarioService{
		
	@Transactional
	Usuario findById(Long id);
		
	@Transactional
	Usuario findByName(String nombre);
	
	@Transactional
	void saveUsuario(Usuario usuario);
	
	@Transactional
	void updateUsuario(Usuario usuario);
		
	@Transactional
	void deleteUsuarioById(Long id);

	@Transactional
	List<Usuario> findAllUsuarios(); 
		
	@Transactional
	void deleteAllUsuarios();
		
	@Transactional
	public boolean isUsuarioExist(Usuario usuario);

}
