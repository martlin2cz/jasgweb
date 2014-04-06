package cz.upol.jasgweb.pages;

import java.util.LinkedList;

import cz.upol.jasg.jasgdb.data.assignments.Assignment;
import cz.upol.jasg.jasgdb.data.students.Student;
import cz.upol.jasg.jasgdb.errors.DataAccessException;
import cz.upol.jasgweb.ResultReporter;
import cz.upol.jasgweb.login.Login;

public class StudentsSummary {

	private Student student;
	private LinkedList<Assignment> asgsToSubmit;
	
	public StudentsSummary() {
		doLoad();	//TODO TOREM
	}
	
	public LinkedList<Assignment> getAsgsToSubmit() {
		return asgsToSubmit;
	}

	public void doLoad() {
		student = Login.getLogin().getLoggedStudent();
		try {
			asgsToSubmit = new LinkedList<Assignment>(student.getAssignmentsSolutions(false, true).keySet());
		} catch (DataAccessException e) {
			String summary = "Chyba při načítání dat";
			String detailedMessage = "Nepodařilo se načíst seznam úkolů";
			ResultReporter.reportError(summary, detailedMessage, e);
		}
	}
	

	
	public String load() {
		doLoad();

		return "success";
	}


}
