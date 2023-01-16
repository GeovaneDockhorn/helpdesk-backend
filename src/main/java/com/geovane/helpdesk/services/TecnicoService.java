package com.geovane.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geovane.helpdesk.domain.Pessoa;
import com.geovane.helpdesk.domain.Tecnico;
import com.geovane.helpdesk.domain.dtos.TecnicoDTO;
import com.geovane.helpdesk.repositories.PessoaRepository;
import com.geovane.helpdesk.repositories.TecnicoRepository;
import com.geovane.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.geovane.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> optionalTecnico = tecnicoRepository.findById(id);
		return optionalTecnico.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validaPorCpfEEmail(tecnicoDTO);
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		return tecnicoRepository.save(tecnico);
	}
	
	private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
		
		Optional<Pessoa> optionalPessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if(optionalPessoa.isPresent() && optionalPessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no sistema!");
		}
		
		optionalPessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if(optionalPessoa.isPresent() && optionalPessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
		
	}
	
}
