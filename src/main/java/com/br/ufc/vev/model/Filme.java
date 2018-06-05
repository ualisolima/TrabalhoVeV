package com.br.ufc.vev.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Filme {
	
	public Filme() {
		super();
	}
	
	public Filme(String nome, String sinopse, int duracao, Genero genero) {
		super();
		this.nome = nome;
		this.sinopse = sinopse;
		this.duracao = duracao;
		this.adicionarGenero(genero);
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Nome do filme é uma informação obrigatória")
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "filme_tem_atores",
			joinColumns = {@JoinColumn(name = "id_filme") },
			inverseJoinColumns = { @JoinColumn(name = "id_ator") })
	private List<Ator> atores;
	
	@ManyToMany
	@JoinTable(name = "filme_tem_diretores",
			joinColumns = {@JoinColumn(name = "id_filme") },
			inverseJoinColumns = { @JoinColumn(name = "id_diretor") })
	private List<Diretor> diretores;
	
	@ManyToMany
	@JoinTable(name = "filme_tem_generos",
			joinColumns = {@JoinColumn(name = "id_filme") },
			inverseJoinColumns = { @JoinColumn(name = "id_genero") })
	private List<Genero> generos;
	
	@Column(nullable = false)
	@NotBlank(message = "Sinopse do filme é uma informação obrigatória")
	private String sinopse;
	//duração em minutos
	@Column(nullable = false)
	@NotBlank(message = "Duração do filme é uma informação obrigatória")
	private int duracao;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "filme")
	private List<Sessao> sessoes;
	
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
	public List<Ator> getAtores() {
		return atores;
	}
	public void setAtores(List<Ator> atores) {
		this.atores = atores;
	}
	public List<Diretor> getDiretores() {
		return diretores;
	}
	public void setDiretores(List<Diretor> diretores) {
		this.diretores = diretores;
	}
	public List<Genero> getGeneros() {
		return generos;
	}
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public int getDuracao() {
		return duracao;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	public boolean adicionarAtor(Ator ator) {
		if (atores == null)
			atores = new ArrayList<Ator>();
		return atores.add(ator);
	}
	
	public boolean removerAtor(Ator ator) {
		if (atores == null)
			return false;
		return atores.remove(ator);
	}
	
	public boolean adicionarDiretor(Diretor diretor) {
		if (diretores == null)
			diretores = new ArrayList<Diretor>();
		return diretores.add(diretor);
	}
	
	public boolean removerDiretor(Diretor diretor) {
		if (diretores == null)
			return false;
		return diretores.remove(diretor);
	}
	
	public boolean adicionarGenero(Genero genero) {
		if (generos == null)
			generos = new ArrayList<Genero>();
		return generos.add(genero);
	}
	
	public boolean removerGenero(Genero genero) {
		if (generos == null)
			return false;
		return generos.remove(genero);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getName().equals(this.getClass().getName()))
			return false;
		Filme other = (Filme) obj;
		return other.getId() == getId() &&
				other.getNome().equals(getNome()) &&
				other.getDuracao() == getDuracao() &&
				other.getSinopse().equals(getSinopse());
	}
	
	

}
