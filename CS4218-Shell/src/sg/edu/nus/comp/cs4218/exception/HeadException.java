package sg.edu.nus.comp.cs4218.exception;

public class HeadException extends AbstractApplicationException {
	
	private static final long serialVersionUID = 2333796686823942499L;

	public HeadException(String message) {
		super("head: " + message);
	}
}