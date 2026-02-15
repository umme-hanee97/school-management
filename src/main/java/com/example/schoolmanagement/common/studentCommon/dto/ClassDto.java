package com.example.schoolmanagement.common.studentCommon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ClassDto {
    private Long id;
    private String className;
}
