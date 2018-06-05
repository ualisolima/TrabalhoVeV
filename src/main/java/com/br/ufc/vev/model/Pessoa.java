package com.br.ufc.vev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Pessoa {
	
	public Pessoa() {
		super();
	}
	
	public Pessoa(String nome, String sobre) {
		super();
		this.nome = nome;
		this.sobre = sobre;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "Nome da Pessoa é uma informação obrigatória")
	private String nome;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "Nome da Pessoa é uma informação obrigatória")
	private String sobre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobre() {
		return sobre;
	}
	public void setSobre(String sobre) {
		this.sobre = sobre;
	}
	
	@Override
	public boolean equals(Object obj) {
		Pessoa other = (Pessoa) obj;
		return other.getId() == this.getId() &&
				other.getNome().equals(this.getNome()) &&
				other.getSobre().equals(this.getSobre());
	}

}
