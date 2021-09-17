package com.project.accountposting.specification;

import com.project.accountposting.domain.Title;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Builder
public class TitleSpecification implements Specification<Title> {

    private static final long serialVersionUID = 1L;

    @Builder.Default
    private final transient Optional<Long> id = Optional.empty();

    @Builder.Default
    private final transient Optional<String> situation = Optional.empty();

    @Override
    public Predicate toPredicate(Root<Title> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        id.ifPresent(s -> predicates.add(root.get("id").in(s)));
        situation.ifPresent(s -> predicates.add(builder.like(builder.lower(root.get("situation")), "%" + s.toLowerCase() + "%")));
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
