package io.github.wesleyleocadio.api.clientes.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.wesleyleocadio.api.clientes.entity.Cliente;
import io.github.wesleyleocadio.api.clientes.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody @Valid Cliente cliente) {
        Cliente clientePersistido = clienteService.salvar(cliente);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientePersistido.getId())
                .toUri();

        return ResponseEntity.created(location).body(clientePersistido);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
	    Optional<Cliente> clienteConsultado = clienteService.buscarPorId(id);
	    return clienteConsultado
	            .map(cliente -> ResponseEntity.ok(cliente))  // Retorna o cliente encontrado com status 200 (OK)
	            .orElseGet(() -> ResponseEntity.notFound().build());  // Retorna 404 (Not Found) caso o cliente não seja encontrado
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
	    try {
	        clienteService.deletar(id);
	        return ResponseEntity.noContent().build();  // Status 204 (sem conteúdo) após exclusão
	    } catch (EntityNotFoundException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
	    }
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
	    try {
	        clienteService.atualizar(id, clienteAtualizado);
	        return ResponseEntity.noContent().build();  // Status 204 (sem conteúdo) após atualização
	    } catch (EntityNotFoundException e) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
	    }
	}
	
}
