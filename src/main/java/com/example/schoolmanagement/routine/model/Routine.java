package com.example.schoolmanagement.routine.model;

import com.example.schoolmanagement.common.lookup.model.StudentClass;
import com.example.schoolmanagement.common.lookup.model.StudentSection;
import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import com.example.schoolmanagement.student.model.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "routines")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;
    private LocalTime time;
    @ManyToMany
    @JoinTable(
        name = "routine_subjects",
        joinColumns = @JoinColumn(name = "routine_id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<StudentSubject> subject;
    @ManyToMany
    @JoinTable(
        name = "routine_teachers",
        joinColumns = @JoinColumn(name = "routine_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teacher;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private StudentClass className;
    @OneToOne
    @JoinColumn(name = "section_id")
    private StudentSection section;

}
