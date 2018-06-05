package com.br.ufc.vev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ufc.vev.model.Admin;
import com.br.ufc.vev.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository sRepository;
	
	public Admin logar(String login, String senha) throws Exception {
		if (login == null)
			throw new Exception("Login não pode ser nulo");
		if (senha == null)
			throw new Exception("Senha não pode ser nulo");
		return sRepository.logar(login, senha);
	}
	
	public boolean atualizarAdmin(String login, String senha) throws Exception {
		if (login == null)
			throw new Exception("Login não pode ser nulo");
		if (senha == null)
			throw new Exception("Senha não pode ser nulo");
		Admin a = sRepository.findAll().get(0);
		a.setLogin(login);
		a.setSenha(senha);
		return sRepository.save(a) != null;
	}

}
