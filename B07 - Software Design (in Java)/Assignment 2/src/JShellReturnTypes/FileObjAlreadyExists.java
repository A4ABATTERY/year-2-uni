package JShellReturnTypes;


public class FileObjAlreadyExists extends StdError{
	/**
	 * the method checks if FileObj Exists or not
	 * @param fileName
	 */
    public FileObjAlreadyExists(String fileName){
        data = fileName;
    }
    /**
	 * the method returns an error message if Directory already exists
	 * @param fileName
	 */
    public String toString(){
        return "JShell: File or Directory Already exists: \"" +  data +
            "\" already exists";
    }   
}