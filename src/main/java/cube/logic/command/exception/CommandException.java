//@@author tygq13
package cube.logic.command.exception;

import cube.exception.CubeException;

/**
 * Handles exception specific to command.
 */
public class CommandException extends CubeException {
    /**
     * Default constructor.
     */
	public CommandException() {
		super();
	}

    /**
     * Constructor with one argument.
     * Constructs the exception with message.
     *
     * @param message the message to be printed when exception happens.
     */
    public CommandException(String message) {
        super(message);
    }
}