package sg.edu.nus.comp.cs4218.exception;

public class SedException extends AbstractApplicationException {
	
	private static final long serialVersionUID = 0;
	
	public SedException(String message) {
		super("sed: " + message);
	}

}
