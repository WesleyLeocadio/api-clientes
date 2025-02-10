package io.github.wesleyleocadio.api.clientes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.wesleyleocadio.api.clientes.entity.Cliente;
import io.github.wesleyleocadio.api.clientes.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;

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

	public void deletar(Integer id) {
	    buscarPorId(id).map(cliente -> {
	        clienteRepository.delete(cliente);
	        return Void.TYPE;
	    }).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o ID: " + id));
	}
	
}
