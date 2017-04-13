package sg.edu.nus.comp.cs4218.exception;

public class TailException extends AbstractApplicationException {

	private static final long serialVersionUID = 2333796686823942499L;

	public TailException(String message) {
		super("tail: " + message);
	}
}