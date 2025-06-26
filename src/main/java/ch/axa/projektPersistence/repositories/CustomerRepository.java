package ch.axa.projektPersistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.projektPersistence.models.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
