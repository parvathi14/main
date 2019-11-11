/**
 * The command list all the food.
 *
 * @author tygq13
 */

package cube.logic.command;

import cube.model.food.FoodList;
import cube.model.food.FoodList.SortType;
import cube.model.ModelManager;
import cube.storage.ProfitStorage;
import cube.storage.StorageManager;
import cube.logic.command.util.CommandResult;

public class ListCommand extends Command {
	public SortType sortType;

	public static final String MESSAGE_SUCCESS = "Here are the food in your list: \n"
		+ "%1$s\n"
		+ "The total revenue this year is $ %2$.2f\n";

	/**
	 * Default constructor of ListCommand.
	 */
	public ListCommand() {

	}

	/**
	 * Constructor with one argument.
	 *
	 * @param sortType The type of sorting used for food list.
	 */
	public ListCommand(SortType sortType) {
		this.sortType = sortType;
	}

	/**
	 * Shows the list of food.
	 * @param model The facade of all models.
	 * @param storage The storage manager for commands.
	 * @return The command result with feedback to user.
	 */
	@Override
	public CommandResult execute(ModelManager model, StorageManager storage) {
		FoodList list = model.getFoodList();
		if (sortType != null) {
			list.sort(sortType);
		}
		return new CommandResult(String.format(MESSAGE_SUCCESS, list, ProfitStorage.getAnnualRevenue()));
	}
}