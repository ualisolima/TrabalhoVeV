package com.br.ufc.vev.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.br.ufc.vev.model.Pessoa;
import com.br.ufc.vev.repository.PessoaRepository;

public class PessoaService {
	
	@Autowired
	PessoaRepository sRepository;
	
	public Pessoa addPessoa(Pessoa p) throws Exception {
		if (p.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual p 0");
		if (p.getSobre() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(p);
	}
	
	public Pessoa buscarPessoa(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual p 0");
		Pessoa p = sRepository.getOne(id);
		if( p == null)
			throw new Exception("genero não encontrado");
		return p;
	}
	
	public Pessoa removerPessoa(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual p 0");
		Pessoa p = sRepository.getOne(id);
		if( p == null)
			throw new Exception("genero não encontrado");
		sRepository.delete(p);
		return p;
	}
	
	public Pessoa atualizarPessoa(Pessoa p) throws Exception {
		if (p.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual p 0");
		if (p.getSobre() == null)
			throw new Exception("descricao não pode ser nulo");
		return sRepository.save(p);
	}

}
