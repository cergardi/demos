package com.example.demo.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="USUARIOS")
@ApiModel( value = "Usuario", description = "Objeto que representa la entidad Usuario" )
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -6084191229378830383L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	@ApiModelProperty( value = "Identificador del usuario", required = true ) 
    private Long id;
 
    @NotEmpty
    @Column(name="nombre", nullable=false)
    @ApiModelProperty( value = "Nombre del usuario", required = true ) 
    private String nombre;
 
    @Column(name="APELLIDOS", nullable=false)
    @ApiModelProperty( value = "Apellidos del usuario", required = true )
    private String apellidos;
 
    @Column(name="EDAD", nullable=false)
    @ApiModelProperty( value = "Edad del usuario", required = true )
    private Integer edad;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	

}
