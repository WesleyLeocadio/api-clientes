package io.github.wesleyleocadio.api.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.wesleyleocadio.api.clientes.entity.Servico;


public interface ServiceRepository extends JpaRepository<Servico, Integer> {
}
