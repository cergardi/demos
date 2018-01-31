package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.Usuario;
import com.example.demo.service.interfaz.UsuarioService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
public class OrdersController {
 
    public static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
 
    @Autowired
    UsuarioService usuarioService;
 
    // Listado Usuarios
    @ApiOperation(value = "Busca todos los usuarios", nickname = "listAllUsers", response = List.class)
	@ApiResponse(code = 200, message = "Petición Correcta", response = ResponseEntity.class)
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<List<Usuario>> listAllUsers() {
        List<Usuario> listaUsuarios = usuarioService.findAllUsuarios();
        if (listaUsuarios.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
    }
 
    // Obtener Usuario por identificador
    @ApiOperation( value = "Obtiene un usuario filtrado por un identificador", nickname = "getUsuario", response = Usuario.class)
	@ApiResponse(code = 200, message = "Petición Correcta", response = List.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("id") long id) {
        logger.info("Buscando usuario con id {}", id);
        Usuario usuarios = usuarioService.findById(id);
        if (usuarios == null) {
            logger.error("El usuario con id {} no existe.", id);
            return new ResponseEntity(new CustomErrorType("El usuario con id " + id 
                    + " no existe"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuarios, HttpStatus.OK);
    }
 
    // Crear usuario
    @ApiOperation(value = "Crea un usuario", nickname = "createUsuario", response = String.class)
	@ApiResponse(code = 200, message = "Petición Correcta", response = String.class)
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<String> createUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
        logger.info("Creando usuario : {}", usuario);
 
	        if (usuarioService.isUsuarioExist(usuario)) {
	            logger.error("No se ha podido crear el usuario {}, ya existe", usuario.getNombre());
	            return new ResponseEntity(new CustomErrorType("No se ha podido crear el usuario {} " + 
	            	usuario.getNombre() + ", ya existe."),HttpStatus.CONFLICT);
	        }
	        usuarioService.saveUsuario(usuario);
 
        HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(usuario.getId()).toUri());
	    
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
 
    // Actualizar usuario
    @ApiOperation(value = "Actualiza un usuario", nickname = "updateUsuario", response = Usuario.class)
	@ApiResponse(code = 200, message = "Usuario Actualizado", response = Usuario.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> updateUsuario(@PathVariable("id") long id, @RequestBody Usuario usuario) {
        logger.info("Actualizando usuario con id {}", id);
 
        Usuario usuarioActual = usuarioService.findById(id);
 
        if (usuarioActual == null) {
            logger.error("El usuario {} no existe.", id);
            return new ResponseEntity(new CustomErrorType("El usuario " + id + " no existe."),
                    HttpStatus.NOT_FOUND);
        }
 
        usuarioActual.setNombre(usuario.getNombre());
        usuarioActual.setApellidos(usuario.getApellidos());
        usuarioActual.setEdad(usuario.getEdad()); 
        
        usuarioService.updateUsuario(usuarioActual);
        
        return new ResponseEntity<Usuario>(usuarioActual, HttpStatus.OK);
    }
 
    // Borrar usuario
    @ApiOperation(value = "Borra un usuario", nickname = "deleteUsuario", response = Usuario.class)
	@ApiResponse(code = 200, message = "Usuario borrado", response = ResponseEntity.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("id") long id) {
        logger.info("Borrar usuario con id {}", id);
 
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            logger.error("No se ha podido borrar el usuario con id {} , no existe.", id);
            return new ResponseEntity(new CustomErrorType("No se ha podido borrar el usuario con id " + id + ", no existe."),
                    HttpStatus.NOT_FOUND);
        }
        usuarioService.deleteUsuarioById(id);
        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }
 
 
}
