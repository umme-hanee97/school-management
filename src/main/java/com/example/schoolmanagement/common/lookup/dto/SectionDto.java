package com.example.schoolmanagement.common.lookup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class SectionDto {
    private Long id;
    private String sectionName;
}
