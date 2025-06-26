package ch.axa.projektPersistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.projektPersistence.models.Vertrag;

public interface VertragRepository extends CrudRepository<Vertrag, Long> {

}