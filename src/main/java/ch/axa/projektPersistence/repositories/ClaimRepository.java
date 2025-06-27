package ch.axa.projektPersistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ch.axa.projektPersistence.models.Claim;

@RepositoryRestResource(path = "claims")
public interface ClaimRepository extends CrudRepository<Claim, Long> {

}
