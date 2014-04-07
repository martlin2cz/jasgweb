package cz.upol.jasgweb.errors;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import com.sun.faces.context.ExceptionHandlerFactoryImpl;

import cz.upol.jasgweb.ResultReporter;

public class JAsgExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler handler;
	
	public JAsgExceptionHandler() {
		ExceptionHandlerFactory fact = new ExceptionHandlerFactoryImpl();
		this.handler = fact.getExceptionHandler();
	}
	
	public JAsgExceptionHandler(ExceptionHandler handler) {
		super();
		this.handler = handler;
	}
	
	@Override
	public ExceptionHandler getWrapped() {
		return handler;
	}

	@Override
	public void handle() throws FacesException {
		for (ExceptionQueuedEvent event: getUnhandledExceptionQueuedEvents()) {
			Object source = event.getSource();
			if (source instanceof ExceptionQueuedEventContext) {
				ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) source;
				Throwable exception = context.getException();
				
				handleException(exception);
			}
		}
		
		super.handle();
	}
	
	private void handleException(Throwable e) {
		ResultReporter.reportError("Došlo k chybě", "Došlo k chybě", e);
	}
}
