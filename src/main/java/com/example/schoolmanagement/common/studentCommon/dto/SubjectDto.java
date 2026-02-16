package com.example.schoolmanagement.common.studentCommon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {
    private Long id;
    private String subjectName;
    private String subjectCode;
}
