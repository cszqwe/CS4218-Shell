package sg.edu.nus.comp.cs4218.exception;

public class WcException extends AbstractApplicationException {

	private static final long serialVersionUID = 0;

	public WcException(String message) {
		super("wc: " + message);
	}

}
