package com.br.ufc.vev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.ufc.vev.model.Ator;
import com.br.ufc.vev.model.Diretor;
import com.br.ufc.vev.model.Filme;
import com.br.ufc.vev.model.Genero;
import com.br.ufc.vev.repository.AtorRepository;
import com.br.ufc.vev.repository.FilmeRepository;

public class FilmeService {
	
	@Autowired
	private FilmeRepository sRepository;
	
	@Autowired
	private AtorService aService;
	
	@Autowired
	private DiretorService dService;
	
	@Autowired
	private GeneroService gService;
	
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

	public List<Filme> buscarTodosFilmes() {
		// TODO Auto-generated method stub
		return sRepository.findAll();
	}

	public void vinculaAtorAoFilme(int idFilme, int idAtor) throws Exception {
		// TODO Auto-generated method stub
		Ator a = aService.buscarAtor(idAtor);
		Filme f = buscarFilmeId(idFilme);
		f.adicionarAtor(a);
		a.addFilme(f);
		aService.atualizarAtor(a);
		atualizarFilme(f);
		
	}

	public void desvinculaAtorDoFilme(int idFilme, int idAtor) throws Exception {
		Ator a = aService.buscarAtor(idAtor);
		Filme f = buscarFilmeId(idFilme);
		f.removerAtor(a);
		a.removerFilme(f);
		aService.atualizarAtor(a);
		atualizarFilme(f);
		
	}

	public void vinculaDiretorAoFilme(int idFilme, int idDir) throws Exception {
		// TODO Auto-generated method stub
		Diretor a = dService.buscarDiretor(idDir);
		Filme f = buscarFilmeId(idFilme);
		f.adicionarDiretor(a);
		a.addFilme(f);
		dService.atualizarDiretor(a);
		atualizarFilme(f);
	}
	
	public void desvinculaDiretorDoFilme(int idFilme, int idDir) throws Exception {
		// TODO Auto-generated method stub
		Diretor a = dService.buscarDiretor(idDir);
		Filme f = buscarFilmeId(idFilme);
		f.removerDiretor(a);
		a.removerFilme(f);
		dService.atualizarDiretor(a);
		atualizarFilme(f);
	}

	public void vinculaGeneroAoFilme(int idFilme, int idGen) throws Exception {
		// TODO Auto-generated method stub
		Genero a = gService.buscarGenero(idGen);
		Filme f = buscarFilmeId(idFilme);
		f.adicionarGenero(a);
		a.adicionarFilme(f);
		gService.atualizarGenero(a);
		atualizarFilme(f);
	}

	public void desvinculaGeneroDoFilme(int idFilme, int idGen) throws Exception {
		// TODO Auto-generated method stub
		Genero a = gService.buscarGenero(idGen);
		Filme f = buscarFilmeId(idFilme);
		f.removerGenero(a);
		a.removerFilme(f);
		gService.atualizarGenero(a);
		atualizarFilme(f);
	}

}
