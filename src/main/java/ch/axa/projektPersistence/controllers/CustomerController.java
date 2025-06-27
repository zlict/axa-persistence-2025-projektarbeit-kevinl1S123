package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Customer;
import ch.axa.projektPersistence.repositories.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Customer", description = "Customer API")
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Operation(summary = "Alle Kunden abrufen")
    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Operation(summary = "Kunde nach ID abrufen")
    @GetMapping("/{id}")
    public Customer getCustomerById(@Parameter(description = "ID des Kunden") @PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Kunde anlegen")
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @Operation(summary = "Kunde aktualisieren")
    @PutMapping("/{id}")
    public Customer updateCustomer(@Parameter(description = "ID des Kunden") @PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerRepository.save(customer);
    }

    @Operation(summary = "Kunde löschen")
    @DeleteMapping("/{id}")
    public void deleteCustomer(@Parameter(description = "ID des Kunden") @PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
