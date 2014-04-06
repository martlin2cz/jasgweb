package cz.upol.jasgweb;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class ResultReporter {
	private static final String RESULT_FORM_NAME = "resultForm";

	public ResultReporter() {
	}

	public String getResultFormName() {
		return RESULT_FORM_NAME;
	}

	public static void reportError(String summary, String detailedMessage,
			Throwable e) {
		submitMessage(FacesMessage.SEVERITY_ERROR, summary, detailedMessage);
		// logování, odeslání emailu ...

	}

	public static void reportError(String summary, String detailedMessage) {
		reportError(summary, detailedMessage, null);
	}

	public static void reportWarning(String summary, String detailedMessage) {
		submitMessage(FacesMessage.SEVERITY_WARN, summary, detailedMessage);

	}

	public static void reportSuccess(String summary, String detailedMessage) {
		submitMessage(FacesMessage.SEVERITY_INFO, summary, detailedMessage);

	}

	private static void submitMessage(Severity severity, String summary,
			String detailedMessage) {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			
			FacesMessage message = new FacesMessage(severity, summary,
					detailedMessage);

			context.addMessage(RESULT_FORM_NAME, message);
		} catch (NullPointerException e) {
			System.err.println("Neexistuje kontext, Není kam odeslat hlášku");
		}
	}

}
