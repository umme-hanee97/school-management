package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import com.example.schoolmanagement.common.lookup.repository.SubjectRepository;
import com.example.schoolmanagement.student.dto.TeacherDto;
import com.example.schoolmanagement.student.model.Teacher;
import com.example.schoolmanagement.student.repository.TeacherRepository;
import com.example.schoolmanagement.common.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {


    private TeacherRepository repository;

    private SubjectRepository subjectRepository;

    private UserService userService;

    private TeacherServiceImpl(TeacherRepository repository,
                               SubjectRepository subjectRepository,
                               UserService userService) {
        this.repository = repository;
        this.subjectRepository = subjectRepository;
        this.userService = userService;
    }

    @Override
    public List<TeacherDto> getAll() {
        List<Teacher> teachers = repository.findAllByIsActive(true);
        return teachers.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public TeacherDto getById(Long id) {
        Teacher teacher = repository.findById(id).orElseThrow(RuntimeException::new);
        return teacher != null ? mapToDto(teacher) : new TeacherDto();
    }

    @Override
    public String saveData(TeacherDto dto) {
        Teacher teacher = repository.findByEmail(dto.getEmail());
        Boolean hasUser = userService.emailExists(dto.getEmail());
        if (!hasUser) {
            throw new RuntimeException("User not found with email: " + dto.getEmail());
        }
        if (teacher != null) {
            dto.setId(teacher.getId());
        }
        teacher = mapToEntity(dto);
        return repository.save(teacher).getName();
    }

    @Override
    public String updateData(Long id, TeacherDto dto) {
        Teacher teacher = repository.findById(id).orElseThrow(RuntimeException::new);
        if (teacher == null) {
            throw new RuntimeException("Teacher not found with id: " + id);
        } else {
            teacher = mapToEntity(dto);
            teacher.setId(id);
            return repository.save(teacher).getName();
        }
    }

    @Override
    public void deleteData(Long id) {
        Teacher teacher = repository.findById(id).orElseThrow(RuntimeException::new);
        if (teacher != null) {
            teacher.setActive(false);
            repository.save(teacher);
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }

    private TeacherDto mapToDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        if (teacher.getId() != null) dto.setId(teacher.getId());
        if (teacher.getName() != null) dto.setName(teacher.getName());
        if (teacher.getEmail() != null) dto.setEmail(teacher.getEmail());
        if (teacher.getPhoneNumber() != null) dto.setPhoneNumber(teacher.getPhoneNumber());
        if (teacher.getAddress() != null) dto.setAddress(teacher.getAddress());
        if (teacher.getDateOfBirth() != null) dto.setDateOfBirth(teacher.getDateOfBirth());
        if (teacher.getSubjects() != null) {
            List<String> subjects = teacher.getSubjects().stream()
                    .map(subject -> subject.getSubjectName())
                    .toList();
            dto.setSubjectNames(subjects);
        }
        if (teacher.getFileB64() != null) dto.setFileB64(teacher.getFileB64());
        if (teacher.getFileName() != null) dto.setFileName(teacher.getFileName());
        return dto;
    }

    private Teacher mapToEntity(TeacherDto dto) {
        Teacher teacher = new Teacher();
        if (dto.getId() != null) teacher.setId(dto.getId());
        if (dto.getName() != null) teacher.setName(dto.getName());
        if (dto.getEmail() != null) teacher.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) teacher.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddress() != null) teacher.setAddress(dto.getAddress());
        if (dto.getDateOfBirth() != null) teacher.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getSubjectIds() != null) {
            List<StudentSubject> subjects = dto.getSubjectIds().stream()
                    .map(subjectId -> {
                        StudentSubject subject = subjectRepository.findById(subjectId).get();
//                    subject.setId(subjectId);
                        return subject;
                    })
                    .toList();
            teacher.setSubjects(subjects);
        }
        if (dto.getFileB64() != null) teacher.setFileB64(dto.getFileB64());
        if (dto.getFileName() != null) teacher.setFileName(dto.getFileName());
        return teacher;
    }

}
