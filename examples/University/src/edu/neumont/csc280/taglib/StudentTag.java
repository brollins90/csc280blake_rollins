package edu.neumont.csc280.taglib;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import edu.neumont.csc280.univ.model.Student;


public class StudentTag extends TagSupport {
    private Student student;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter w = this.pageContext.getOut();
		try {
			w.println("<tr>");
			w.print("<td><a href=\"");
			w.print(((HttpServletRequest)this.pageContext.getRequest()).getContextPath());
			w.print("/app/student/");
			w.print(String.valueOf(student.getId()));
			w.print("\">");
			w.print(student.getFirstName());
			w.print(" ");
			w.print(student.getLastName());
			w.print("</a></td>");
			w.newLine();
			w.print("<td><a href=\"");
			w.print(((HttpServletRequest)this.pageContext.getRequest()).getContextPath());
			w.print("/app/student/");
			w.print(student.getId());
			w.println("/update\">Edit</a></td>");
			w.println("</tr>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
