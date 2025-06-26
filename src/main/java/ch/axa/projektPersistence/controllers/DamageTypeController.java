package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.DamageType;
import ch.axa.projektPersistence.repositories.DamageTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/damagetypes")
public class DamageTypeController {
    @Autowired
    private DamageTypeRepository damageTypeRepository;

    @GetMapping
    public Iterable<DamageType> getAllDamageTypes() {
        return damageTypeRepository.findAll();
    }

    @GetMapping("/{id}")
    public DamageType getDamageTypeById(@PathVariable Long id) {
        return damageTypeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public DamageType createDamageType(@RequestBody DamageType damageType) {
        return damageTypeRepository.save(damageType);
    }

    @PutMapping("/{id}")
    public DamageType updateDamageType(@PathVariable Long id, @RequestBody DamageType damageType) {
        damageType.setId(id);
        return damageTypeRepository.save(damageType);
    }

    @DeleteMapping("/{id}")
    public void deleteDamageType(@PathVariable Long id) {
        damageTypeRepository.deleteById(id);
    }
}
