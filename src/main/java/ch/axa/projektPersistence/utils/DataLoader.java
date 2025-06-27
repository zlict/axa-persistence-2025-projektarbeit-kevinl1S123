package ch.axa.projektPersistence.utils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ch.axa.projektPersistence.models.Claim;
import ch.axa.projektPersistence.models.Customer;
import ch.axa.projektPersistence.models.DamageType;
import ch.axa.projektPersistence.models.Payment;
import ch.axa.projektPersistence.models.Police;
import ch.axa.projektPersistence.models.Vertrag;
import ch.axa.projektPersistence.repositories.ClaimRepository;
import ch.axa.projektPersistence.repositories.CustomerRepository;
import ch.axa.projektPersistence.repositories.DamageTypeRepository;
import ch.axa.projektPersistence.repositories.PaymentRepository;
import ch.axa.projektPersistence.repositories.PoliceRepository;
import ch.axa.projektPersistence.repositories.VertragRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DamageTypeRepository damageTypeRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PoliceRepository policeRepository;
    @Autowired
    private VertragRepository vertragRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Kunden
        Customer customer1 = new Customer();
        customer1.setFirstName("Max");
        customer1.setLastName("Mustermann");
        customer1.setEmail("max@mustermann.ch");
        customer1.setPhoneNumber("0791234567");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setFirstName("Erika");
        customer2.setLastName("Musterfrau");
        customer2.setEmail("erika@musterfrau.ch");
        customer2.setPhoneNumber("0789876543");
        customerRepository.save(customer2);

        // Verträge
        Vertrag vertrag1 = new Vertrag();
        vertrag1.setStartingDate(LocalDateTime.now().minusYears(1));
        vertrag1.setState("aktiv");
        vertrag1.setCustomer(customer1);
        vertragRepository.save(vertrag1);

        Vertrag vertrag2 = new Vertrag();
        vertrag2.setStartingDate(LocalDateTime.now().minusMonths(6));
        vertrag2.setState("gekündigt");
        vertrag2.setCustomer(customer2);
        vertragRepository.save(vertrag2);

        // Policen
        Police police1 = new Police();
        police1.setType("Haftpflicht");
        police1.setAmountInsured(100000);
        police1.setVertrag(vertrag1);
        policeRepository.save(police1);

        Police police2 = new Police();
        police2.setType("Hausrat");
        police2.setAmountInsured(50000);
        police2.setVertrag(vertrag2);
        policeRepository.save(police2);

        // DamageTypes
        DamageType damageType1 = new DamageType();
        damageType1.setType("Wasserschaden");
        damageTypeRepository.save(damageType1);
        DamageType damageType2 = new DamageType();
        damageType2.setType("Brandschaden");
        damageTypeRepository.save(damageType2);

        // Claims
        Claim claim1 = new Claim();
        claim1.setDate(LocalDateTime.now().minusDays(10));
        claim1.setAmount(1200.50);
        claim1.setDescription("Rohrbruch im Bad");
        claim1.setVertrag(vertrag1);
        Set<DamageType> claim1Types = new HashSet<>();
        claim1Types.add(damageType1);
        claim1.setDamageTypes(claim1Types);
        claimRepository.save(claim1);

        Claim claim2 = new Claim();
        claim2.setDate(LocalDateTime.now().minusDays(5));
        claim2.setAmount(3500.00);
        claim2.setDescription("Küchenbrand");
        claim2.setVertrag(vertrag2);
        Set<DamageType> claim2Types = new HashSet<>();
        claim2Types.add(damageType2);
        claim2.setDamageTypes(claim2Types);
        claimRepository.save(claim2);

        // Payments
        Payment payment1 = new Payment();
        payment1.setAmount(1000.00);
        payment1.setDueDate(LocalDateTime.now().plusDays(10));
        payment1.setVertrag(vertrag1);
        paymentRepository.save(payment1);

        Payment payment2 = new Payment();
        payment2.setAmount(3000.00);
        payment2.setDueDate(LocalDateTime.now().plusDays(20));
        payment2.setVertrag(vertrag2);
        paymentRepository.save(payment2);
    }
}
