package JShellReturnTypes;

import java.util.List;

public class InvalidArguments extends ArgError{
	/**
	 * returns an error if you have invalidArguments" 
	 * @param args
	 * @param comm
	 */
	
    public InvalidArguments(List<String> args, String comm){
        super(args, comm);
    }
    /**
	 * the method returns an error message if there are invalid Arguments.
	 * @param fileName
	 */
    public String toString(){
        return "JShell: " + commandName + ": Invalid Arguments: " + data;
    }
}