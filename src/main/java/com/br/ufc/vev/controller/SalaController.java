package br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufc.vev.bean.Diretor;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.service.SalaService;

@Controller
@RequestMapping(path = "/sala")
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	
	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("sala");
		try {
			List<Sala> salas = getAllSala();

			model.addObject("salas", salas);
			return model;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-sala");
		model.addObject("sala", new Sala());

		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaSala(Sala sala) {
		ModelAndView model = new ModelAndView("sala");

		try {
			if (this.validaSala(sala.getNome(), sala.getCapacidade())) {
				salaService.salvarSala(sala);
				
				model.addObject("salaRetorno", sala);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}
	
	public boolean validaSala(String nome, int capacidade) throws Exception {
		
		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (capacidade <= 0) {
			throw new Exception("Quantidades de lugares não pode ser menor ou igual a zero");
		}	 
		return true;
	}
	
	public boolean validaIdSala(int id) throws Exception {
		if (id == 0) {
			throw new Exception("Erro ID deve ser maior que zero");
		} else if (id < 0) {
			throw new Exception("Erro ID não pode ser negativo");
		}
		return true;
	}

	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaSala(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("sala");
		try {
			if (this.validaIdSala(id)) {
				if (existsByIdSala(id)) {
					Sala sala = new Sala();

					sala = salaService.buscarSala(id);

					model.addObject("salaRetorno", sala);
				} else {
					// mensagem de erro "id nao existente no banco"
				}
			} else {
				// msg de id invalido
			}
		} catch (Exception e) { // caso de erro
			e.printStackTrace();
		} finally { // sempre será execultado
			return index();
		}
	}

	@SuppressWarnings("finally")
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluiSala(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("sala");
		
		try {
			Sala sala = new Sala();
			if (validaIdSala(id) && existsByIdSala(id)) {
				sala = salaService.buscarSala(id);
				salaService.excluirSala(sala);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return index();
		}
	}

	public List<Sala> getAllSala() {		
		return salaService.getAllSala();
	}

	// o metodo utilizado para atualizar será o salvar, visto que o spring boot ja
		// atualiza automaticamente o objeto passado.
		// este método só redireciona para a digitação dos novos campos do model
		@SuppressWarnings("finally")
		@RequestMapping("/atualizar/{id}")
		public ModelAndView atualizaSala(@PathVariable("id") Integer id) {
			ModelAndView model = new ModelAndView("formulario-sala");

			try {
				if (existsByIdSala(id)) {
					Sala sala = salaService.buscarSala(id);

					model.addObject("sala", sala);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return model;
			}
		}

	public boolean existsByIdSala(int id) {
		return salaService.buscaSala(id);
	}
	
}
