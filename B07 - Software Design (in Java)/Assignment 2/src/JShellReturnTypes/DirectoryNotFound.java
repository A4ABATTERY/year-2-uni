package JShellReturnTypes;



public class DirectoryNotFound extends FileObjNotFound {
	/**
	 * method returns if Directory is Not Found
	 * @param fileName
	 */

    public DirectoryNotFound(String fileName) {
        super(fileName);
    }
    /**
     * the method returns an error message if Directory does not exist
     * @param fileName
     */
    public String toString() {
        return "JShell: Directory Not found: file or path to \"" + data
                + "\" was not found";

    }

}