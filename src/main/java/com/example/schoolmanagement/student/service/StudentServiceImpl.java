package com.example.schoolmanagement.student.service;

import com.example.schoolmanagement.common.lookup.model.StudentSubject;
import com.example.schoolmanagement.common.lookup.repository.ClassRepository;
import com.example.schoolmanagement.common.lookup.repository.SectionRepository;
import com.example.schoolmanagement.common.lookup.repository.SubjectRepository;
import com.example.schoolmanagement.common.user.service.UserService;
import com.example.schoolmanagement.student.dto.StudentDto;
import com.example.schoolmanagement.student.model.EmergencyContact;
import com.example.schoolmanagement.student.model.Student;
import com.example.schoolmanagement.student.repository.EmergencyContactRepository;
import com.example.schoolmanagement.student.repository.StudentRepository;
import com.example.schoolmanagement.student.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository repository;

    private final SectionRepository sectionRepository;

    private final SubjectRepository subjectRepository;

    private final ClassRepository classRepository;

    private final TeacherRepository teacherRepository;

    private final EmergencyContactRepository emergencyContactRepository;

    private UserService userService;

    private StudentServiceImpl(StudentRepository repository,
                               SectionRepository sectionRepository,
                               SubjectRepository subjectRepository,
                               ClassRepository classRepository,
                               TeacherRepository teacherRepository,
                               EmergencyContactRepository emergencyContactRepository,
                               UserService userService) {
        this.repository = repository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.classRepository = classRepository;
        this.teacherRepository = teacherRepository;
        this.emergencyContactRepository = emergencyContactRepository;
        this.userService = userService;
    }

    @Override
    public List<StudentDto> getAll() {
        List<Student> students = repository.findByIsActiveTrue(true);
        return students.stream().map(this::mapToDto)
                .toList();
    }

    @Override
    public StudentDto getById(Long id) {
        Student student = repository.findById(id).orElseThrow(RuntimeException::new);
        return student != null ? mapToDto(student) : new StudentDto();
    }

    @Override
    public StudentDto getByEmail(String email) {
        Student student = repository.findByEmail(email);
        if (student != null) {
            return mapToDto(student);
        } else {
            throw new RuntimeException("Student not found with email: " + email);
        }
    }

    @Override
    public String saveData(StudentDto dto) {
        Student student = repository.findByEmail(dto.getEmail());
        Boolean hasUser = userService.emailExists(dto.getEmail());
        if (!hasUser) {
            throw new RuntimeException("User not found with email: " + dto.getEmail());
        }
        if (student != null) {
            dto.setId(student.getId());
        }
        student = mapToEntity(dto);
        repository.save(student);
        return student.getName();
    }

    @Override
    public String updateData(Long id, StudentDto dto) {
        Student student = repository.findById(id).orElseThrow(RuntimeException::new);
        if (student == null) {
            throw new RuntimeException("Student not found with id: " + id);
        } else {
            student = mapToEntity(dto);
            student.setId(id);
            repository.save(student);
            return student.getName();
        }
    }

    @Override
    public void deleteData(Long id) {
        Student student = repository.findById(id).orElseThrow(RuntimeException::new);
        if (student != null) {
            student.setActive(false);
            repository.save(student);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }

    private StudentDto mapToDto(Student student) {
        StudentDto dto = new StudentDto();
        if (student.getId() != null) dto.setId(student.getId());
        if (student.getName() != null) dto.setName(student.getName());
        if (student.getFatherName() != null) dto.setFatherName(student.getFatherName());
        if (student.getMotherName() != null) dto.setMotherName(student.getMotherName());
        if (student.getEmail() != null) dto.setEmail(student.getEmail());
        if (student.getPhoneNumber() != null) dto.setPhoneNumber(student.getPhoneNumber());
        if (student.getAddress() != null) dto.setAddress(student.getAddress());
        if (student.getDateOfBirth() != null) dto.setDateOfBirth(student.getDateOfBirth());
        if (student.getStudentClass() != null) dto.setClassId(student.getStudentClass().getId());
        if (student.getSection() != null) dto.setSectionId(student.getSection().getId());
        if (student.getSubjects() != null) {
            List<Long> subjects = student.getSubjects().stream()
                    .map(subject -> subject.getId())
                    .toList();
            dto.setSubjects(subjects);
        }
        if (student.getRollNumber() != null) dto.setRollNumber(student.getRollNumber());
        if (student.getTeacher() != null) dto.setTeacherId(student.getTeacher().getId());
        if (student.getEmergencyContacts() != null) {
            List<Long> emergencyContacts = student.getEmergencyContacts().stream()
                    .map(emergencyContact -> emergencyContact.getId())
                    .toList();
            dto.setEmergencyContacts(emergencyContacts);
        }
        if (student.getFileB64() != null) dto.setFileB64(student.getFileB64());
        if (student.getFileName() != null) dto.setFileName(student.getFileName());
        return dto;
    }

    private Student mapToEntity(StudentDto dto) {
        Student student = new Student();
        if (dto.getId() != null) student.setId(dto.getId());
        if (dto.getName() != null) student.setName(dto.getName());
        if (dto.getFatherName() != null) student.setFatherName(dto.getFatherName());
        if (dto.getMotherName() != null) student.setMotherName(dto.getMotherName());
        if (dto.getEmail() != null) student.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) student.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddress() != null) student.setAddress(dto.getAddress());
        if (dto.getDateOfBirth() != null) student.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getClassId() != null) student.setStudentClass(classRepository.findById(dto.getClassId()).get());
        if (dto.getSectionId() != null) student.setSection(sectionRepository.findById(dto.getSectionId()).get());
        if (dto.getSubjects() != null) {
            List<StudentSubject> subjects = dto.getSubjects().stream()
                    .map(subjectId -> {
                        StudentSubject subject = subjectRepository.findById(subjectId).get();
                        return subject;
                    })
                    .toList();
            student.setSubjects(subjects);
        }
        if (dto.getRollNumber() != null) student.setRollNumber(dto.getRollNumber());
        if (dto.getTeacherId() != null) student.setTeacher(teacherRepository.getById(dto.getTeacherId()));
        if (dto.getEmergencyContacts() != null) {
            List<EmergencyContact> emergencyContacts = dto.getEmergencyContacts().stream()
                    .map(emergencyContact -> emergencyContactRepository.findById(emergencyContact).get())
                    .toList();
            student.setEmergencyContacts(emergencyContacts);
        }
        if (dto.getFileB64() != null) student.setFileB64(dto.getFileB64());
        if (dto.getFileName() != null) student.setFileName(dto.getFileName());
        return student;
    }

}
