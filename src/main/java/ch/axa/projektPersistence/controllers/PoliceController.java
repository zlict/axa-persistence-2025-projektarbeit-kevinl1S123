package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Police;
import ch.axa.projektPersistence.repositories.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/polices")
public class PoliceController {
    @Autowired
    private PoliceRepository policeRepository;

    @GetMapping
    public Iterable<Police> getAllPolices() {
        return policeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Police getPoliceById(@PathVariable Long id) {
        return policeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Police createPolice(@RequestBody Police police) {
        return policeRepository.save(police);
    }

    @PutMapping("/{id}")
    public Police updatePolice(@PathVariable Long id, @RequestBody Police police) {
        police.setId(id);
        return policeRepository.save(police);
    }

    @DeleteMapping("/{id}")
    public void deletePolice(@PathVariable Long id) {
        policeRepository.deleteById(id);
    }
}
