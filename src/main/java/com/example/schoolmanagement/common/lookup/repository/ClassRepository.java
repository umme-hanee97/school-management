package com.example.schoolmanagement.common.lookup.repository;

import com.example.schoolmanagement.common.lookup.dto.ClassDto;
import com.example.schoolmanagement.common.lookup.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<StudentClass, Long> {

    @Query(value = "select (select count(*)  from school.student_class sc) * (select count(*) from school.student_section ss) as count_class", nativeQuery = true)
    Integer countClassWithSection();

    @Query(value = "select sc.id as id, sc.class_name as className, sc.class_name_code as classNameCode, ss.id as sectionId, ss.section_name as sectionName from school.student_class sc cross join school.student_section ss order by id, sectionName", nativeQuery = true)
    List<ClassDto> findAllClassWithSection();
}
