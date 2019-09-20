package JShellReturnTypes;

public class FileNotFound extends FileObjNotFound{
	/**
	 * method checks if File is found
	 * @param fileName
	 */
    public FileNotFound(String fileName){
        super(fileName);
    }
    /**
	 * the method returns an error message if file or path to file 
	 * does not exist
	 * @param fileName
	 */
    public String toString(){
        return "JShell: File not found: file or path to \"" + data + 
                "\" was not found";
    }
}