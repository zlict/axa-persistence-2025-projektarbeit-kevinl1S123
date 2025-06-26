package ch.axa.projektPersistence.repositories;

import org.springframework.data.repository.CrudRepository;

import ch.axa.projektPersistence.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}