package ch.axa.punchclock.utils;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ch.axa.punchclock.models.Category;
import ch.axa.punchclock.models.Entry;
import ch.axa.punchclock.models.Tag;
import ch.axa.punchclock.repositories.CategoryRepository;
import ch.axa.punchclock.repositories.EntryRepository;
import ch.axa.punchclock.repositories.TagRepository;

@Component
public class DataLoader implements ApplicationRunner {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private EntryRepository entryRepository;

  @Autowired
  private TagRepository tagRepository;



  @Override
  public void run(ApplicationArguments args) throws Exception {
    // Beispiel-Kategorien
    var category1 = new Category();
    category1.setName("Entwicklung");
    var category2 = new Category();
    category2.setName("Meeting");
    var category3 = new Category();
    category3.setName("Support");
    categoryRepository.save(category1);
    categoryRepository.save(category2);
    categoryRepository.save(category3);

    // Beispiel-Tags
    var tag1 = new Tag();
    tag1.setName("Frontend");
    var tag2 = new Tag();
    tag2.setName("Backend");
    var tag3 = new Tag();
    tag3.setName("Bugfix");
    tagRepository.save(tag1);
    tagRepository.save(tag2);
    tagRepository.save(tag3);

    Set<Tag> testTags1 = Set.of(tag1, tag2);
    Set<Tag> testTags2 = Set.of(tag2, tag3);
    Set<Tag> testTags3 = Set.of(tag1, tag3);


    // Beispiel-Einträge
    var entry1 = new Entry();
    entry1.setDescription("Projektmeeting mit dem Team");
    entry1.setCheckIn(java.time.LocalDateTime.now().minusDays(1).withHour(9).withMinute(0));
    entry1.setDuration(90);
    entry1.setCategory(category2);
    entry1.setTags(testTags3);
    entryRepository.save(entry1);

    var entry2 = new Entry();
    entry2.setDescription("Feature-Implementierung: Login");
    entry2.setCheckIn(java.time.LocalDateTime.now().minusDays(1).withHour(11).withMinute(0));
    entry2.setDuration(120);
    entry2.setCategory(category1);
    entry2.setTags(testTags1);
    entryRepository.save(entry2);

    var entry3 = new Entry();
    entry3.setDescription("Support-Anfrage gelöst");
    entry3.setCheckIn(java.time.LocalDateTime.now().minusDays(1).withHour(14).withMinute(0));
    entry3.setDuration(60);
    entry3.setCategory(category3);
    entry3.setTags(testTags2);
    entryRepository.save(entry3);
  }

}
