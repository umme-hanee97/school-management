package com.example.schoolmanagement.student.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class EmergencyContactDto {
    private Long id;
    private List<Long> studentId;
    private String name;
    private Long relationshipId;
    private String relationshipName;
    private String phoneNumber;
    private String email;
}
