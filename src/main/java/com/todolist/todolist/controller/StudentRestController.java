package com.todolist.todolist.controller;

import com.todolist.todolist.dto.StudentDTO;
import com.todolist.todolist.model.Student;
import com.todolist.todolist.response.ResponseHandler;
import com.todolist.todolist.service.StudentService;
import com.todolist.todolist.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/students")
public class StudentRestController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private StudentService studentService;


    @GetMapping(value = "/")
    public ResponseEntity<Object> getAllStudents() {
        try {
            LOG.info("*********  getAllStudents() **********");
            List<StudentDTO> studentList = ObjectMapperUtils.mapAll(studentService.findAll(),StudentDTO.class);
            return ResponseHandler.generateResponse("student list retrieve successfully.", true,HttpStatus.OK, studentList);
        }catch (Exception exception){
            return ResponseHandler.generateResponse(exception.getMessage(), false,HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveOrUpdateStudent(@RequestBody StudentDTO studentDTO) {
        try {
            LOG.info("*********  saveOrUpdateStudent() **********");
            studentService.saveOrUpdateStudent(ObjectMapperUtils.map(studentDTO,Student.class));
            return ResponseHandler.generateResponse("Student save successfully.",true,HttpStatus.OK,studentDTO);
        }catch (Exception exception){
            return ResponseHandler.generateResponse(exception.getMessage(),false, HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping(value = "/by-student-number/{studentNumber}")
    public ResponseEntity<Object> getStudentByStudentNumber(@PathVariable long studentNumber){
        try{
            LOG.info("*********  getStudentByStudentNumber() **********");
            LOG.info("studentNumber:{}",studentNumber);
            StudentDTO student = ObjectMapperUtils.map(studentService.findByStudentNumber(studentNumber),StudentDTO.class);
            return ResponseHandler.generateResponse("get student by student number successfully.",true,HttpStatus.OK,student);
        }catch (Exception exception){
            return ResponseHandler.generateResponse(exception.getMessage(), false,HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping(value = "/by-email/{email}")
    public ResponseEntity<Object> getStudentByEmail(@PathVariable String email){
        try {
            LOG.info("*********  getStudentByEmail() **********");
            LOG.info("email:{}",email);
            StudentDTO student = ObjectMapperUtils.map(studentService.findByEmail(email),StudentDTO.class);
            return ResponseHandler.generateResponse("get student by email successfully.",true,HttpStatus.OK,student);
        }catch (Exception exception){
            return ResponseHandler.generateResponse(exception.getMessage(),false,HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping(value = "/order-by-gpa")
    public ResponseEntity<Object> getStudentByGPA(){
        try{
            LOG.info("*********  getStudentByGPA() **********");
            List<StudentDTO> studentsResult = ObjectMapperUtils.mapAll(studentService.findAllByOrderByGpaDesc(),StudentDTO.class);
            return ResponseHandler.generateResponse("get student order by gpa successfully.",true,HttpStatus.OK,studentsResult);
        }catch (Exception exception){
            return ResponseHandler.generateResponse(exception.getMessage(),false, HttpStatus.MULTI_STATUS, null);
        }

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteStudentByStudentNumber(@PathVariable String id) {
        try{
            LOG.info("*********  deleteStudentByStudentNumber() **********");
            LOG.info("id:{}",id);
            studentService.deleteStudentById(id);
            return ResponseHandler.generateResponse("Student deleted successfully",true,HttpStatus.OK,null);
        }catch (Exception exception){
            return ResponseHandler.generateResponse(exception.getMessage(),false, HttpStatus.MULTI_STATUS, null);
        }
    }

}
