package com.geovane.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geovane.helpdesk.domain.Tecnico;	

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
	
}
