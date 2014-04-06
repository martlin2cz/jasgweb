package cz.upol.jasgweb.pages;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import cz.upol.jasg.jasgdb.data.assignments.Assignment;
import cz.upol.jasg.jasgdb.data.solutions.Solution;
import cz.upol.jasg.jasgdb.errors.DataAccessException;
import cz.upol.jasgweb.ResultReporter;

public class StudentsDoneAsgs extends StudentsDataView {
	private LinkedHashMap<Assignment, List<Solution>> assignments;

	public StudentsDoneAsgs() {
		super();
		///assignments = new LinkedHashMap<Assignment, List<Solution>>();
		doLoad();
	}

	public LinkedHashMap<Assignment, List<Solution>> getAsgsAndSltns() {
		return assignments;
	}

	public LinkedList<Assignment> getAssignments() {
		return new LinkedList<Assignment>(assignments.keySet());

	}

	@Override
	protected boolean doLoad() {
		try {
			assignments = getStudent().getAssignmentsSolutions(true, true);
			return true;
		} catch (DataAccessException e) {
			String message = "Nepodařilo se načíst seznam odeslaných úkolů.";
			ResultReporter.reportDataAccessError(message, e);
			return false;
		}
	}

}
