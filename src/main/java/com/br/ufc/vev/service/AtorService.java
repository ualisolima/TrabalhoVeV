package com.br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ufc.vev.model.Ator;
import com.br.ufc.vev.repository.AtorRepository;


@Service
public class AtorService {
	
	@Autowired
	AtorRepository sRepository;
	
	public List<Ator> buscarTodosAtores() {
		return sRepository.findAll();
	}
	
	public Ator addAtor(Ator a) throws Exception {
		if (a.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (a.getSobre() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(a);
	}
	
	public Ator buscarAtor(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Ator a = sRepository.getOne(id);
		if( a == null)
			throw new Exception("genero não encontrado");
		return a;
	}
	
	public Ator removerAtor(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Ator a = sRepository.getOne(id);
		if( a == null)
			throw new Exception("genero não encontrado");
		sRepository.delete(a);
		return a;
	}
	
	public Ator atualizarAtor(Ator a) throws Exception {
		if (a.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (a.getSobre() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(a);
	}

}
