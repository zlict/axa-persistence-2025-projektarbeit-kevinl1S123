package ch.axa.punchclock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.repositories.EntryRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@Tag(name = "Entry API", description = "Verwaltung von Zeiterfassungs-Einträgen")
@RestController
@RequestMapping("/api/entries")
public class APIEntryController {

  @Autowired
  private EntryRepository entryRepository;

  @Operation(summary = "Erstellt einen neuen Zeiteintrag")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Entry create(@RequestBody @Valid Entry entry) {
    return entryRepository.save(entry);
  }

  @Operation(summary = "Listet alle Zeiteinträge auf, optional gefiltert nach Kategorie oder Tag")
  @GetMapping
  public Iterable<Entry> index(
      @Parameter(description = "ID der Kategorie zum Filtern") @RequestParam(required = false) Long categoryId,
      @Parameter(description = "ID des Tags zum Filtern") @RequestParam(required = false) Long tagId) {
    if(categoryId != null){
      return entryRepository.findByCategory(categoryId);
    }
    if(tagId != null){
      return entryRepository.findByTags_Id(tagId);
    }
    return entryRepository.findAll();
  }

  @Operation(summary = "Liest einen Zeiteintrag per ID aus")
  @GetMapping("/{id}")
  public ResponseEntity<Entry> read(@Parameter(description = "ID des Eintrags") @PathVariable Long id) {
    return ResponseEntity.of(entryRepository.findById(id));
  }

  @PutMapping("/{id}")
  public Entry update(@PathVariable Long id, @RequestBody @Valid Entry entry) {
    entry.setId(id);
    return entryRepository.save(entry);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Entry> delete(@PathVariable Long id) {
    var entry = entryRepository.findById(id);
    if(entry.isPresent()) {
      entryRepository.delete(entry.get());
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}