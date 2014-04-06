package cz.upol.jasgweb.pages;

import java.util.LinkedList;

import cz.upol.jasg.jasgdb.data.assignments.Assignment;
import cz.upol.jasg.jasgdb.errors.DataAccessException;
import cz.upol.jasgweb.ResultReporter;

public class StudentsToSubmit extends StudentsDataView {

	private LinkedList<Assignment> asgsToSubmit;
	
	public StudentsToSubmit() {
		super();
		
		///asgsToSubmit = new LinkedList<Assignment>();
		doLoad();	
	}
	
	public LinkedList<Assignment> getAsgsToSubmit() {
		return asgsToSubmit;
	}

	public boolean doLoad() {
		try {
			asgsToSubmit = new LinkedList<Assignment>(getStudent().getAssignmentsSolutions(false, true).keySet());
			return true;
		} catch (DataAccessException e) {
			String message = "Nepodařilo se načíst seznam úkolů.";
			ResultReporter.reportDataAccessError(message, e);
			return false;
		}
	}
}
