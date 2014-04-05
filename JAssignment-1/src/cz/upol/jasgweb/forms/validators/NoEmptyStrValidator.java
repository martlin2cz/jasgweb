package cz.upol.jasgweb.forms.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cz.upol.jasgweb.forms.validators.NoEmptyStrValidator")
public class NoEmptyStrValidator implements Validator {

	private final String ofWhat;

	public NoEmptyStrValidator() {
		ofWhat = null;
	}

	public NoEmptyStrValidator(String ofWhat) {
		this.ofWhat = ofWhat;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		String username = arg2.toString();
		String summary = null;

		if (username.isEmpty()) {
			if (ofWhat != null) {
				summary = "Musíte zadat " + ofWhat;
			} else {
				summary = "Toto pole musí být vyplněno";
			}
		}

		if (summary != null) {
			FacesMessage message = new FacesMessage(summary);
			throw new ValidatorException(message);
		}
	}

}
