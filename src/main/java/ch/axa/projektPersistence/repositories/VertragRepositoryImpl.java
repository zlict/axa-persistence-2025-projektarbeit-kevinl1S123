package ch.axa.projektPersistence.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ch.axa.projektPersistence.models.Vertrag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class VertragRepositoryImpl implements VertragRepositoryCustom {

    @Autowired
    private EntityManager em;

    @Override
    public Iterable<Vertrag> findByState(String state) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Vertrag> cq = cb.createQuery(Vertrag.class);

        Root<Vertrag> vertrag = cq.from(Vertrag.class);

        cq.where(cb.equal(vertrag.get("state"), state));

        return em.createQuery(cq).getResultList();
    }

}
