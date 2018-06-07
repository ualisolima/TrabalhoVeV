package com.br.ufc.vev.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.vev.model.Filme;
import com.br.ufc.vev.model.Sala;
import com.br.ufc.vev.model.Sessao;
import com.br.ufc.vev.service.FilmeService;
import com.br.ufc.vev.service.SalaService;
import com.br.ufc.vev.service.SessaoService;

@Controller
@RequestMapping(path= "/sessao/")
public class SessaoController {
		
	@Autowired
	SessaoService sessaoService;
	@Autowired
	FilmeService filmeService;
	@Autowired
	SalaService salaService;
	
	@RequestMapping(path = "/")
	public ModelAndView index() {
// 	todasAsSessoes
		ModelAndView model = new ModelAndView("sessao");
		List<Sessao> sessoes = sessaoService.getTodasSessoes();
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path="/{id}")
	public ModelAndView detalhesSessao(@PathVariable("id") Integer id){
	//+ getSessaoPorIdI(id : int) : Sessao		
	  ModelAndView model = new ModelAndView("detalhes-sessao");
	  Sessao sessao = sessaoService.getSessaoPorId(id);
			
	  model.addObject("sessao", sessao);
			
	  return model;
	}
	
	@RequestMapping(path = "/porData", method = RequestMethod.GET)
	public ModelAndView verTodasPorData(@RequestParam String dataInicio, @RequestParam String dataFim) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		LocalDate dataInicial = LocalDate.parse(dataInicio);
		LocalDate dataFinal = LocalDate.parse(dataFim);
		
		List<Sessao> sessoes = sessaoService.getSessaoPorData(dataInicial, dataFinal);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path = "/porCidade", method = RequestMethod.GET)
	public ModelAndView verTodasPorCidade(@RequestParam String cidade) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		
		List<Sessao> sessoes = sessaoService.getSessaoPorCidade(cidade);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path = "/porFilme", method = RequestMethod.GET)
	public ModelAndView verTodasPorFilme(@RequestParam String filme) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		
		List<Sessao> sessoes = sessaoService.getSessaoPorFilme(filme);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path = "/porGenero", method = RequestMethod.GET)
	public ModelAndView verTodasPorGenero(@RequestParam String genero) {
// 	todasAsSessoesPorData
		ModelAndView model = new ModelAndView("sessao");
		
		List<Sessao> sessoes = sessaoService.getSessaoPorGenero(genero);
		
		model.addObject("sessao", sessoes);
		
		return model;
	}
	
	@RequestMapping(path="/adicionar", method = RequestMethod.POST)
	public ModelAndView addSessao(@RequestParam Filme filme,@RequestParam Sala sala, 
								@RequestParam LocalTime horario, @RequestParam 
								LocalDate dataInicio, @RequestParam LocalDate dataFim) {
//	+ addSessao(sessao : Sessao) : Sessao
		Sessao sessao = new Sessao(filme, sala, horario, dataInicio, dataFim);
		
		try {
			if (this.validaSessao(sessao)) {
				Sessao sessaoRetorno = sessaoService.salvarSessao(sessao);
				
				ModelAndView model = new ModelAndView("sessao");
				model.addObject("sessao", sessaoRetorno);
				
				return model;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(path="/atualizar", method = RequestMethod.POST)
	public ModelAndView atualizarSessao(@RequestParam Integer idSessao, @RequestParam Filme filme,@RequestParam Sala sala, 
			@RequestParam LocalTime horario, @RequestParam LocalDate dataInicio, @RequestParam LocalDate dataFim){
//	+ atualizarSessao(sessao : Sessao) : Sessao
		
		Sessao sessao = new Sessao(filme, sala, horario, dataInicio, dataFim);
		
				sessao.setId(idSessao);
				sessaoService.atualizarSessao(sessao);
		
			ModelAndView model = new ModelAndView("sessao");
			model.addObject("sessao", sessao);
			return model;
				
	}
	
	@RequestMapping(path="/excluir", method = RequestMethod.POST)
	public ModelAndView excluirSessao(@RequestParam Integer idSessao) {
//	+ removerSessao(id : int) : Sessao
		
		if (sessaoService.getSessaoPorId(idSessao) != null) {
			Sessao sessao = sessaoService.deletarSessao(idSessao);
			ModelAndView model = new ModelAndView("sessao");
			model.addObject("sessao", sessao);
			return model;
		}
		return null;
	}
	
	@RequestMapping(path="/busca", method = RequestMethod.POST)
	public ModelAndView buscaSessao(@RequestParam Integer idSessao) {
//	+ removerSessao(id : int) : Sessao
		
		if (sessaoService.getSessaoPorId(idSessao) != null) {
			Sessao sessao = sessaoService.getSessaoPorId(idSessao);
			
			ModelAndView model = new ModelAndView("sessao");
			model.addObject("sessao", sessao);
			return model;
		}
		return null;
	}
	
	public boolean validaSessao(Sessao sessao) throws Exception {
		
		if (sessao.getHorario() == null) {
			throw new Exception();
		} else if (sessao.getDataInicio() == null) {
			throw new Exception();
		} else if (sessao.getDataFim() == null) {
			throw new Exception();
		} else if (sessao.getFilme() == null) {
			throw new Exception();
		} else if (sessao.getSala() == null) {
			throw new Exception();
		}
			
		return true;
	}
}
