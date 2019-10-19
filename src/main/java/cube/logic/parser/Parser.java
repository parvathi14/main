package cube.logic.parser;

import cube.logic.command.*;
import cube.exception.CubeException;
import cube.model.food.Food;
import cube.ui.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

// design pattern copied from address book
// reference: https://github.com/nusCS2113-AY1920S1/addressbook-level3/tree/master/src/main/java/seedu/address/logic/parser

/**
 * Parse user command.
 */
public class Parser {

	/**
	 * Parse user command.
	 *
	 * Format:
	 * add -n foodName -t foodType -p price -s stock -e expiryDate
	 * list --sort expiry/name/stock
	 * delete -i index / -n foodName / -t foodType
	 * sold -n foodName -q quantity
	 * reminder
	 * help
	 * bye / exit / quit
	 *
	 * Assumption: no repetitive parameters given.
	 *
	 * @param fullCommand the command that user inputs.
	 * @return corresponding command.
	 */
	public static Command parse (String fullCommand) throws CubeException{
		String[] inputs = fullCommand.split(" ");
		String command = inputs[0];
		command = command.trim().toLowerCase();
		switch (command) {
			case "add":
				return new AddCommandParser().parse(inputs);
			case "list":
				return new ListCommandParser().parse(inputs);
			case "delete":
				return new DeleteCommandParser().parse(inputs);
			case "sold":
				return new SoldCommandParser().parse(inputs);
			case "reminder":
				return new ReminderCommand();
			case "help":
				return new HelpCommand();
			case "bye":
			case "exit":
			case "quit":
				return new ExitCommand();
			default:
				throw new CubeException(Message.INVALID_COMMAND);
		}
	}
}

