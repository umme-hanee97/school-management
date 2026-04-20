package com.example.schoolmanagement.routine.service;

import com.example.schoolmanagement.routine.dto.PeriodDto;

import java.util.List;

public interface PeriodService {
    PeriodDto saveOrUpdatePeriod(PeriodDto periodDto);
    List<PeriodDto> getAllPeriods();
    PeriodDto getPeriodById(Long id);
    List<PeriodDto> getPeriodsByRoutineId(Long routineId);
    void deletePeriodById(Long id);
}
