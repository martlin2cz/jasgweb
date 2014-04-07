package cz.upol.jasgweb.submitSolution;

import javax.servlet.http.Part;

import cz.upol.jasg.jasgdb.data.assignments.Assignment;

public class SubmitSolutionForm {
	private Part sources;
	private Assignment assignment;
	
	public SubmitSolutionForm() {
		super();
	}

	public Part getSources() {
		return sources;
	}

	public void setSources(Part sources) {
		this.sources = sources;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	
	//TODO TTTOOODODOOOOOTOODODOTOOOOTOTTTTOOODOOTOTOTODODODODOOOO

}
