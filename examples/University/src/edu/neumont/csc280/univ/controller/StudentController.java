package edu.neumont.csc280.univ.controller;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import edu.neumont.csc280.framework.Controller;
import edu.neumont.csc280.framework.ModelAndView;
import edu.neumont.csc280.framework.UrlMapping;
import edu.neumont.csc280.univ.model.Student;
import edu.neumont.csc280.univ.service.StudentService;

/**
 * Student CRUD.  Note that with the introduction of the method parameter in UrlMapping,
 * there is only one controller per resource (e.g. we'd have another one for courses, etc.)
 * 
 * @author jcummings
 *
 */
@Stateless
@LocalBean
public class StudentController implements Controller {
	@Inject StudentService studentService;
	@Inject HttpServletRequest request;
	
	@UrlMapping(value="/student/list", method="GET")
	public ModelAndView prepareStudentList() {
		List<Student> list = studentService.findAll();
		return new ModelAndView(list, "/WEB-INF/student/list.jsp");
	}
	
	@UrlMapping(value="/student/(\\d+)/update", method="GET")
	public ModelAndView prepareUpdateStudent(Long id) {
		Student s = studentService.findById(id);
		
		return new ModelAndView(s, "/WEB-INF/student/edit.jsp");
	}
	
	@UrlMapping(value="/student/(\\d+)", method="GET")
	public ModelAndView prepareGetStudent(Long id) {
		Student s = studentService.findById(id);
		return new ModelAndView(s, "/WEB-INF/student/view.jsp");
	}
	
	@UrlMapping(value="/student", method="GET")
	public ModelAndView prepareCreateStudent() {
		return new ModelAndView(null, "/WEB-INF/student/edit.jsp");
	}
	
	@UrlMapping(value="/student/(\\d+)", method="POST")
	public ModelAndView executeUpdateStudent(Long id) {
		Student s = studentService.findById(id);
		s.setFirstName(request.getParameter("firstName"));
		s.setLastName(request.getParameter("lastName"));
		
		return new ModelAndView(s, "/WEB-INF/student/edit.jsp");
	}
	
	@UrlMapping(value="/student", method="POST")
	public ModelAndView executeCreateStudent() {
		Student s = new Student();
		s.setFirstName(request.getParameter("firstName"));
		s.setLastName(request.getParameter("lastName"));
		studentService.updateStudent(s);
		
		return new ModelAndView(s, request.getContextPath() + "/app/student/" + s.getId(), true);
	}
}
