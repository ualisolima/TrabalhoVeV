package com.br.ufc.vev.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.ufc.vev.model.Genero;
import com.br.ufc.vev.model.Sessao;
import com.br.ufc.vev.repository.SessaoRepository;

public class SessaoService {
	
	@Autowired
	private SessaoRepository repository;
	
	public Sessao salvarSessao(Sessao sessao) {
		return repository.save(sessao);
	}
	
	public Sessao atualizarSessao(Sessao sessao) {
		repository.save(sessao);
		return sessao;
	}
	
	public Sessao getSessaoPorId(Integer idSessao) {
		return repository.getOne(idSessao);
	}
	
	public List<Sessao> getTodasSessoes() {
		return repository.findAll();
	}
	
	public Sessao deletarSessao(Integer idSessao) {
		Sessao sessao = repository.getOne(idSessao);
		repository.delete(sessao);
		return sessao; 
	}
	
	public List<Sessao> getSessaoPorData(LocalDate dataInicial, LocalDate dataFinal) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			if (sessao.getDataInicio().isBefore(dataInicial) && sessao.getDataFim().isAfter(dataFinal)) {
				s.add(sessao);
			}
		}
		return s;
	}
	
	public List<Sessao> getSessaoPorCidade(String cidade) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			if (sessao.getSala().getCinema().getCidade().equals(cidade)) {
				s.add(sessao);
			}
		}
		return s;
	}
	
	public List<Sessao> getSessaoPorFilme(String filme) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			if (sessao.getFilme().getNome().equals(filme)) {
				s.add(sessao);
			}
		}
		return s;
	}
	
	public List<Sessao> getSessaoPorGenero(String genero) {
		List<Sessao> s = new ArrayList<Sessao>();
		for (Sessao sessao : getTodasSessoes()) {
			for (Genero g : sessao.getFilme().getGeneros()) {
				if (g.getDescricao().equals(genero)) {
					s.add(sessao);
				}
			}
		}
		return s;
	}

}
