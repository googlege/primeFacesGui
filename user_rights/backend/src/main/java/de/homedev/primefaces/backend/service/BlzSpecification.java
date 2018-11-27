package de.homedev.primefaces.backend.service;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.*;

import org.springframework.data.jpa.domain.Specification;

import de.homedev.primefaces.api.model.BlzEntity;

public class BlzSpecification implements Specification<BlzEntity> {
    private static final long serialVersionUID = 1L;
    private final Map<String, Object> filterMap;

    public BlzSpecification(Map<String, Object> filterMap) {
        super();
        this.filterMap = filterMap;
    }

    public Map<String, Object> getFilterMap() {
        return filterMap;
    }

    @Override
    public Predicate toPredicate(Root<BlzEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate mainPredicate = cb.and();

        for (Entry<String, Object> entry : filterMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (value instanceof String) {
                final Predicate singlePredicate = getStartsWithString(root, cb, key, (String) value, false);
                mainPredicate = cb.and(mainPredicate, singlePredicate);
            } else {
                System.err.println("Not supported value class: " + value.getClass());
            }
        }

        return mainPredicate;

    }

    private Predicate getStartsWithString(Path<BlzEntity> path, CriteriaBuilder cb, String key,
                                          String value, boolean caseSensitive) {
        final StringBuilder likeBuilder = new StringBuilder(30);
        likeBuilder.append(caseSensitive ? value : value.toUpperCase());
        likeBuilder.append("%");
        if (caseSensitive) {
            return cb.like(path.get(key).as(String.class), likeBuilder.toString());
        }
        return cb.like(cb.upper(path.get(key).as(String.class)), likeBuilder.toString());
    }

}
