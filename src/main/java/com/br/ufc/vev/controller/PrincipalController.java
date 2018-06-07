package com.br.ufc.vev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.vev.model.Admin;
import com.br.ufc.vev.service.AdminService;
import com.br.ufc.vev.service.CinemaService;

@Controller
public class PrincipalController {
	
	@Autowired
	CinemaService cService;
	
	@Autowired
	AdminService aService;
	
	@GetMapping(path="/")
	public ModelAndView index() {
		ModelAndView mv =  new ModelAndView("/cinema/cineimah");
		mv.addObject("cinemas", cService.buscarTodosOsCinemas());
		return mv;
	}
	
	@GetMapping(path="/entrar")
	public ModelAndView entrar() {
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("admin", new Admin());
		return mv;
	}
	
	@GetMapping(path="/voltar")
	public ModelAndView voltar(Admin admin) {
		ModelAndView mv =  new ModelAndView("/cinema/cineimah");
		mv.addObject("admin", admin);
		return mv;
	}
	
	@GetMapping(path="/sair")
	public ModelAndView sair() {
		ModelAndView mv = new ModelAndView("/cinema/cineimah");
		mv.addObject("cinemas", cService.buscarTodosOsCinemas());
		mv.addObject("admin", null);
		return mv;
	}
	
	@PostMapping(path="/entrar")
	public ModelAndView entrando(Admin admin) {
		ModelAndView mv = new ModelAndView("/cinema/cineimah");
		mv.addObject("cinemas", cService.buscarTodosOsCinemas());
		try {
			Admin a = aService.logar(admin.getLogin(), admin.getSenha());
			mv.addObject("admin",a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mv.addObject("admin",null);
		}
		return mv;
	}
	
}
