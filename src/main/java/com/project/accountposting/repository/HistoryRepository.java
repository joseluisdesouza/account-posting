package com.project.accountposting.repository;

import com.project.accountposting.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<Long, History> {
}
