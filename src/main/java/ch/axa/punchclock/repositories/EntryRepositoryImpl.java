package ch.axa.punchclock.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.axa.punchclock.models.Entry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class EntryRepositoryImpl implements EntryRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Iterable<Entry> searchDescription(String description) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Entry> cq = cb.createQuery(Entry.class);

        Root<Entry> entry = cq.from(Entry.class);

        cq.where(cb.like(entry.get("description"), "%"+description+"%"));

        return em.createQuery(cq).getResultList();
    }

}
