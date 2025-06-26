package ch.axa.projektPersistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.projektPersistence.models.Claim;

public interface ClaimRepository extends CrudRepository<Claim, Long> {

}
