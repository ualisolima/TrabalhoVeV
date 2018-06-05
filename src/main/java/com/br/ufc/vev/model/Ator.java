package com.br.ufc.vev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Ator extends Pessoa{
	
	public Ator() {
		super();
	}
	
	public Ator(String nome, String sobre) {
		super(nome, sobre);
	}
	
	@ManyToMany(mappedBy = "atores")
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
