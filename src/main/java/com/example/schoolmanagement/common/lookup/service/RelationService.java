package com.example.schoolmanagement.common.lookup.service;

import com.example.schoolmanagement.common.lookup.dto.RelationshipDto;
import com.example.schoolmanagement.common.lookup.model.Relationship;
import com.example.schoolmanagement.common.lookup.repository.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelationService {


    private final RelationRepository repository;

    public List<RelationshipDto> getAll() {
        List<Relationship> relationships = repository.findAll(Sort.by("relationship"));
        return relationships.stream().map(section -> mapToDto(section, new RelationshipDto())).toList();
    }

    public RelationshipDto getById(Long id) {
        RelationshipDto dto = new RelationshipDto();
        Relationship relationship = repository.findById(id).get();
        mapToDto(relationship, dto);
        return dto;
    }

    public String saveData(RelationshipDto dto) {
        Relationship relationship = new Relationship();
        mapToEntity(relationship, dto);
        return repository.save(relationship).getRelationship();
    }

    public String updateData(Long id, RelationshipDto dto) {
        Relationship relationship = repository.findById(id).orElseThrow(RuntimeException::new);
        mapToEntity(relationship, dto);
        return repository.save(relationship).getRelationship();
    }

    private Relationship mapToEntity(Relationship relationship, RelationshipDto dto) {
        if (dto.getId() != null) relationship.setId(dto.getId());
        if (dto.getRelationship() != null) relationship.setRelationship(dto.getRelationship());
        return relationship;
    }

    private RelationshipDto mapToDto(Relationship relationship, RelationshipDto dto) {
        if (relationship.getId() != null) dto.setId(relationship.getId());
        if (relationship.getRelationship() != null) dto.setRelationship(relationship.getRelationship());
        return dto;
    }
}
