package com.br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ufc.vev.model.Genero;
import com.br.ufc.vev.repository.GeneroRepository;

@Service
public class GeneroService {
	
	@Autowired
	GeneroRepository sRepository;
	
	public Genero addGenero(Genero g) throws Exception {
		if (g.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (g.getDescricao() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(g);
	}
	
	public Genero removerGenero(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Genero g = sRepository.getOne(id);
		if( g == null)
			throw new Exception("genero não encontrado");
		sRepository.delete(g);
		return g;
	}
	
	public Genero atualizarGenero(Genero g) throws Exception {
		if (g.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (g.getDescricao() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(g);
	}
	
	public List<Genero> getAllGeneros() {
		return sRepository.findAll();
	}

}
