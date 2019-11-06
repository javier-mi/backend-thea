package edu.caece.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import edu.caece.app.domain.Persona;

@RepositoryRestResource(collectionResourceRel = "personas", path = "personas")
@CrossOrigin(origins = "http://localhost:4200")

public interface IPersonaRepositorio extends JpaRepository<Persona, String> {

}
