package ch.axa.projektPersistence.controllers;

import ch.axa.projektPersistence.models.Vertrag;
import ch.axa.projektPersistence.repositories.VertragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vertraege")
public class VertragController {
    @Autowired
    private VertragRepository vertragRepository;

    @GetMapping
    public Iterable<Vertrag> getAllVertraege() {
        return vertragRepository.findAll();
    }

    @GetMapping("/{id}")
    public Vertrag getVertragById(@PathVariable Long id) {
        return vertragRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Vertrag createVertrag(@RequestBody Vertrag vertrag) {
        return vertragRepository.save(vertrag);
    }

    @PutMapping("/{id}")
    public Vertrag updateVertrag(@PathVariable Long id, @RequestBody Vertrag vertrag) {
        vertrag.setId(id);
        return vertragRepository.save(vertrag);
    }

    @DeleteMapping("/{id}")
    public void deleteVertrag(@PathVariable Long id) {
        vertragRepository.deleteById(id);
    }
}
