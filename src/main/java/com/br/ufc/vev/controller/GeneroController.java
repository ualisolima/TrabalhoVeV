package com.br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.br.ufc.vev.model.Genero;
import com.br.ufc.vev.service.GeneroService;

@Controller
@RequestMapping(path= "/genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(path="/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("genero");
		try {
			List<Genero> generos = getAllGenero();
			
			model.addObject("generos", generos);
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-genero");
		model.addObject("genero", new Genero());
		
		return model;
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(path="/salvar", method = RequestMethod.POST)
	public ModelAndView salvaGenero(Genero genero) {
		ModelAndView model = new ModelAndView("genero");
		
		try {
			if (this.validaGenero(genero.getDescricao())) { // adiciona um genero novo
				
				generoService.addGenero(genero);
				
				model.addObject("generoRetorno", genero);
				
		 	}
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.buscarTodosGeneros();
			model.addObject("generos", generos);
			return model;
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaGenero(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("genero");
		try {
			if(this.validaIdGenero(id)) {
				if(existsByIdGenero(id)) {
					Genero genero = new Genero();
					
					genero = generoService.buscarGenero(id);
					
					model.addObject("generoRetorno", genero);
				}else {
					//mensagem de erro "id nao existente no banco"
				}
		 	}else {
		 		//msg de id invalido
		 	}
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.buscarTodosGeneros();
			model.addObject("generos", generos);
			return model;
		}
	}

	@SuppressWarnings("finally")
	@GetMapping("/excluir/{id}")
	public ModelAndView excluiGenero(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("genero");
		try {
			if (validaIdGenero(id) && existsByIdGenero(id)) { 
				generoService.removerGenero(id);
		 	}
			
		} catch (Exception e) { 	// caso de erro 
			e.printStackTrace();
		} finally { // sempre será execultado
			List<Genero> generos = generoService.buscarTodosGeneros();
			model.addObject("generos", generos);
			return model;
		}
	}

	public List<Genero> getAllGenero() {		
		return generoService.buscarTodosGeneros();
	}

	//o metodo utilizado para atualizar será o salvar, visto que o spring boot ja atualiza automaticamente o objeto passado.
	//este método só redireciona para a digitação dos novos campos do model
	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaGenero(@PathVariable int id) {
		ModelAndView model = new ModelAndView("formulario-genero");
		try {
			if (existsByIdGenero(id)) {
				Genero genero = generoService.buscarGenero(id);
				
				model.addObject("genero", genero);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return model;
		}
		
	}
	
	public boolean validaGenero(String nome) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} 
		for (Genero genero : getAllGenero()) {
			// Verifica se existe um genero de mesmo nome no banco de dados
			if (genero.getDescricao().equals(nome)) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean validaIdGenero(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro, ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro, ID não pode ser negativo");
		} 
		return true;
	}
	
	public boolean existsByIdGenero(int id) {
		return generoService.buscarGenero(id) != null;
	}
	
}
