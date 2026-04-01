package com.example.schoolmanagement.common.service;

import com.example.schoolmanagement.common.lookup.repository.ClassRepository;
import com.example.schoolmanagement.common.model.Dashboard;
import com.example.schoolmanagement.student.repository.StudentRepository;
import com.example.schoolmanagement.student.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public Dashboard countStats(){
        Dashboard dashboard = new Dashboard();
        dashboard.setStudentCount(studentRepository.countStudent());
        dashboard.setTeacherCount(teacherRepository.countTeacher());
        dashboard.setClassCount(classRepository.countClassWithSection());
        return dashboard;
    }
}
