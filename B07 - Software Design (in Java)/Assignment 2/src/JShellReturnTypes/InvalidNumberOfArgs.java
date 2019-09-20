package JShellReturnTypes;

import java.util.List;


public class InvalidNumberOfArgs extends ArgError{

	/**
	 * Invalid Number of Arguments error
	 * @param args
	 * @param comm
	 */

    public InvalidNumberOfArgs(List<String> args, String comm){
        super(args, comm);
    }
    /**
	 * the method returns an error message if there is not enough or too much 
	 * arguments.
	 */
    public String toString(){
        return "JShell: " + commandName + ": Too many, or too few arguments: "+
            data ;
    }
}