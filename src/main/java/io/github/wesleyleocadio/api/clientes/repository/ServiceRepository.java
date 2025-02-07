package io.github.wesleyleocadio.api.clientes.repository;

import java.security.Provider.Service;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Integer>{

}
