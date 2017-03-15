package sg.edu.nus.comp.cs4218.exception;

public class GrepException extends AbstractApplicationException {
	
	private static final long serialVersionUID = 7499486452467089104L;

	public GrepException(String message) {
		super("grep: " + message);
	}
}