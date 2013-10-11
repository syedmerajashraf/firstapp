package com.fidelis.k2.bo;

import java.util.List;

import com.fidelis.k2.entity.Student;
import com.fidelis.k2.model.StudentDto;

public interface StudentBo {

	StudentDto savestudent(Student student);
	List<StudentDto> getAllStudents();
	StudentDto getStudentById(Integer studentId);

}
