package com.br.ufc.vev.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.br.ufc.vev.Enums.TipoSala;

@Entity
public class Sala {
	
	public Sala() {super();}
	
	public Sala(TipoSala tipo, String nome, int capacidade, Cinema cinema) {
		super();
		this.tipo = tipo;
		this.nome = nome;
		this.capacidade = capacidade;
		this.cinema = cinema;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	@NotBlank(message = "Tipo da sala é uma informação obrigatória")
	@Enumerated(EnumType.STRING)
	private TipoSala tipo;
	
	@Column(nullable = false)
	@NotBlank(message = "Nome da sala é uma informação obrigatória")
	private String nome;
	
	@Column(nullable = false)
	@NotBlank(message = "Capacidade da sala é uma informação obrigatória")
	private int capacidade;
	
	@ManyToOne
	@JoinColumn(name = "id_cinema")
	private Cinema cinema;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
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
	public int getCapacidade() {
		return capacidade;
	}
	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
	public TipoSala getTipo() {
		return tipo;
	}
	public void setTipo(TipoSala tipo) {
		this.tipo = tipo;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public List<Sessao> getSessoes() {
		return sessoes;
	}
	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getName().equals(this.getClass().getName()))
			return false;
		Sala other = (Sala) obj;
		return other.getId() == getId() &&
				other.getCapacidade() == getCapacidade() &&
				other.getCinema().equals(getCinema()) &&
				other.getNome().equals(getNome()) &&
				other.getTipo().equals(getTipo());
	}
	
	
	

}
