package com.br.ufc.vev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.ufc.vev.model.Diretor;
import com.br.ufc.vev.model.Diretor;
import com.br.ufc.vev.model.Diretor;
import com.br.ufc.vev.model.Diretor;
import com.br.ufc.vev.model.Diretor;
//import com.br.ufc.vev.model.Filme;
import com.br.ufc.vev.service.DiretorService;
//import com.br.ufc.vev.service.FilmeService;

@Controller
@RequestMapping(path = "/diretor")
public class DiretorController {
	@Autowired
	private DiretorService diretorService;

	@RequestMapping(path = "/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("diretor");
		try {
			List<Diretor> diretores = getAllDiretores();

			model.addObject("diretores", diretores);
			return model;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping("/formulario")
	public ModelAndView formularioGenero() {
		ModelAndView model = new ModelAndView("formulario-diretor");
		model.addObject("diretor", new Diretor());

		return model;
	}

	@SuppressWarnings("finally")
	@RequestMapping(path = "/salvar", method = RequestMethod.POST)
	public ModelAndView salvaDiretor(Diretor diretor) {
		ModelAndView model = new ModelAndView("diretor");
		try {
			if (this.validaDiretor(diretor.getNome(), diretor.getSobre())) {
				diretorService.addDiretor(diretor);
				model.addObject("diretorRetorno", diretor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return index();
		}
	}

	public boolean validaDiretor(String nome, String sobre) throws Exception {

		if (nome.equals("")) {
			throw new Exception("Nome não pode ser vazio");
		} else if (nome.equals(null)) {
			throw new Exception("Nome não pode ser nulo");
		} else if (sobre.equals(null)) {
			throw new Exception("Sobre não pode ser nulo");
		} else if (sobre.equals("")) {
			throw new Exception("Sobre não pode ser vazio");
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

	@SuppressWarnings("finally")
	@RequestMapping("/buscar/{id}")
	public ModelAndView buscaDiretor(@PathVariable Integer id) {
		ModelAndView model = new ModelAndView("diretor");
		try {
			if (this.validaId(id)) {
				if (existsByIdDiretor(id)) {
					Diretor diretor = new Diretor();

					diretor = diretorService.buscarDiretor(id);

					model.addObject("diretorRetorno", diretor);
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
	public ModelAndView excluiDiretor(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("diretor");

		try {
			if (validaId(id) && existsByIdDiretor(id)) {
				diretorService.removerDiretor(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return index();
		}
	}

	public List<Diretor> getAllDiretores() {
		return diretorService.buscarTodosDiretores();
	}

	@SuppressWarnings("finally")
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizaDiretor(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("formulario-diretor");

		try {
			if (existsByIdDiretor(id)) {
				Diretor diretor = diretorService.buscarDiretor(id);

				model.addObject("diretor", diretor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return model;
		}
	}

	public boolean existsByIdDiretor(int id) {
		try {
			return diretorService.buscarDiretor(id) != null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
