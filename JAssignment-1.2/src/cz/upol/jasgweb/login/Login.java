package cz.upol.jasgweb.login;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;

import cz.upol.jasg.jasgdb.data.students.Student;
import cz.upol.jasg.jasgdb.errors.DataAccessException;
import cz.upol.jasgweb.ResultReporter;
import cz.upol.jasgweb.Session;

public class Login {


	public static final String MANAGED_BEAN_NAME = "login";
	public static final String MAIN_PAGE = "index.xhtml";

	private Student loggedStudent;

	public Login() {
	}

	public static Login getLogin() {
		Login login = Session.<Login> getBean(MANAGED_BEAN_NAME);
		if (login == null) {
			login = new Login();
			Session.<Login> addBean(MANAGED_BEAN_NAME, login);
		}

		return login;
	}

	public static Login getLogin(ServletRequest request) {
		return Session.getBean(request, MANAGED_BEAN_NAME);
	}

	public Student getLoggedStudent() {
		return loggedStudent;
	}

	public void setLoggedStudent(Student loggedStudent) {
		this.loggedStudent = loggedStudent;
	}

	public boolean isLoggedIn() {
		return loggedStudent != null;
	}

	public static boolean isLoggedInStatic() {
		Login login = getLogin();

		if (login != null) {
			return login.isLoggedIn();
		} else {
			return false;
		}
	}

	public static boolean isLoggedInStatic(ServletRequest request) {
		Login login = getLogin(request);

		if (login != null) {
			return login.isLoggedIn();
		} else {
			return false;
		}
	}

	public String logIn(String username, String password) {
		Student studentToLogIn;

		Logger loger = Logger.getLogger(this.getClass());
		loger.info("Pokus o přihlášení ...");
		
		try {
			studentToLogIn = Student.loadByLogin(username, password);

			if (studentToLogIn != null) {
				loginSuccessfull(studentToLogIn);
				return ResultReporter.SUCCESS;
			} else {
				loginUnsuccessfull(username, password);
				return ResultReporter.UNSUCCESS;
			}
		} catch (DataAccessException e) {
			loginError(e);
			return ResultReporter.ERROR;
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

	public String logOut() {
		loggedStudent = null;

		String summary = "Odhlášení bylo úspěšné";
		String detailedMessage = "Byl jste úspěšně odhlášen.";
		ResultReporter.reportSuccess(summary, detailedMessage);

		return "";
	}
}
