package com.example.schoolmanagement.routine.model;

import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import com.example.schoolmanagement.student.model.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "periods")
@Getter
@Setter
@RequiredArgsConstructor
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, name = "day")
    private String day;
    @Column(nullable = false, name = "start_time")
    private LocalTime startTime;
    @Column(nullable = false, name = "end_time")
    private LocalTime endTime;
    @Column(nullable = false, name = "room_number")
    private String roomNumber;
    @Column(nullable = false, name = "duration_in_minutes")
    private Integer durationInMinutes;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private StudentSubject subject;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @Column(nullable = false, length = 20, name = "status")
    private String status;

}
