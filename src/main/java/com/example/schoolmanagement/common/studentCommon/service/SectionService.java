package com.example.schoolmanagement.common.studentCommon.service;

import com.example.schoolmanagement.common.studentCommon.dto.SectionDto;
import com.example.schoolmanagement.common.studentCommon.model.StudentSection;
import com.example.schoolmanagement.common.studentCommon.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public List<SectionDto> getAll(){
        List<StudentSection> sections = repository.findAll();
        return sections.stream().map(section -> mapToDto(section, new SectionDto())).toList();
    }

    public SectionDto getById(Long id){
        SectionDto dto = new SectionDto();
        StudentSection section = repository.findById(id).get();
        mapToDto(section, dto);
        return dto;
    }

    public String saveData(SectionDto dto){
        StudentSection section = new StudentSection();
        mapToEntity(section, dto);
        return repository.save(section).getSectionName();
    }

    public String updateData(Long id, SectionDto dto){
        StudentSection section = repository.findById(id).orElseThrow(RuntimeException::new);
        mapToEntity(section, dto);
        return repository.save(section).getSectionName();
    }

    private StudentSection mapToEntity(StudentSection section, SectionDto dto) {
        if (dto.getSectionName() != null) section.setSectionName(dto.getSectionName());
        return section;
    }

    private SectionDto mapToDto(StudentSection section, SectionDto sectionDto) {
        if (section.getSectionName() != null) sectionDto.setSectionName(section.getSectionName());
        return sectionDto;
    }
}
