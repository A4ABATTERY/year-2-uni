package JShellReturnTypes;

import java.util.List;
	
public abstract class ArgError extends StdError{
	/**
	 * method checks if theres error in arguments
	 */
	protected String commandName;
	/**
	 * the method returns an error message if argument has an error
	 * @param fileName
	 */ 
    public ArgError(List<String> args, String comm){
       for(String d: args)
           data += d + " ";
        commandName = comm;
    }
}