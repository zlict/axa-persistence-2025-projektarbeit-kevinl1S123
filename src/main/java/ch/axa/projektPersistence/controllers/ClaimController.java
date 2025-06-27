package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Claim;
import ch.axa.projektPersistence.repositories.ClaimRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Claim", description = "Claim API")
@RestController
@RequestMapping("/claims")
public class ClaimController {
    @Autowired
    private ClaimRepository claimRepository;

    @Operation(summary = "Alle Schäden abrufen", description = "Gibt alle Schäden zurück, optional sortiert nach Datum.")
    @GetMapping
    public Iterable<Claim> getAllClaims(@Parameter(description = "Sortierung nach Datum (asc/desc)") @RequestParam(required = false) String sortByDate) {
        if (sortByDate != null) {
            return claimRepository.findAllSortedByDate(sortByDate);
        }
        return claimRepository.findAll();
    }

    @Operation(summary = "Schaden nach ID abrufen")
    @GetMapping("/{id}")
    public Claim getClaimById(@Parameter(description = "ID des Schadens") @PathVariable Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    @Operation(summary = "Schaden anlegen")
    @PostMapping
    public Claim createClaim(@RequestBody Claim claim) {
        return claimRepository.save(claim);
    }

    @Operation(summary = "Schaden aktualisieren")
    @PutMapping("/{id}")
    public Claim updateClaim(@Parameter(description = "ID des Schadens") @PathVariable Long id, @RequestBody Claim claim) {
        claim.setId(id);
        return claimRepository.save(claim);
    }

    @Operation(summary = "Schaden löschen")
    @DeleteMapping("/{id}")
    public void deleteClaim(@Parameter(description = "ID des Schadens") @PathVariable Long id) {
        claimRepository.deleteById(id);
    }
}
