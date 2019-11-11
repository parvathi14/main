/**
 * Handles input of commands to be executed within the GUI.
 *
 * @author kuromono
 */

package cube.ui;

import cube.exception.CubeException;
import cube.logic.command.util.CommandResult;
import cube.model.food.FoodList;
import cube.storage.StorageManager;
import cube.util.FileUtilJson;
import cube.util.LogUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.util.logging.Logger;


public class CommandBox extends UiManager<StackPane> {
    private static final String FXML = "CommandBox.fxml";

    @FXML
    private TextField commandTextField;

    private final CommandExecutor commandExecutor;

    private StorageManager storageManager;
    private FileUtilJson storage;
    private FoodList foodList;

    private final Logger logger = LogUtil.getLogger(CommandBox.class);

    /**
     * Main Constructor for CommandBox.
     *
     * @param commandExecutor Function that contains the logic of executing commands.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
    }

    /**
     * Sets the content to be shown in the command box.
     *
     * @param commandText Content to be shown.
     */
    public void setCommandText(String commandText) {
        commandTextField.clear();
        commandTextField.setText(commandText);
    }

    /**
     * Handles the Enter button pressed event listener.
     */
    @FXML
    private void handleCommandEntered() throws CubeException {
        String fullCommand = commandTextField.getText();

        try {
            commandExecutor.execute(fullCommand);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            commandTextField.clear();
        }
        commandTextField.clear();
    }


    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         */
        CommandResult execute(String commandText) throws CubeException;
    }
}
