package com.geovane.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geovane.helpdesk.domain.Chamado;	

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{
	
}
