package com.example.schoolmanagement.common.studentCommon.service;

import com.example.schoolmanagement.common.studentCommon.dto.SubjectDto;
import com.example.schoolmanagement.common.studentCommon.model.StudentSubject;
import com.example.schoolmanagement.common.studentCommon.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository repository;

    public List<SubjectDto> getAll(){
        List<StudentSubject> subjects = repository.findAll(Sort.by("subjectName"));
        return subjects.stream().map(subject -> mapToDto(subject, new SubjectDto())).toList();
    }

    public SubjectDto getById(Long id){
        SubjectDto dto = new SubjectDto();
        StudentSubject subject = repository.findById(id).get();
        mapToDto(subject, dto);
        return dto;
    }

    public String saveData(SubjectDto dto){
        StudentSubject subject = new StudentSubject();
        mapToEntity(subject, dto);
        return repository.save(subject).getSubjectName();
    }

    public String updateData(Long id, SubjectDto dto){
        StudentSubject subject = repository.findById(id).orElseThrow(RuntimeException::new);
        mapToEntity(subject, dto);
        return repository.save(subject).getSubjectName();
    }

    private StudentSubject mapToEntity(StudentSubject subject, SubjectDto dto) {
        if (dto.getSubjectName() != null) subject.setSubjectName(dto.getSubjectName());
        if (dto.getSubjectCode() != null) subject.setSubjectCode(dto.getSubjectCode());
        return subject;
    }

    private SubjectDto mapToDto(StudentSubject subject, SubjectDto dto) {
        if (subject.getSubjectName() != null) dto.setSubjectName(subject.getSubjectName());
        if (subject.getSubjectCode() != null) dto.setSubjectCode(subject.getSubjectCode());
        return dto;
    }
}
