package com.br.ufc.vev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Cinema {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Endereoço do cinema é uma informação obrigatória")
	private String endereco;
	
	@Column(nullable = false)
	@NotBlank(message = "Endereoço do cinema é uma informação obrigatória")
	private String cidade;
	
	@Column(nullable = false)
	@NotBlank(message = "Nome do cinema é uma informação obrigatória")
	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cinema")
	private List<Sala> salas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Sala> getSalas() {
		return salas;
	}
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	
	public boolean vincularSala(Sala sala) {
		if (salas == null)
			salas = new ArrayList<Sala>();
		return salas.add(sala);
	}
	
	public boolean desvincularSala(Sala sala) {
		if (salas == null)
			return false;
		return salas.remove(sala);
	}
	

}
