package com.br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.ufc.vev.model.Sala;
import com.br.ufc.vev.repository.SalaRepository;

public class SalaService {
	
	@Autowired
	private SalaRepository sRepository;
	
	public Sala addSala(Sala s) throws Exception {
		if (s.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (s.getCapacidade() <= 0)
			throw new Exception("capacidade não pode ser menor ou igual a 0");
		if(s.getNome() == null)
			throw new Exception("nome não pode ser nulo");
		if(s.getTipo() == null)
			throw new Exception("tipo da sala não pode ser nulo");
		return sRepository.save(s);
	}
	
	public Sala removerSala(int id) throws Exception{
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Sala s = sRepository.getOne(id);
		if (s == null)
			throw new Exception("sala não encontrada");
		sRepository.delete(s);
		return s;
	}
	
	public Sala atualizarSala(Sala s) throws Exception {
		if (s.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (s.getCapacidade() <= 0)
			throw new Exception("capacidade não pode ser menor ou igual a 0");
		if(s.getNome() == null)
			throw new Exception("nome não pode ser nulo");
		if(s.getTipo() == null)
			throw new Exception("tipo da sala não pode ser nulo");
		return sRepository.save(s);
	}
	
	public Sala buscarSala(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Sala s = sRepository.getOne(id);
		if (s == null)
			throw new Exception("sala não encontrada");
		return s;
	}
	
	public List<Sala> buscarTodasAsSalas() {
		return sRepository.findAll();
	}
	
	

}
