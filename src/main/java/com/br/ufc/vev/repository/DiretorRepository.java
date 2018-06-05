package com.br.ufc.vev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ufc.vev.model.Diretor;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Integer>{

}
