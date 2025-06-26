package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Category;
import ch.axa.punchclock.repositories.CategoryRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Tag(name = "Category API", description = "Verwaltung von Kategorien für Zeiteinträge")
@RestController
@RequestMapping("/api/categories")
public class APICategoryController {

  @Autowired
  private CategoryRepository categoryRepository;

  @Operation(summary = "Erstellt eine neue Kategorie")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Category create(@RequestBody @Valid Category category) {
    return categoryRepository.save(category);
  }

  @Operation(summary = "Listet alle Kategorien auf")
  @GetMapping
  public Iterable<Category> index() {
    return categoryRepository.findAll();
  }

  @Operation(summary = "Liest eine Kategorie per ID aus")
  @GetMapping("/{id}")
  public ResponseEntity<Category> read(@Parameter(description = "ID der Kategorie") @PathVariable Long id) {
    return ResponseEntity.of(categoryRepository.findById(id));
  }

  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @RequestBody @Valid Category category) {
    category.setId(id);
    return categoryRepository.save(category);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Category> delete(@PathVariable Long id) {
    var category = categoryRepository.findById(id);
    if(category.isPresent()) {
      categoryRepository.delete(category.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}