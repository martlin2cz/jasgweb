package cz.upol.jasgweb.login;

import cz.upol.jasg.jasgdb.data.students.Student;
import cz.upol.jasg.jasgdb.errors.DataAccessException;
import cz.upol.jasgweb.ResultReporter;
import cz.upol.jasgweb.Session;

public class Login {
	private static final String MANAGED_BEAN_NAME = "login";

	private Student loggedStudent;

	public Login() {
		System.out.println("Jede konstruktor!");
	}

	public static Login getLogin() {
		Login login = Session.getBean(MANAGED_BEAN_NAME);
		return login;
	}

	public Student getLoggedStudent() {
		System.out.println("Čtu lognutýho na \" " + loggedStudent);
		return loggedStudent;
	}

	public void setLoggedStudent(Student loggedStudent) {
		System.out.println("Nastavuji lognutýho na - " + loggedStudent);
		this.loggedStudent = loggedStudent;
	}

	public boolean isLoggedIn() {
		System.out.println("Je lognutý? " + (loggedStudent != null));
		return loggedStudent != null;
	}

	public void logIn(String username, String password) {
		System.out.println("Provádím login...");
		Student studentToLogIn;

		try {
			studentToLogIn = Student.loadByLogin(username, password);

			if (studentToLogIn != null) {
				loginSuccessfull(studentToLogIn);
			} else {
				loginUnsuccessfull(username, password);
			}
		} catch (DataAccessException e) {
			loginError(e);
		}
	}

	private void loginSuccessfull(Student studentToLogIn) {
		loggedStudent = studentToLogIn;

		String summary = "Přihlášení úspěšné";
		String detailedMessage = "Byl jste úspěšně přihlášen.";
		ResultReporter.reportSuccess(summary, detailedMessage);
	}

	private void loginUnsuccessfull(String username, String password) {
		String summary = "Přihlášení neúspěšné";
		String detailedMessage = "Uživatelské jméno nebo heslo jsou chybné.";
		ResultReporter.reportWarning(summary, detailedMessage);

	}

	private void loginError(Exception e) {
		String summary = "Chyba při přihlašování";
		String detailedMessage = "Při přihlašování došlo k chybě.";
		ResultReporter.reportError(summary, detailedMessage, e);
	}

	public void logOut() {
		loggedStudent = null;

		String summary = "Odhlášení bylo úspěšné";
		String detailedMessage = "Byl jste úspěšně odhlášen.";
		ResultReporter.reportSuccess(summary, detailedMessage);
	}
}
