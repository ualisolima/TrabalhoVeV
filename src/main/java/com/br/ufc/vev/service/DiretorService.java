package com.br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.ufc.vev.model.Diretor;
import com.br.ufc.vev.repository.DiretorRepository;

public class DiretorService {
	
	@Autowired
	DiretorRepository sRepository;
	
	public Diretor addDiretor(Diretor d) throws Exception {
		if (d.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual d 0");
		if (d.getSobre() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(d);
	}
	
	public Diretor buscarDiretor(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual d 0");
		Diretor d = sRepository.getOne(id);
		if( d == null)
			throw new Exception("genero não encontrado");
		return d;
	}
	
	public Diretor removerDiretor(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual d 0");
		Diretor d = sRepository.getOne(id);
		if( d == null)
			throw new Exception("genero não encontrado");
		sRepository.delete(d);
		return d;
	}
	
	public Diretor atualizarDiretor(Diretor d) throws Exception {
		if (d.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual d 0");
		if (d.getSobre() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(d);
	}

	public List<Diretor> buscarTodosDiretores() {
		
		return sRepository.findAll();
	}

}
