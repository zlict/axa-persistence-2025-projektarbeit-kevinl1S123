package ch.axa.projektPersistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.projektPersistence.models.DamageType;

public interface DamageTypeRepository extends CrudRepository<DamageType, Long> {

}