package com.Springwebmvcform.StudentService;

import com.Springwebmvcform.StudentEntity.Student;

import java.util.List;

public interface StudentSer {
    List<Student> getAllStudent();
    boolean saveStudent(Student student);
    Student getStudentById(long id);
    public void deleteStudentById(long id);
}
