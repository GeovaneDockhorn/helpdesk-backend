package com.geovane.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geovane.helpdesk.domain.Chamado;
import com.geovane.helpdesk.domain.Cliente;
import com.geovane.helpdesk.domain.Tecnico;
import com.geovane.helpdesk.domain.enums.Prioridade;
import com.geovane.helpdesk.domain.enums.Status;
import com.geovane.helpdesk.repositories.ChamadoRepository;
import com.geovane.helpdesk.repositories.ClienteRepository;
import com.geovane.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository; 

	public void instanciaDB() {
		Tecnico tecnico1 = new Tecnico(null, "Geovane Dockhorn", "36068628655", "geovane@mail.com", "123");
		
		Cliente cliente1 = new Cliente(null, "Henrique Souza", "65880581284", "henr@mail.com", "123");
		
		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "primeiro chamado", tecnico1, cliente1);
		
		tecnicoRepository.saveAll(Arrays.asList(tecnico1));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		chamadoRepository.saveAll(Arrays.asList(chamado1)); 
	}
	
}
