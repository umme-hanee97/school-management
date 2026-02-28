package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import com.example.schoolmanagement.common.lookup.repository.SubjectRepository;
import com.example.schoolmanagement.student.dto.TeacherDto;
import com.example.schoolmanagement.student.model.Teacher;
import com.example.schoolmanagement.student.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository repository;
    @Autowired
    private SubjectRepository subjectRepository;

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
        Teacher teacher = mapToEntity(dto);
        return repository.save(teacher).getName();
    }

    @Override
    public String updateData(Long id, TeacherDto dto) {
        Teacher teacher = repository.findById(id).orElseThrow(RuntimeException::new);
        if (teacher != null) {
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

    private TeacherDto mapToDto(Teacher teacher){
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setEmail(teacher.getEmail());
        dto.setPhoneNumber(teacher.getPhoneNumber());
        dto.setAddress(teacher.getAddress());
        dto.setDateOfBirth(teacher.getDateOfBirth().toString());
        List<String> subjects = teacher.getSubjects().stream()
                .map(subject -> subject.getSubjectName())
                .toList();
        dto.setSubjectNames(subjects);
        return dto;
    }

    private Teacher mapToEntity(TeacherDto dto){
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setPhoneNumber(dto.getPhoneNumber());
        teacher.setAddress(dto.getAddress());
        List<StudentSubject> subjects = dto.getSubjectIds().stream()
                .map(subjectId -> {
                    StudentSubject subject = subjectRepository.findById(subjectId).get();
//                    subject.setId(subjectId);
                    return subject;
                })
                .toList();
        teacher.setSubjects(subjects);
        return teacher;
    }

}
