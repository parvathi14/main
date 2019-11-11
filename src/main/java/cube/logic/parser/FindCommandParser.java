//@@author ZKathrynx

package cube.logic.parser;

import cube.logic.command.FindCommand;
import cube.logic.parser.exception.ParserErrorMessage;
import cube.logic.parser.exception.ParserException;
import cube.model.food.FoodList;

/**
 * Parse find command.
 */
public class FindCommandParser implements ParserPrototype<FindCommand> {

    /**
     * Parse user find command.
     * @param args user inputs.
     * @return find command with relative parameters.
     * @throws ParserException when user input is illegal.
     */
    public FindCommand parse(String[] args) throws ParserException {
        String[] params = new String[]{"-i","-n","-t","-sort"};

        if (ParserUtil.hasInvalidParameters(args,params)) {
            throw new ParserException(ParserErrorMessage.INVALID_PARAMETER);
        }
        if (ParserUtil.hasRepetitiveParameters(args)) {
            throw new ParserException(ParserErrorMessage.REPETITIVE_PARAMETER);
        }
        if (args.length < 3) {
            throw new ParserException(ParserErrorMessage.NOT_ENOUGH_PARAMETER);
        }

        switch (args[1]) {
            case "-i":
                if (!ParserUtil.isValidInteger(args[2])) {
                    throw new ParserException(ParserErrorMessage.INVALID_INTEGER);
                }
                return new FindCommand(Integer.parseInt(args[2]),"INDEX");
            case "-n":
                String name  = new ParserUtil().findFullString(args,2);
                int sortIndex = -1;
                for (int i = 1; i < args.length; i++) {
                    if (args[i].equals("-sort")) {
                        sortIndex = i;
                    }
                }
                if (sortIndex != -1) {
                    if (!ParserUtil.hasField(args,sortIndex + 1)) {
                        throw new ParserException(ParserErrorMessage.EMPTY_FIELD);
                    }
                    if (FoodList.SortType.isDefined(args[sortIndex + 1])) {
                        return new FindCommand(name,"NAME",
                                FoodList.SortType.valueOf(args[sortIndex + 1].toUpperCase()));
                    } else {
                        throw new ParserException(ParserErrorMessage.INVALID_SORT_TYPE);
                    }
                }
                return new FindCommand(name,"NAME");
            case "-t":
                String type  = new ParserUtil().findFullString(args,2);
                sortIndex = -1;
                for (int i = 1; i < args.length; i++) {
                    if (args[i].equals("-sort")) {
                        sortIndex = i;
                    }
                }
                if (sortIndex != -1) {
                    if (!ParserUtil.hasField(args,sortIndex + 1)) {
                        throw new ParserException(ParserErrorMessage.EMPTY_FIELD);
                    }
                    if (FoodList.SortType.isDefined(args[sortIndex + 1])) {
                        return new FindCommand(type,"TYPE",
                                FoodList.SortType.valueOf(args[sortIndex + 1].toUpperCase()));
                    } else {
                        throw new ParserException(ParserErrorMessage.INVALID_SORT_TYPE);
                    }
                }
                return new FindCommand(type,"TYPE");
            default:
                throw new ParserException(ParserErrorMessage.INVALID_COMMAND_FORMAT);
        }

    }
}
