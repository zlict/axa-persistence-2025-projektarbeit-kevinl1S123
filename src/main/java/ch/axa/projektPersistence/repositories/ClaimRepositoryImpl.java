package ch.axa.projektPersistence.repositories;

import ch.axa.projektPersistence.models.Claim;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClaimRepositoryImpl implements ClaimRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Claim> findAllSortedByDate(String direction) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);
        Root<Claim> claim = cq.from(Claim.class);
        if ("desc".equalsIgnoreCase(direction)) {
            cq.orderBy(cb.desc(claim.get("date")));
        } else {
            cq.orderBy(cb.asc(claim.get("date")));
        }
        return em.createQuery(cq).getResultList();
    }
}
