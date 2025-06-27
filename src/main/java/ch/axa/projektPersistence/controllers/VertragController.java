package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Vertrag;
import ch.axa.projektPersistence.repositories.VertragRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Vertrag", description = "Vertrag API")
@RestController
@RequestMapping("/vertrage")
public class VertragController {
    @Autowired
    private VertragRepository vertragRepository;

    @Operation(summary = "Alle Verträge abrufen", description = "Gibt alle Verträge zurück, optional gefiltert nach Status.")
    @GetMapping
    public Iterable<Vertrag> getAllVertraege(@Parameter(description = "Status-Filter") @RequestParam(required = false) String filterByState) {

        if (filterByState != null) {
            return vertragRepository.findByState(filterByState);
        }

        return vertragRepository.findAll();
    }

    @Operation(summary = "Vertrag nach ID abrufen")
    @GetMapping("/{id}")
    public Vertrag getVertragById(@Parameter(description = "ID des Vertrags") @PathVariable Long id) {
        return vertragRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Vertrag anlegen")
    @PostMapping
    public Vertrag createVertrag(@RequestBody Vertrag vertrag) {
        return vertragRepository.save(vertrag);
    }

    @Operation(summary = "Vertrag aktualisieren")
    @PutMapping("/{id}")
    public Vertrag updateVertrag(@Parameter(description = "ID des Vertrags") @PathVariable Long id, @RequestBody Vertrag vertrag) {
        vertrag.setId(id);
        return vertragRepository.save(vertrag);
    }

    @Operation(summary = "Vertrag löschen")
    @DeleteMapping("/{id}")
    public void deleteVertrag(@Parameter(description = "ID des Vertrags") @PathVariable Long id) {
        vertragRepository.deleteById(id);
    }
}
