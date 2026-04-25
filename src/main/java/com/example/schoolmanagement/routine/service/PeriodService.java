package com.example.schoolmanagement.routine.service;

import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.routine.dto.PeriodDto;
import java.util.List;

public interface PeriodService {
    PeriodDto saveOrUpdatePeriod(PeriodDto periodDto) throws ErrorHandler;
    List<PeriodDto> getAllPeriods();
    PeriodDto getPeriodById(Long id) throws ErrorHandler;
    List<PeriodDto> getPeriodsByClassIdAndSectionId(Long classId, Long sectionId);
    void deletePeriodById(Long id);
}
