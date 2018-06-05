package com.br.ufc.vev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.ufc.vev.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
	
	@Query(value= "SELECT DISTINCT a FROM Admin a WHERE a.login = :login AND a.senha = :senha")
	public Admin logar(@Param(value="login") String login, @Param(value="senha") String senha);
	

}
