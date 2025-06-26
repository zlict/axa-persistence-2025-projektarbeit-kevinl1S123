package ch.axa.punchclock.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.axa.punchclock.models.Entry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class EntryService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void create(Entry entry) {
        this.em.persist(entry);
    }

    public Entry read(Long id) {
        return this.em.find(Entry.class, id);
    }

    public List<Entry> index() {
        return this.em.createQuery("SELECT e FROM Entry e", Entry.class).getResultList();
    }

    @Transactional
    public void update(Entry entry) {
        this.em.merge(entry);
    }

    @Transactional
    public void delete(Entry entry) {
        Entry managedEntry = em.contains(entry) ? entry : em.merge(entry);
        em.remove(managedEntry);
    }
}