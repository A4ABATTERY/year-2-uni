package JShellReturnTypes;

public class PathNotFound extends StdError{
	/**
	 * error method for path not existing
	 * @param path
	 */
    public PathNotFound(String path){
        data = path;
    }
    /**
	 * the method returns an error message if path does not exist
	 * @param fileName
	 */
    public String toString(){
        return "JShell: Path not found: \"" + data + 
               "\" was not found";
    }
}