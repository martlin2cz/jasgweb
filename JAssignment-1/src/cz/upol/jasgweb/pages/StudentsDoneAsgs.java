package cz.upol.jasgweb.pages;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import cz.upol.jasg.jasgdb.data.assignments.Assignment;
import cz.upol.jasg.jasgdb.data.solutions.Solution;
import cz.upol.jasg.jasgdb.data.students.Student;
import cz.upol.jasg.jasgdb.errors.DataAccessException;
import cz.upol.jasgweb.ResultReporter;
import cz.upol.jasgweb.login.Login;

public class StudentsDoneAsgs {
	private Student student;
	private LinkedHashMap<Assignment, List<Solution>> assignments;

	public StudentsDoneAsgs() {
		student = Login.getLogin().getLoggedStudent();

		doLoad();		//TODO TOREM
	}

	public LinkedHashMap<Assignment, List<Solution>> getAsgsAndSltns() {
		return assignments;
	}

	public LinkedList<Assignment> getAssignments() {
		return new LinkedList<Assignment>(assignments.keySet());

	}

	private void doLoad() {
		try {
			assignments = student.getAssignmentsSolutions(true, true);
		} catch (DataAccessException e) {
			String summary = "Nenačtena data";
			String detailedMessage = "Nepodařilo se načíst seznam odeslaných úkolů.";
			ResultReporter.reportError(summary, detailedMessage, e);
		}
	}

	public String load() {
		doLoad();

		return "success";
	}

}
