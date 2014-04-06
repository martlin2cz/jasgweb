package cz.upol.jasgweb.errors;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class JAsgExceptionHandlerFactory extends ExceptionHandlerFactory {

	public JAsgExceptionHandlerFactory() {
	}

	@Override
	public ExceptionHandler getExceptionHandler() {
		return new JAsgExceptionHandler();
	}

}
