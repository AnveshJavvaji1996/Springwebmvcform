package com.Springwebmvcform.StudentService;

import com.Springwebmvcform.StudentEntity.Student;
import com.Springwebmvcform.StudentRepo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService implements StudentSer{
    @Autowired
    private StudentRepo studentRepo;

    //this method is used to get the list of students.
    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    //this method is used to save the student records.
    @Override
    public boolean saveStudent(Student student) {
        student = studentRepo.save(student);
        return student.getId() > 0;
    }


    //this method is used to get the student records using student id
    @Override
    public Student getStudentById(long id) {
        Optional<Student> optional = studentRepo.findById(id);
        Student student = null;
        if(optional.isPresent()){
            student = optional.get();
        }else {
            throw new RuntimeException("Student details not found for id: "+id);
        }
        return student;


    }

    //this method is used to delete the student records using student id
    @Override
    public void deleteStudentById(long id) {
        this.studentRepo.deleteById(id);
    }
}
