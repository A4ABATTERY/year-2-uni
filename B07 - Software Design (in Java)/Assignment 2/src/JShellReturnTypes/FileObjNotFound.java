package JShellReturnTypes;


public class FileObjNotFound extends StdError{
	/**
	 * the method checks if File Object is Found
	 * @param fileName
	 */
    public FileObjNotFound(String fileName){
        data = fileName;
    }

    public String toString(){
    	/**
    	 * the method returns an error message if file or Directory does not 
    	 * exist
    	 * @param fileName
    	 */
        return "JShell: File or Directory not Found: \"" + data + 
            "\" does not exist";
    }
}