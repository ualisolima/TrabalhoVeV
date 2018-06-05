package com.br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.ufc.vev.model.Filme;
import com.br.ufc.vev.repository.FilmeRepository;

public class FilmeService {
	
	@Autowired
	private FilmeRepository sRepository;
	
	public Filme buscarFilmeId(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Filme f = sRepository.getOne(id);
		if (f == null)
			throw new Exception("filme não encontrado");
		return f;
	}
	
	public List<Filme> buscarFilmeNome(String nome) throws Exception {
		if (nome == null)
			throw new Exception("nome não pode ser nulo");
		List<Filme> f = sRepository.buscarFilmeNome(nome);
		if (f.size() == 0)
			throw new Exception("nenhum resultado encontrado");
		return f;
	}
	
	public Filme addFilme(Filme f) throws Exception {
		if (f.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (f.getNome() == null)
			throw new Exception("nome não pode ser nulo");
		if (f.getSinopse() == null)
			throw new Exception("sinopse não pode ser nulo");
		if (f.getDuracao() <= 0) 
			throw new Exception("duração não pode ser menor ou igual a 0");
		return sRepository.save(f);
	}
	public Filme atualizarFilme(Filme f) throws Exception {
		if (f.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (f.getNome() == null)
			throw new Exception("nome não pode ser nulo");
		if (f.getSinopse() == null)
			throw new Exception("sinopse não pode ser nulo");
		if (f.getDuracao() <= 0) 
			throw new Exception("duração não pode ser menor ou igual a 0");
		return sRepository.save(f);
	}
	
	public Filme removerFilme(int id) throws Exception{
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Filme f = sRepository.getOne(id);
		if (f == null)
			throw new Exception("filme não encontrado");
		sRepository.delete(f);
		return f;
	}

}
