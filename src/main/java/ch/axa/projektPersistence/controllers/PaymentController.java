package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Payment;
import ch.axa.projektPersistence.repositories.PaymentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment", description = "Payment API")
@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentRepository paymentRepository;

    @Operation(summary = "Alle Zahlungen abrufen")
    @GetMapping
    public Iterable<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Operation(summary = "Zahlung nach ID abrufen")
    @GetMapping("/{id}")
    public Payment getPaymentById(@Parameter(description = "ID der Zahlung") @PathVariable Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Zahlung anlegen")
    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @Operation(summary = "Zahlung aktualisieren")
    @PutMapping("/{id}")
    public Payment updatePayment(@Parameter(description = "ID der Zahlung") @PathVariable Long id, @RequestBody Payment payment) {
        payment.setId(id);
        return paymentRepository.save(payment);
    }

    @Operation(summary = "Zahlung löschen")
    @DeleteMapping("/{id}")
    public void deletePayment(@Parameter(description = "ID der Zahlung") @PathVariable Long id) {
        paymentRepository.deleteById(id);
    }
}
