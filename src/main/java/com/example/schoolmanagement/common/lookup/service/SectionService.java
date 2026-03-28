package com.example.schoolmanagement.common.lookup.service;

import com.example.schoolmanagement.common.lookup.dto.SectionDto;
import com.example.schoolmanagement.common.lookup.model.StudentSection;
import com.example.schoolmanagement.common.lookup.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public List<SectionDto> getAll() {
        List<StudentSection> sections = repository.findAll(Sort.by("sectionName"));
        return sections.stream().map(section -> mapToDto(section, new SectionDto())).toList();
    }

    public SectionDto getById(Long id) {
        SectionDto dto = new SectionDto();
        StudentSection section = repository.findById(id).get();
        mapToDto(section, dto);
        return dto;
    }

    public String saveData(SectionDto dto) {
        StudentSection section = new StudentSection();
        mapToEntity(section, dto);
        return repository.save(section).getSectionName();
    }

    public String updateData(Long id, SectionDto dto) {
        StudentSection section = repository.findById(id).orElseThrow(RuntimeException::new);
        mapToEntity(section, dto);
        return repository.save(section).getSectionName();
    }

    private StudentSection mapToEntity(StudentSection section, SectionDto dto) {
        if (dto.getId() != null) section.setId(dto.getId());
        if (dto.getSectionName() != null) section.setSectionName(dto.getSectionName());
        return section;
    }

    private SectionDto mapToDto(StudentSection section, SectionDto sectionDto) {
        if (section.getId() != null) sectionDto.setId(section.getId());
        if (section.getSectionName() != null) sectionDto.setSectionName(section.getSectionName());
        return sectionDto;
    }
}
