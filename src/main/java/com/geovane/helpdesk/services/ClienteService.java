package com.geovane.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.geovane.helpdesk.domain.Cliente;
import com.geovane.helpdesk.domain.Pessoa;
import com.geovane.helpdesk.domain.dtos.ClienteDTO;
import com.geovane.helpdesk.repositories.ClienteRepository;
import com.geovane.helpdesk.repositories.PessoaRepository;
import com.geovane.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.geovane.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		return optionalCliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
		validaPorCpfEEmail(clienteDTO);
		Cliente cliente = new Cliente(clienteDTO);
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return clienteRepository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Cliente cliente = findById(id);

		if (cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}

		clienteRepository.deleteById(id);
	}
	
	private void validaPorCpfEEmail(ClienteDTO clienteDTO) {
		
		Optional<Pessoa> optionalPessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
		if(optionalPessoa.isPresent() && optionalPessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("Cpf já cadastrado no sistema!");
		}
		
		optionalPessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
		if(optionalPessoa.isPresent() && optionalPessoa.get().getId() != clienteDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
		
	}
	
}
