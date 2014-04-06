package cz.upol.jasgweb.pages;

import cz.upol.jasg.jasgdb.data.students.Student;
import cz.upol.jasgweb.ResultReporter;
import cz.upol.jasgweb.login.Login;

public abstract class StudentsDataView {

	private Student student;

	public StudentsDataView() {
		super();
		loadStudent();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	protected void loadStudent() {
		student = Login.getLogin().getLoggedStudent();

		if (student == null) {
			student = new Student();
		}
	}

	public String load() {
		loadStudent();

		boolean success = doLoad();

		if (success)
			return ResultReporter.SUCCESS;
		else
			return ResultReporter.UNSUCCESS;
	}

	protected abstract boolean doLoad();

}