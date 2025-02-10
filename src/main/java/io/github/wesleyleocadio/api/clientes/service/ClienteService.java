package io.github.wesleyleocadio.api.clientes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.wesleyleocadio.api.clientes.entity.Cliente;
import io.github.wesleyleocadio.api.clientes.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {	
		return clienteRepository.save(cliente);
	}

	public Optional<Cliente> buscarPorId(Integer id) {
		return clienteRepository.findById(id);
	}
	
}
