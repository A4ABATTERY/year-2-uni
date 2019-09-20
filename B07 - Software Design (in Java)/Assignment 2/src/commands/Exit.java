package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import JShellReturnTypes.*;

/**
 * This class is a dummy class that exits the JShell
 */
public class Exit extends Command {
    /**
     * This method returns the string str.
     * 
     * @param args is a list of String that serve as parameters
     * @return an empty String
     */
    public List<RetType> execute(List<String> args) {
        List<RetType> output = validate(args);
        return output;
    }

    /**
     * This method returns the string that is in args[0]. the string at args[0]
     * is equal to "exit"
     * 
     * @param args is a list of Strings that serve as parameters
     * @return an empty String
     */
    protected List<RetType> validate(List<String> args) {
        List<RetType> outputs = new ArrayList<>();
        outputs.add(new StdOutput(args.get(0)));
        return outputs;
    }
}
