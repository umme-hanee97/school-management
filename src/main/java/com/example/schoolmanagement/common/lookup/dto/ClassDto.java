package com.example.schoolmanagement.common.lookup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ClassDto {
    private Long id;
    private String className;
    private String classNameCode;
    private List<SectionDto> sections;
}
