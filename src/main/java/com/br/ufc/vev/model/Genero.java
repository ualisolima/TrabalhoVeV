package com.br.ufc.vev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Genero {
	
	public Genero() {
		super();
	}
	
	public Genero(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Nome do genero é uma informação obrigatória")
	private String descricao;
	
	@ManyToMany(mappedBy = "generos")
	private List<Filme> filmes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
	public boolean adicionarFilme(Filme filme) {
		if (filmes == null)
			filmes = new ArrayList<Filme>();
		return filmes.add(filme);
	}
	
	public boolean removerFilme(Filme filme) {
		if (filmes == null)
			return false;
		return filmes.remove(filme);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getName().equals(this.getClass().getName()))
			return false;
		Genero other = (Genero) obj;
		return other.getId() == getId() &&
				other.getDescricao().equals(other.getDescricao());
	}
	
	

}
