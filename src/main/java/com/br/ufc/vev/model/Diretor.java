package com.br.ufc.vev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Diretor extends Pessoa{
	
	public Diretor() {
		super();
	}
	
	public Diretor(String nome, String sobre) {
		super(nome, sobre);
	}
	
	@ManyToMany(mappedBy = "diretores")
	private List<Filme> filmes;

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
	public boolean addFilme(Filme filme) {
		if (filmes == null)
			filmes = new ArrayList<Filme>();
		return filmes.add(filme);
	}
	
	public boolean removerFilme(Filme filme) {
		if (filmes == null)
			return false;
		return filmes.remove(filme);
	}
	

}
