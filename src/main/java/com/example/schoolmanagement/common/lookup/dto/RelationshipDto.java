package com.example.schoolmanagement.common.lookup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RelationshipDto {
    private Long id;
    private String relationship;
}
