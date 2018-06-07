package com.br.ufc.vev.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.ufc.vev.model.Filme;
import com.br.ufc.vev.model.Genero;
import com.br.ufc.vev.model.Sessao;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer>{
	
	@Query(value="SELECT s from Sessao as s WHERE dataInicio >= :dtInicio AND dataFim <= :dtFim")
	public List<Sessao> buscarTodas(@Param(value="dtInicio") LocalTime dataInicio, 
			@Param(value="dtFim") LocalTime dataFim);
	
	@Query(value="SELECT s FROM Sessao as s, Cinema as c WHERE s.sala MEMBER OF c.salas and LOWER(c.cidade) = LOWER(:cidade)")
	public List<Sessao> todasPorCidade(@Param(value = "cidade") String cidade);
	
	@Query(value="SELECT s FROM Sessao as s WHERE s.filme = :filme")
	public List<Sessao> buscarPorFilme(@Param(value="filme") Filme filme);
	
	@Query(value="SELECT DISTINCT s from Sessao as s, Filme as f WHERE s.filme = f AND :genero MEMBER OF f.generos")
	public List<Sessao> buscarPorGenero(@Param(value="genero") Genero genero);

}
