package com.example.schoolmanagement.common.lookup.repository;

import com.example.schoolmanagement.common.lookup.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<StudentClass, Long> {

    @Query(value = "select (select count(*)  from school.student_class sc) * (select count(*) from school.student_section ss) as count_class", nativeQuery = true)
    Integer countClassWithSection();

}
