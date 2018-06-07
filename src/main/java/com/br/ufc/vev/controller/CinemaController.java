package com.br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.vev.model.Admin;
import com.br.ufc.vev.model.Cinema;
import com.br.ufc.vev.service.CinemaService;

@Controller
public class CinemaController {
	
	@Autowired
	private CinemaService service;
	
	@GetMapping(path="/cinema/add")
	public ModelAndView salvarCinema(Admin admin) {
		ModelAndView mv = new ModelAndView("cinema/cinemaAdd");
		
		mv.addObject("cinema", new Cinema());
		mv.addObject("admin", admin);
		return mv;
	}
	
	@PostMapping(path="/cinema/save")
	public ModelAndView salvandoCinema(Cinema cinema, Admin admin) {
		ModelAndView mv = new ModelAndView("cinema/cinemaAdd");
		
		try {
			if (this.validaCinema(cinema.getNome(), cinema.getCidade(), cinema.getEndereco())) {
				
				mv.addObject("cinema", service.addCinema(cinema));
				mv.addObject("admin", admin);
				return mv;
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv2 = new ModelAndView("cinema/cineimah");
		mv2.addObject("admin", admin);
		mv2.addObject("cinemas", service.buscarTodosOsCinemas());
		return mv2; 
	}
	
	public boolean validaCinema(String nome, String cidade, String endereco) throws Exception {
		
		if (nome.equals(""))
			throw new Exception("Nome não pode ser vazio");
		else if (nome == null)
			throw new Exception("Nome não pode ser nulo");
		else if (endereco.equals(""))
			throw new Exception("Endereco não pode ser vazio");
		else if (endereco == null)
			throw new Exception("Endereco não pode ser nulo");
		else if (cidade.equals(""))
			throw new Exception("Cidade não pode ser vazio");
		else if (cidade == null) {
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
	
	@GetMapping(path="/cinema/edit/{id}")
	public ModelAndView buscaCinema(@PathVariable(name="id") int id, Admin admin) {
		
		try {
			if (validaId(id)) {
				ModelAndView mv = new ModelAndView("cinema/cinemaAdd");
				mv.addObject("cinema", service.addCinema(service.buscarCinema(id)));
				mv.addObject("admin", admin);	
				return mv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mv2 = new ModelAndView("cinema/cineimah");
		mv2.addObject("admin", admin);
		mv2.addObject("cinemas", service.buscarTodosOsCinemas());
		return mv2; 
	}

	public boolean excluiCinema(int id) {
		try {
			if (validaId(id)) {
				service.removerCinema(id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Cinema> getAllCinema() {		
		return service.buscarTodosOsCinemas();
	}

//	public boolean atualizaCinema(Cinema cinema) {
//		try {
//			if (buscaCinema(cinema.getId()) != null && 
//					validaCinema(cinema.getNome(), cinema.getCidade(), cinema.getEndereco()) &&
//					validaId(cinema.getId())) {
//				service.atualizarCinema(cinema);
//				return true;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	public boolean vinculaSalaAoCinema(int idCine, int idSala) {
		try {
			if (validaId(idCine) && validaId(idSala)) {
				return service.vincularSala(idSala, idCine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void desvinculaSalaAoCinema(int idCine, int idSala) {
		try {
			if (validaId(idCine) && validaId(idSala)) {
				service.desvincularSala(idSala, idCine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

