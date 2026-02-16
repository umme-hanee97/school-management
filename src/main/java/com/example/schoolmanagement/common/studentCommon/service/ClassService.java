package com.example.schoolmanagement.common.studentCommon.service;

import com.example.schoolmanagement.common.studentCommon.dto.ClassDto;
import com.example.schoolmanagement.common.studentCommon.model.StudentClass;
import com.example.schoolmanagement.common.studentCommon.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassService {
    @Autowired
    private ClassRepository repository;
    
    public List<ClassDto> getAll(){
        List<StudentClass> classes = repository.findAll(Sort.by("className"));
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
        if (dto.getClassNameWord() != null) studentClass.setClassNameWord(dto.getClassNameWord());
        return studentClass;
    }

    private ClassDto mapToDto(StudentClass studentClass, ClassDto classDto) {
        if (studentClass.getClassName() != null) classDto.setClassName(studentClass.getClassName());
        if (studentClass.getClassNameWord() != null) classDto.setClassNameWord(studentClass.getClassNameWord());
        return classDto;
    }
}
