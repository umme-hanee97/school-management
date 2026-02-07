package com.example.schoolmanagement.role.service;

import com.example.schoolmanagement.role.dto.RolesDto;
import com.example.schoolmanagement.role.model.Roles;
import com.example.schoolmanagement.role.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesRepository repository;

    public List<RolesDto> findAll(){
        List<Roles> roles = repository.findAll(Sort.by("name"));
        return roles.stream().map((role) -> mapToDto(role, new RolesDto())).toList();
    }

    public RolesDto getByRoleName(String roleName){
        RolesDto rolesDto = repository.findById(roleName).map((roles -> mapToDto(roles, new RolesDto()))).orElseThrow(RuntimeException::new);
        return rolesDto;
    }

    public String createNew(RolesDto rolesDto){
        Roles role = new Roles();
        mapToEntity(rolesDto, role);
        role.setName(rolesDto.getName());
        return repository.save(role).getName();
    }

    public String updateData(String roleName, RolesDto rolesDto){
        Roles role = repository.findById(roleName).orElseThrow(RuntimeException::new);
        mapToEntity(rolesDto,role);
        return repository.save(role).getName();
    }

    public void deleteData(String roleName){
//        Roles role = repository.findById(roleName).orElseThrow(RuntimeException::new);
//        role.setIsActive(false);
        repository.deleteById(roleName);
    }

    public boolean roleNameExists(String roleName){
        return repository.existsByNameIgnoreCase(roleName);
    }

    private RolesDto mapToDto(Roles model, RolesDto dto){
        dto.setName(model.getName());
        dto.setDescription(model.getDescription());
        return dto;
    }

    private Roles mapToEntity(RolesDto dto, Roles role){
        role.setDescription(dto.getDescription());
        return role;
    }
}
