package JShellReturnTypes;

public class InvalidFileName extends StdError{
	/**
	 * returns an InvalidFileName error
	 * @param fileName
	 */
	
    public InvalidFileName(String fileName){
        data = fileName;
    }
    public String toString(){
    	/**
    	 * the method returns an error message if the file name have 
    	 * invalid characters
    	 * @param fileName
    	 */
        return "JShell: Invalid File Name: The file name \"" + data + "\" has"+ 
            "invalid characters";
    }
}