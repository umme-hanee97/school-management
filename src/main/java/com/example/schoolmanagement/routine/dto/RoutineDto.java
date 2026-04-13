package com.example.schoolmanagement.routine.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class RoutineDto {
    private Long id;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer period;
    private Integer breakTime;
    private String roomNumber;
    private Integer durationInMinutes;
    private List<Long> subjectIds;
    private List<String> subjectNames;
    private List<Long> teacherIds;
    private List<String> teacherNames;
    private Long classId;
    private String className;
    private Long sectionId;
    private String sectionName;
}
