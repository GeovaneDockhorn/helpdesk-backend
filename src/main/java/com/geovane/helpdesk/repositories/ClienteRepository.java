package com.geovane.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geovane.helpdesk.domain.Cliente;	

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
