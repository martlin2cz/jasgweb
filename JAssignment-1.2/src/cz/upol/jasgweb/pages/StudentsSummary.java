package cz.upol.jasgweb.pages;

import java.util.List;

import cz.upol.jasg.jasgdb.data.assignments.Assignment;
import cz.upol.jasg.jasgdb.errors.DataAccessException;
import cz.upol.jasgweb.ResultReporter;

public class StudentsSummary extends StudentsDataView {

	public StudentsSummary() {
		super();
		///
		doLoad();
	}

	public boolean isHasCredit() {
		return getStudent().isHasCredit();
	}

	public boolean isHasAllAssignmentsOK() {
		try {
			return getStudent().hasActuallyAllOK();
		} catch (DataAccessException e) {
			ResultReporter
					.reportDataAccessError(
							"Nepodařilo se zjistit, zda máte všechny úkoly odeslány",
							e);
			return false;
		}
	}

	public boolean isWaitingForRevision() {
		// TODO
		return false;
	}

	public boolean isHasAsignmentToCoCorect() {
		// TODO
		return false;
	}

	public boolean isHasAssignmentToSubmit() {
		List<Assignment> assignments = null;

		try {
			assignments = getStudent().getUnsubmitedSolutionsAssignments(false,
					false);
		} catch (DataAccessException e) {
			ResultReporter.reportDataAccessError(
					"Nepodařilo se zjistit, zda máte úkoly k odeslání", e);
			return false;
		}

		return assignments.isEmpty();
	}

	public boolean isHasAssignmentToSubmitLate() {
		List<Assignment> assignments = null;

		try {
			assignments = getStudent().getUnsubmitedSolutionsAssignments(false,
					false);
			// TODO ty, co už jsou pozdě (ne všechny)
		} catch (DataAccessException e) {
			ResultReporter.reportDataAccessError(
					"Nepodařilo se zjistit, zda máte úkoly k odeslání", e);
			return false;
		}

		return assignments.isEmpty();
	}

	public boolean isUnactive() {
		return !getStudent().isActive();
	}

	@Override
	public boolean doLoad() {
		//TODO ?
		return true;
	}
	

}
