package ch.axa.projektPersistence.repositories;

import ch.axa.projektPersistence.models.Claim;
import org.springframework.data.repository.CrudRepository;

public interface ClaimRepository extends CrudRepository<Claim, Long>, ClaimRepositoryCustom {
}
