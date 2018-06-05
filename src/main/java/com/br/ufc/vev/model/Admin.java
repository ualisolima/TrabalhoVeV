package com.br.ufc.vev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Admin{
	
	public Admin() {
		super();
	}
	
	public Admin(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	@Id
	private String login;
	
	@Column(nullable = false, length = 20)
	@NotBlank(message = "Senha é uma informação  obrigatória")
	private String senha;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getName().equals(this.getClass().getName()))
			return false;
		Admin other = (Admin) obj;
		return other.getLogin().equals(getLogin()) &&
				other.getSenha().equals(getSenha());
	}
	
	

}
