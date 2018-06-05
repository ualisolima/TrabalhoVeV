package com.br.ufc.vev.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Sessao {
	
	public Sessao() {super();}
	
	public Sessao(Filme filme, Sala sala, LocalTime horario, LocalDate dataInicio, LocalDate dataFim) {
		
		super();
		this.filme = filme;
		this.sala = sala;
		this.horario = horario;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "id_filme")
	private Filme filme;
	
	@ManyToOne
	@JoinColumn(name = "id_sala")
	private Sala sala;
	
	@Column(nullable = false)
	@NotBlank(message = "Horario da sessão é uma informação obrigatória")
	private LocalTime horario;
	
	@Column(nullable = false)
	@NotBlank(message = "Início da sessão é uma informação obrigatória")
	private LocalDate dataInicio;
	
	@Column(nullable = false)
	@NotBlank(message = "Fim da sessão é uma informação obrigatória")
	private LocalDate dataFim;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public Filme getFilme() {
		return filme;
	}
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getName().equals(this.getClass().getName()))
			return false;
		Sessao other = (Sessao) obj;
		return other.getDataFim().equals(getDataFim()) &&
				other.getDataInicio().equals(getDataInicio()) &&
				other.getFilme().equals(getFilme()) &&
				other.getHorario().equals(getHorario()) &&
				other.getId() == getId() &&
				other.getSala().equals(getSala());
	}

}
