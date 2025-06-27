package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Claim;
import ch.axa.projektPersistence.repositories.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {
    @Autowired
    private ClaimRepository claimRepository;

    @GetMapping
    public Iterable<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @GetMapping("/{id}")
    public Claim getClaimById(@PathVariable Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Claim createClaim(@RequestBody Claim claim) {
        return claimRepository.save(claim);
    }

    @PutMapping("/{id}")
    public Claim updateClaim(@PathVariable Long id, @RequestBody Claim claim) {
        claim.setId(id);
        return claimRepository.save(claim);
    }

    @DeleteMapping("/{id}")
    public void deleteClaim(@PathVariable Long id) {
        claimRepository.deleteById(id);
    }
}
