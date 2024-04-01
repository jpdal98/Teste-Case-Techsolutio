package com.techsolutio.domain.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.techsolutio.domain.User;
import com.techsolutio.domain.enums.Perfil;


public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Campo obrigatório!!")
	@Email(message = "Favor entrar com um e-mail válido!!")
	private String email;
	
	@NotBlank(message = "Campo obrigatório!!")
	private String senha;
	
	private Set<Integer> perfis = new HashSet<>();

	public UserDTO() {
		super();
		addPerfil(Perfil.ADMIN);
	}

	public UserDTO(Long id, String email, String senha) {
		this.id = id;
		this.email = email;
		this.senha = senha;

	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.senha = user.getSenha();
		this.perfis = user.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}
}
