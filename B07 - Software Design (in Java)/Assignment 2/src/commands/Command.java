package commands;

import JShellReturnTypes.*;
import java.util.List;

/**
 * The Command class is an abstract class that when extended has a execution
 * and validation method.
 */
public abstract class Command {
    /**
     * This method runs the function of the command when called.
     * @param args is a list of String that serve as parameters.
     * @return a string indicting any errors or the results of the command.
     */
    public abstract List<RetType> execute(List<String> args);

    /**
     * This method checks if the parameters inserted match the
     * requirements of a Command class.
     * @param args is a list of Strings that serve as parameters.
     * @return A string depicting any problems with args, if they exist.
     */
    protected abstract List<RetType> validate(List<String> args);
}