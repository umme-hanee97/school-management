package com.example.schoolmanagement.routine.service;

import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import com.example.schoolmanagement.common.lookup.repository.ClassRepository;
import com.example.schoolmanagement.common.lookup.repository.SectionRepository;
import com.example.schoolmanagement.common.lookup.repository.SubjectRepository;
import com.example.schoolmanagement.common.model.ErrorHandler;
import com.example.schoolmanagement.routine.dto.RoutineDto;
import com.example.schoolmanagement.routine.model.Routine;
import com.example.schoolmanagement.routine.repository.RoutineRepository;
import com.example.schoolmanagement.student.model.Teacher;
import com.example.schoolmanagement.student.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoutineServiceImpl implements RoutineService {

    private final RoutineRepository routineRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ClassRepository classRepository;
    private final SectionRepository sectionRepository;

    public RoutineServiceImpl(RoutineRepository routineRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, ClassRepository classRepository, SectionRepository sectionRepository) {
        this.routineRepository = routineRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public RoutineDto getRoutineById(Long id) throws ErrorHandler {
        try {
            Routine routine = routineRepository.findById(id).orElseThrow(() -> new ErrorHandler("Routine not found"));
            return mapToDto(routine);
        } catch (Exception e) {
            throw new ErrorHandler("Error Occurred!!", e);
        }
    }

    @Override
    public List<RoutineDto> getRoutinesByClassAndSection(Long classId, Long sectionId) throws ErrorHandler {
        try {
            List<Routine> routines = routineRepository.findByClassNameIdAndSectionId(classId, sectionId);
            return routines.stream().map(this::mapToDto).toList();
        } catch (Exception e) {
            throw new ErrorHandler("Error Occurred!!", e);
        }
    }

    @Override
    public RoutineDto createRoutine(RoutineDto routineDto) throws ErrorHandler {
        try {
            if (routineDto.getStartTime().isAfter(routineDto.getEndTime()) || routineDto.getPeriod() == null || routineDto.getDurationInMinutes() == null) {
                throw new ErrorHandler("Start time, end time, period and duration must be provided and valid");
            }
            Routine routine = mapToEntity(routineDto);
            routine.setStatus("Pending");
            routineRepository.save(routine);
            return routineDto;
        } catch (Exception e) {
            throw new ErrorHandler("Error Occurred!!", e);
        }
    }

    @Override
    public void deleteRoutine(Long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(() -> new RuntimeException("Routine not found"));
        routineRepository.delete(routine);
    }

    @Override
    public void updateStatus(Long id, String status) throws ErrorHandler {
        try {
            Routine routine = routineRepository.findById(id).orElseThrow(() -> new ErrorHandler("Routine not found"));
            routine.setStatus(status);
            routineRepository.save(routine);
        } catch (Exception e) {
            throw new ErrorHandler("Error Occurred!!", e);
        }
    }

    private RoutineDto mapToDto(Routine routine) {
        RoutineDto dto = new RoutineDto();
        if (routine.getId() != null) dto.setId(routine.getId());
        if (routine.getDay() != null) dto.setDay(routine.getDay());
        if (routine.getStartTime() != null) dto.setStartTime(routine.getStartTime());
        if (routine.getEndTime() != null) dto.setEndTime(routine.getEndTime());
        if (routine.getPeriod() != null) dto.setPeriod(routine.getPeriod());
        if (routine.getBreakTime() != null) dto.setBreakTime(routine.getBreakTime());
        if (routine.getRoomNumber() != null) dto.setRoomNumber(routine.getRoomNumber());
        if (routine.getDurationInMinutes() != null) dto.setDurationInMinutes(routine.getDurationInMinutes());
        if (routine.getSubject() != null) {
            dto.setSubjectIds(routine.getSubject().stream().map(StudentSubject::getId).toList());
            dto.setSubjectNames(routine.getSubject().stream().map(StudentSubject::getSubjectName).toList());
        }
        if (routine.getTeacher() != null) {
            dto.setTeacherIds(routine.getTeacher().stream().map(Teacher::getId).toList());
            dto.setTeacherNames(routine.getTeacher().stream().map(Teacher::getName).toList());
        }
        if (routine.getClassName() != null) {
            dto.setClassId(routine.getClassName().getId());
            dto.setClassName(routine.getClassName().getClassName());
        }
        if (routine.getSection() != null) {
            dto.setSectionId(routine.getSection().getId());
            dto.setSectionName(routine.getSection().getSectionName());
        }
        return dto;
    }

    private Routine mapToEntity(RoutineDto dto) {
        Routine routine = new Routine();
        if (dto.getId() != null) routine.setId(dto.getId());
        if (dto.getDay() != null) routine.setDay(dto.getDay());
        if (dto.getStartTime() != null) routine.setStartTime(dto.getStartTime());
        if (dto.getEndTime() != null) routine.setEndTime(dto.getEndTime());
        if (dto.getPeriod() != null) routine.setPeriod(dto.getPeriod());
        if (dto.getBreakTime() != null) routine.setBreakTime(dto.getBreakTime());
        if (dto.getRoomNumber() != null) routine.setRoomNumber(dto.getRoomNumber());
        if (dto.getDurationInMinutes() != null) routine.setDurationInMinutes(dto.getDurationInMinutes());
        if (!dto.getSubjectIds().isEmpty()) {
            List<StudentSubject> subjects = subjectRepository.findAllById(dto.getSubjectIds());
            routine.setSubject(subjects);
        }
        if (!dto.getTeacherIds().isEmpty()) {
            List<Teacher> teachers = teacherRepository.findAllById(dto.getTeacherIds());
            routine.setTeacher(teachers);
        }
        if (dto.getClassId() != null) {
            classRepository.findById(dto.getClassId()).ifPresent(routine::setClassName);
        }
        if (dto.getSectionId() != null) {
            sectionRepository.findById(dto.getSectionId()).ifPresent(routine::setSection);
        }
        return routine;
    }
}
