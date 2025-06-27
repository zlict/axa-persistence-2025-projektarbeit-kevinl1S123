package ch.axa.projektPersistence.repositories;

import ch.axa.projektPersistence.models.Claim;
import java.util.List;

public interface ClaimRepositoryCustom {
    List<Claim> findAllSortedByDate(String direction);
}
