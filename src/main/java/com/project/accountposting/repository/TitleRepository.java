package com.project.accountposting.repository;

import com.project.accountposting.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<Long, Title> {
}
