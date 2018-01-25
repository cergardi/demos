package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation(value = "listAllUsers", nickname = "listAllUsers", response = ResponseEntity.class)
	@ApiResponse(code = 200, message = "Petición Correcta", response = ResponseEntity.class)
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listAllUsers() {
        List<Usuario> listaUsuarios = usuarioService.findAllUsuarios();
        if (listaUsuarios.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
    }
 
    // Obtener Usuario por identificador
    @ApiOperation( value = "getUsuario", nickname = "getUsuario", response = ResponseEntity.class)
	@ApiResponse(code = 200, message = "Petición Correcta", response = List.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsuario(@PathVariable("id") long id) {
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
    @ApiOperation(value = "createUsuario", nickname = "createUsuario", response = ResponseEntity.class)
	@ApiResponse(code = 200, message = "Petición Correcta", response = ResponseEntity.class)
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario, UriComponentsBuilder ucBuilder) {
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
    @ApiOperation(value = "updateUsuario", nickname = "updateUsuario", response = ResponseEntity.class)
	@ApiResponse(code = 200, message = "Usuario Actualizado", response = ResponseEntity.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
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
    @ApiOperation(value = "deleteUsuario", nickname = "deleteUsuario", response = ResponseEntity.class)
	@ApiResponse(code = 200, message = "Usuario borrado", response = ResponseEntity.class)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") long id) {
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
 
    // Borrar todos los usuarios
    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
    public ResponseEntity<Usuario> deleteAllUsers() {
        logger.info("Borrar todos los usuarios");
 
        usuarioService.deleteAllUsuarios();
        return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
    }
 
}
