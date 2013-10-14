package com.fidelis.k2.bo;

import java.util.List;

import com.fidelis.k2.entity.Student;
import com.fidelis.k2.model.StudentDto;
import com.fidelis.k2.model.TeacherDto;

public interface StudentBo {

	StudentDto savestudent(Student student);
	List<StudentDto> getAllStudents();
	StudentDto getStudentById(Integer studentId);
	TeacherDto addteacherforstudent(int studentId, int teacherId);
	TeacherDto removeteacher(int studentId, int teacherId);

}
