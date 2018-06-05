package com.br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ufc.vev.model.Cinema;
import com.br.ufc.vev.model.Sala;
import com.br.ufc.vev.repository.CinemaRepository;

@Service
public class CinemaService {
	
	@Autowired
	private CinemaRepository sRepository;
	
	@Autowired
	private SalaService salaService;
	
	public Cinema addCinema(Cinema c) throws Exception {
		if (c.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (c.getNome() == null)
			throw new Exception("nome não pode ser nulo");
		if (c.getEndereco() == null)
			throw new Exception("endereço não pode ser nulo");
		if (c.getCidade() == null)
			throw new Exception("cidade não pode ser nulo");
		return sRepository.save(c);
	}
	
	public Cinema removerCinema(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Cinema c = sRepository.getOne(id);
		if (c == null)
			throw new Exception("cinema não encontrado");
		sRepository.delete(c);
		return c;
	}
	
	public Cinema atualizarCinema(Cinema c) throws Exception {
		if (c.getId() <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		if (c.getNome() == null)
			throw new Exception("nome não pode ser nulo");
		if (c.getEndereco() == null)
			throw new Exception("endereço não pode ser nulo");
		if (c.getCidade() == null)
			throw new Exception("cidade não pode ser nulo");
		return sRepository.save(c);
	}
	
	public Cinema buscarCinema(int id) throws Exception {
		if (id <= 0)
			throw new Exception("id não pode ser menor ou igual a 0");
		Cinema c = sRepository.getOne(id);
		if (c == null)
			throw new Exception("cinema não encontrado");
		return c;
	}
	
	public List<Cinema> buscarTodosOsCinemas() {
		return sRepository.findAll();
	}
	
	public boolean vincularSala(int idSala, int idCinema) throws Exception
	{
		Sala s = salaService.buscarSala(idSala);
		Cinema c = buscarCinema(idCinema);
		s.setCinema(c);
		c.vincularSala(s);
		salaService.atualizarSala(s);
		atualizarCinema(c);
		return true;
		
	}
	
	public boolean desvincularSala(int idSala, int idCinema) throws Exception
	{
		Sala s = salaService.buscarSala(idSala);
		Cinema c = buscarCinema(idCinema);
		if (!c.getSalas().contains(s))
			return false;
		s.setCinema(null);
		c.desvincularSala(s);
		salaService.atualizarSala(s);
		atualizarCinema(c);
		return true;
	}
}
