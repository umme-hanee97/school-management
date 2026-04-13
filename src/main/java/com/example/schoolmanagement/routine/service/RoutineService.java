package com.example.schoolmanagement.routine.service;

import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.routine.dto.RoutineDto;
import java.util.List;

public interface RoutineService {
    RoutineDto getRoutineById(Long id) throws ErrorHandler;
    List<RoutineDto> getRoutinesByClassAndSection(Long classId, Long sectionId) throws ErrorHandler;
    RoutineDto createRoutine(RoutineDto routineDto) throws ErrorHandler;
    void deleteRoutine(Long id);
}
