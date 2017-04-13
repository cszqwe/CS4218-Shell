package sg.edu.nus.comp.cs4218.exception;

public class CdException extends AbstractApplicationException {

	private static final long serialVersionUID = 0;

	public CdException(String message) {
		super("cd: " + message);
	}

}
