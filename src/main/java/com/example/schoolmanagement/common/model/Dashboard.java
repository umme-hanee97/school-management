package com.example.schoolmanagement.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Dashboard {
    private Integer studentCount;
    private Integer teacherCount;
    private Integer classCount;


}
