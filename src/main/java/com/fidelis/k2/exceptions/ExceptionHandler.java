/*package com.fidelis.k2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionHandler {
	@ExceptionHandler(TeacherValidationException.class)
    ResponseEntity<String> test() {
		System.out.println("exception handler called");
        return new ResponseEntity<String>(
                "We are sorry, The Teacher Canot be saved.",
                HttpStatus.I_AM_A_TEAPOT  );
    }

}*/
/*@Transactional
	private void executequery() {
//		String query="SELECT s FROM Student s INNER JOIN FETCH s.teachers t  INNER JOIN FETCH t.students sts WHERE  s.id=1";
		//String query="SELECT t FROM Student s JOIN s.teachers t  INNER JOIN FETCH t.students sts WHERE s.id=1 AND sts.id=s.id";//JOIN  t.students sts WHERE s.id=1";
		//String query="SELECT t from Student s JOIN s.teachers t GROUP BY t.id";
		//String query="SELECT t from Student s JOIN s.teachers t GROUP BY t.id HAVING COUNT(*) >1";
		//String query="SELECT t from Student s JOIN s.teachers t JOIN t.students sts GROUP BY t.id HAVING COUNT(sts) <7";
		//List<Student> students=em.createNamedQuery("Student.FindAll").getResultList();
		List<Student> students=em.createNamedQuery("Student.FindById").setParameter("studentId", 1).getResultList();
		for(Student student:students){
			System.out.println("name ="+student.getName()+"Email="+student.getEmail());//+"Student count:"+teacher.getStudents().size());
		}
		
	}*/