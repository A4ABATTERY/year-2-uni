package JShellReturnTypes;

public class WebsiteNotFound extends StdError {
	
    private String data;
    /**
     * the method checks if website exists
     * @param path
     */
    public WebsiteNotFound(String path) {
        data = path;
    }
    /**
	 * the method returns an error message if website wasn't found
	 * @param fileName
	 */
    public String toString() {
        return "JShell: Website not found: \"" + data + "\" was not found";
    }
}