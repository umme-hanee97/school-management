package com.example.schoolmanagement.routine.service;

import com.example.schoolmanagement.common.lookup.repository.SubjectRepository;
import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.routine.dto.PeriodDto;
import com.example.schoolmanagement.routine.model.Period;
import com.example.schoolmanagement.routine.repository.PeriodRepository;
import com.example.schoolmanagement.student.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService{

    private final PeriodRepository periodRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public PeriodServiceImpl(PeriodRepository periodRepository,
                             SubjectRepository subjectRepository,
                             TeacherRepository teacherRepository) {
        this.periodRepository = periodRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public PeriodDto saveOrUpdatePeriod(PeriodDto periodDto) throws ErrorHandler {
        try {
            if (periodDto.getStartTime().isAfter(periodDto.getEndTime()) || periodDto.getDurationInMinutes() == null) {
                throw new ErrorHandler("Start time, end time and duration must be provided and valid");
            }
            Period period = mapToEntity(periodDto);
            periodRepository.save(period);
            return mapToDto(period);
        } catch (Exception e) {
            throw new ErrorHandler("Error Occurred!!", e);
        }
    }

    @Override
    public List<PeriodDto> getAllPeriods() {
        List<PeriodDto> periods = periodRepository.findAll().stream().map(this::mapToDto).toList();
        return periods;
    }

    @Override
    public PeriodDto getPeriodById(Long id) throws ErrorHandler {
            Period period = periodRepository.findById(id).orElse(null);
            if (period != null) {
                return mapToDto(period);
            } else {
                throw new ErrorHandler("Period not found with id: " + id);
            }
    }

    @Override
    public List<PeriodDto> getPeriodsByRoutineId(Long routineId) {

        return List.of();
    }

    @Override
    public void deletePeriodById(Long id) {

    }

    private PeriodDto mapToDto(Period period) {
        PeriodDto dto = new PeriodDto();
        if (period.getId() != null) dto.setId(period.getId());
        if (period.getStartTime() != null) dto.setStartTime(period.getStartTime());
        if (period.getEndTime() != null) dto.setEndTime(period.getEndTime());
        if (period.getRoomNumber() != null) dto.setRoomNumber(period.getRoomNumber());
        if (period.getDurationInMinutes() != null) dto.setDurationInMinutes(period.getDurationInMinutes());
        if (period.getSubject() != null) {
            dto.setSubjectId(period.getSubject().getId());
            dto.setSubjectName(period.getSubject().getSubjectName());
        }
        if (period.getTeacher() != null) {
            dto.setTeacherId(period.getTeacher().getId());
            dto.setTeacherName(period.getTeacher().getName());
        }
        if (period.getStatus() != null) dto.setStatus(period.getStatus());
        return dto;
    }

    private Period mapToEntity(PeriodDto periodDto) {
        Period period = new Period();
        if (periodDto.getId() != null) period.setId(periodDto.getId());
        if (periodDto.getStartTime() != null) period.setStartTime(periodDto.getStartTime());
        if (periodDto.getEndTime() != null) period.setEndTime(periodDto.getEndTime());
        if (periodDto.getRoomNumber() != null) period.setRoomNumber(periodDto.getRoomNumber());
        if (periodDto.getDurationInMinutes() != null) period.setDurationInMinutes(periodDto.getDurationInMinutes());
        if (periodDto.getSubjectId() != null) {
            period.setSubject(subjectRepository.findById(periodDto.getSubjectId()).get());
        }
        if (periodDto.getTeacherId() != null) {
            period.setTeacher(teacherRepository.findById(periodDto.getTeacherId()).get());
        }
        if (periodDto.getStatus() != null) period.setStatus(periodDto.getStatus());
        return period;
    }
}
