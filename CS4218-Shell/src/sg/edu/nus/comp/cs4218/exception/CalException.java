package sg.edu.nus.comp.cs4218.exception;

public class CalException extends AbstractApplicationException {
	
	private static final long serialVersionUID = 8499486452467089104L;

	public CalException(String message) {
		super("cal: " + message);
	}
}