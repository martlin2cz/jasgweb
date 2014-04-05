package cz.upol.jasgweb.login;

import javax.faces.validator.Validator;

import cz.upol.jasgweb.forms.validators.NoEmptyStrValidator;

public class LoginForm {
	private String username;
	private String password;

	private static final Validator USERNAME_VALIDATOR = new NoEmptyStrValidator(
			"uživatelské jméno");
	private static final Validator PASSWORD_VALIDATOR = new NoEmptyStrValidator(
			"heslo");


	
	public LoginForm() {
	}

	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Validator getUsernameValidator() {
		return USERNAME_VALIDATOR;
	}

	public Validator getPasswordValidator() {
		return PASSWORD_VALIDATOR;
	}

	public String logIn() {
		Login login = new Login();
		
		login.logIn(username, password);
		
		return "";
	}
	

}
