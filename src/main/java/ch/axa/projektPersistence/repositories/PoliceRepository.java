package ch.axa.projektPersistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.projektPersistence.models.Police;

public interface PoliceRepository extends CrudRepository<Police, Long> {

}