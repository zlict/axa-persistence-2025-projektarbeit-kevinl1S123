package ch.axa.projektPersistence.repositories;

import ch.axa.projektPersistence.models.Vertrag;

public interface VertragRepositoryCustom {
    Iterable<Vertrag> findByState(String state);
} 
