package br.ufc.vev.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;

import br.ufc.vev.bean.Ator;
import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Genero;
import br.ufc.vev.service.FilmeService;

@Controller
@Transactional
@Rollback(false)
public class FilmeController {
	
	@Autowired
	FilmeService service;
	@Autowired
	AtorController atorController;
	@Autowired
	DiretorController diretorController;
	@Autowired
	GeneroController generoController;
	
	
	public Filme salvaFilme(String nome, String sinopse, int duracao) {
		try {
			if (this.validaFilme(nome, sinopse, duracao)) {
				Filme filme = new Filme();
				filme.setNome(nome);
				filme.setSinopse(sinopse);
				filme.setDuracao(duracao);
				return service.salvarFilme(filme);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Filme buscaFilme(int id) {
		try {
			if (validaId(id) && existsByIdFilme(id)) {
				return service.buscarFilme(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean excluiFilme(int id) {
		try {
			if (validaId(id) && existsByIdFilme(id)) {
				service.excluirFilme(service.buscarFilme(id));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Filme> getAllFilme() {		
		return service.getAllFilme();
	}

	public Filme atualizaFilme(Filme filme) {
		try {
			if (validaFilme(filme.getNome(), filme.getSinopse(), filme.getDuracao()) &&
					validaId(filme.getId()) && existsByIdFilme(filme.getId())) {
				return service.atualizaFilme(filme);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean vinculaAtorAoFilme(int idFilme, int idAtor) {
		if ((existsByIdFilme(idFilme) && atorController.existsByIdAtor(idAtor)) 
				&& atorPertenceAoFilme(idFilme, idAtor) != true) {
			service.vinculaAtorAoFilme(idFilme, idAtor);
			return true;
		}
		return false;
	}
	
	public boolean desvinculaAtorDoFilme(int idFilme, int idAtor) {
		if (atorPertenceAoFilme(idFilme, idAtor)) {
			service.desvinculaAtorDoFilme(idFilme, idAtor);
			return true;
		}
		return false;
	}
	
	public boolean vinculaDiretorAoFilme(int idFilme, int idDir) {
		if (existsByIdFilme(idFilme) && diretorController.existsByIdDiretor(idDir)
				&& diretorPertenceAoFilme(idFilme, idDir) != true) {
			service.vinculaDiretorAoFilme(idFilme, idDir);
			return true;
		}
		return false;
	}
	
	public boolean desvinculaDiretorDoFilme(int idFilme, int idDir) {
		if (diretorPertenceAoFilme(idFilme, idDir)) {
			service.desvinculaDiretorDoFilme(idFilme, idDir);
			return true;
		}
		return false;
	}
	
	public boolean vinculaGeneroAoFilme(int idFilme, int idGen) {
		try {
			if (existsByIdFilme(idFilme) && generoController.existsByIdGenero(idGen)
					&& generoPertenceAoFilme(idFilme, idGen) != true) {
				service.vinculaGeneroAoFilme(idFilme, idGen);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void desvinculaGeneroDoFilme(int idFilme, int idGen) {
		try {
			if (generoPertenceAoFilme(idFilme, idGen)) {
				service.desvinculaGeneroDoFilme(idFilme, idGen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean validaFilme(String nome, String sinopse, int duracao) throws Exception {
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (duracao < 0) {
			throw new Exception("Duração não pode ser negativa");
		} else if (duracao == 0) {
			throw new Exception("duração não pode ser zero");
		} else if (sinopse.equals("")) {
			throw new Exception("Cidade não pode ser vazio");
		} else if (sinopse.equals(null)) {
			throw new Exception("Cidade não pode ser nulo");
		}
		return true;
	}

	public boolean validaId(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}
	
	public boolean existsByIdFilme(int id) {
		return service.existsById(id);
	}
	
	public boolean atorPertenceAoFilme(int idFilme, int idAtor) {
		if (existsByIdFilme(idFilme) && atorController.existsByIdAtor(idAtor)) {
			Filme filme = buscaFilme(idFilme);
			for (Ator ator : filme.getAtores()) {
				if (ator.getId() == idAtor) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean diretorPertenceAoFilme(int idFilme, int idDir) {
		if (existsByIdFilme(idFilme) && diretorController.existsByIdDiretor(idDir)) {
			Filme filme = buscaFilme(idFilme);
			for (Diretor diretor : filme.getDiretores()) {
				if (diretor.getId() == idDir) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean generoPertenceAoFilme(int idFilme, int idGen) {
		if (existsByIdFilme(idFilme) && generoController.existsByIdGenero(idGen)) {
			Filme filme = buscaFilme(idFilme);
			for (Genero genero : filme.getGeneros()) {
				if (genero.getId() == idGen) {
					return true;
				}
			}
		}
		return false;
	}
}