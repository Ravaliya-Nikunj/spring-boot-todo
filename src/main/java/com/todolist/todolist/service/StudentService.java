package com.todolist.todolist.service;

import com.todolist.todolist.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findByStudentNumber(long studentNumber);

    Student findByEmail(String email);

    List<Student> findAllByOrderByGpaDesc();

    Student saveOrUpdateStudent(Student student);

    void deleteStudentById(String id);

}
