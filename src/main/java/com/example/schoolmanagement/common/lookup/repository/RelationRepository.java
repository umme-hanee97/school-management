package com.example.schoolmanagement.common.lookup.repository;

import com.example.schoolmanagement.common.lookup.model.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relationship, Long> {
}
