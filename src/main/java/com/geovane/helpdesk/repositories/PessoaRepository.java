package com.geovane.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geovane.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	
}
