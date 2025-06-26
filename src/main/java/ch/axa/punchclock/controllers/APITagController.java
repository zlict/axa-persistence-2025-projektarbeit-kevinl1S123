package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Tag;
import ch.axa.punchclock.repositories.TagRepository;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag API", description = "Verwaltung von Tags für Zeiteinträge")
@RestController
@RequestMapping("/api/tags")
public class APITagController {

  @Autowired
  private TagRepository tagRepository;

  @Operation(summary = "Erstellt einen neuen Tag")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Tag create(@RequestBody @Valid Tag tag) {
    return tagRepository.save(tag);
  }

  @Operation(summary = "Listet alle Tags auf")
  @GetMapping
  public Iterable<Tag> index() {
    return tagRepository.findAll(); 
  }

  @Operation(summary = "Liest einen Tag per ID aus")
  @GetMapping("/{id}")
  public ResponseEntity<Tag> read(@Parameter(description = "ID des Tags") @PathVariable Long id) {
    return ResponseEntity.of(tagRepository.findById(id));
  }

  @Operation(summary = "Aktualisiert einen bestehenden Tag")
  @PutMapping("/{id}")
  public Tag update(@PathVariable Long id, @RequestBody @Valid Tag tag) {
    tag.setId(id);
    return tagRepository.save(tag);
  }

  @Operation(summary = "Löscht einen Tag per ID")
  @DeleteMapping("/{id}")
  public ResponseEntity<Tag> delete(@PathVariable Long id) {
    var tag = tagRepository.findById(id);
    if(tag.isPresent()) {
      tagRepository.delete(tag.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}