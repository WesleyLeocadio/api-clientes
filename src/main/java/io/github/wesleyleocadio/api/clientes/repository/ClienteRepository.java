package io.github.wesleyleocadio.api.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.wesleyleocadio.api.clientes.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
