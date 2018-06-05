package com.br.ufc.vev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.ufc.vev.model.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer>{
	
	@Query(value="SELECT f FROM Filme f WHERE LOWER(f.nome) LIKE CONCAT('%', LOWER(:nome),'%')")
	public List<Filme> buscarFilmeNome( @Param(value="nome") String nome);

}
