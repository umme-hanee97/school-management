package com.example.schoolmanagement.routine.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;

@Getter
@Setter
@RequiredArgsConstructor
public class PeriodDto {

    private Long id;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomNumber;
    private Integer durationInMinutes;
    private Long subjectId;
    private String subjectName;
    private Long teacherId;
    private String teacherName;
    private String status;

}
