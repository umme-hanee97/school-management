package com.example.schoolmanagement.common.lookup.service;

import com.example.schoolmanagement.common.lookup.dto.ClassDto;
import com.example.schoolmanagement.common.lookup.model.StudentClass;
import com.example.schoolmanagement.common.lookup.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository repository;
    
    public List<ClassDto> getAll(){
        List<StudentClass> classes = repository.findAll(Sort.by("id"));
        return classes.stream().map(studentClass -> mapToDto(studentClass, new ClassDto())).toList();
    }
    
    public ClassDto getById(Long id){
        ClassDto dto = new ClassDto();
        StudentClass studentClass = repository.findById(id).get();
        mapToDto(studentClass, dto);
        return dto;
    }
    
    public String saveData(ClassDto dto){
        StudentClass studentClass = new StudentClass();
        mapToEntity(studentClass, dto);
        return repository.save(studentClass).getClassName();
    }

    public String updateData(Long id, ClassDto dto){
        StudentClass studentClass = repository.findById(id).orElseThrow(RuntimeException::new);
        mapToEntity(studentClass, dto);
        return repository.save(studentClass).getClassName();
    }

    private StudentClass mapToEntity(StudentClass studentClass, ClassDto dto) {
        if (dto.getClassName() != null) studentClass.setClassName(dto.getClassName());
        if (dto.getClassNameCode() != null) studentClass.setClassNameCode(dto.getClassNameCode());
        return studentClass;
    }

    private ClassDto mapToDto(StudentClass studentClass, ClassDto classDto) {
        if (studentClass.getClassName() != null) classDto.setClassName(studentClass.getClassName());
        if (studentClass.getClassNameCode() != null) classDto.setClassNameCode(studentClass.getClassNameCode());
        return classDto;
    }
}
