package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Police;
import ch.axa.projektPersistence.repositories.PoliceRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Police", description = "Police API")
@RestController
@RequestMapping("/polices")
public class PoliceController {
    @Autowired
    private PoliceRepository policeRepository;

    @Operation(summary = "Alle Policen abrufen")
    @GetMapping
    public Iterable<Police> getAllPolices() {
        return policeRepository.findAll();
    }

    @Operation(summary = "Police nach ID abrufen")
    @GetMapping("/{id}")
    public Police getPoliceById(@Parameter(description = "ID der Police") @PathVariable Long id) {
        return policeRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Police anlegen")
    @PostMapping
    public Police createPolice(@RequestBody Police police) {
        return policeRepository.save(police);
    }

    @Operation(summary = "Police aktualisieren")
    @PutMapping("/{id}")
    public Police updatePolice(@Parameter(description = "ID der Police") @PathVariable Long id, @RequestBody Police police) {
        police.setId(id);
        return policeRepository.save(police);
    }

    @Operation(summary = "Police löschen")
    @DeleteMapping("/{id}")
    public void deletePolice(@Parameter(description = "ID der Police") @PathVariable Long id) {
        policeRepository.deleteById(id);
    }
}
