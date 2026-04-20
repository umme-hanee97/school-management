package com.example.schoolmanagement.routine.service;

import com.example.schoolmanagement.routine.dto.PeriodDto;
import com.example.schoolmanagement.routine.repository.PeriodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService{

    private final PeriodRepository periodRepository;

    public PeriodServiceImpl(PeriodRepository periodRepository) {
        this.periodRepository = periodRepository;
    }

    @Override
    public PeriodDto saveOrUpdatePeriod(PeriodDto periodDto) {

        return null;
    }

    @Override
    public List<PeriodDto> getAllPeriods() {
        return List.of();
    }

    @Override
    public PeriodDto getPeriodById(Long id) {
        return null;
    }

    @Override
    public List<PeriodDto> getPeriodsByRoutineId(Long routineId) {
        return List.of();
    }

    @Override
    public void deletePeriodById(Long id) {

    }
}
