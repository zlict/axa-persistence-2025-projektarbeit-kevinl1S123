package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.DamageType;
import ch.axa.projektPersistence.repositories.DamageTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "DamageType", description = "DamageType API")
@RestController
@RequestMapping("/damagetypes")
public class DamageTypeController {
    @Autowired
    private DamageTypeRepository damageTypeRepository;

    @Operation(summary = "Alle Schadenstypen abrufen")
    @GetMapping
    public Iterable<DamageType> getAllDamageTypes() {
        return damageTypeRepository.findAll();
    }

    @Operation(summary = "Schadenstyp nach ID abrufen")
    @GetMapping("/{id}")
    public DamageType getDamageTypeById(@Parameter(description = "ID des Schadenstyps") @PathVariable Long id) {
        return damageTypeRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Schadenstyp anlegen")
    @PostMapping
    public DamageType createDamageType(@RequestBody DamageType damageType) {
        return damageTypeRepository.save(damageType);
    }

    @Operation(summary = "Schadenstyp aktualisieren")
    @PutMapping("/{id}")
    public DamageType updateDamageType(@Parameter(description = "ID des Schadenstyps") @PathVariable Long id, @RequestBody DamageType damageType) {
        damageType.setId(id);
        return damageTypeRepository.save(damageType);
    }

    @Operation(summary = "Schadenstyp löschen")
    @DeleteMapping("/{id}")
    public void deleteDamageType(@Parameter(description = "ID des Schadenstyps") @PathVariable Long id) {
        damageTypeRepository.deleteById(id);
    }
}
